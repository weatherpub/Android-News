package edu.sfsu.times.ui.home;

import android.app.ProgressDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.model.DataModelViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// singleton in disguise
public class HomeViewModel extends ViewModel {

    private  MutableLiveData<ArrayList<DataModel>> data;
    private final ArrayList<DataModel> model;

    public HomeViewModel() {
        String SOURCES = "bbc";
        String QUERY = "economy";

        data = new MutableLiveData<>();
        DataModelViewModel dms = DataModelViewModel.getInstance();
        model = dms.getData();
        /* *
         * https://newsapi.org/v2/everything?q=bitcoin&apiKey=6e5104549c7f49b0b6e2ff7a036b9939
         * https://newsapi.org/v2/everything?q=facebook&apiKey=6e5104549c7f49b0b6e2ff7a036b9939
         * https://newsapi.org/v2/top-headlines?sources=" + SOURCES + "&apiKey=6a5b4f0943e447a092cc59f7fbe690ef
         */

        new ViewModelAsyncTask().execute("https://newsapi.org/v2/everything?q=" + QUERY + "&apiKey=6e5104549c7f49b0b6e2ff7a036b9939");
        // new ViewModelAsyncTask().execute("https://newsapi.org/v2/top-headlines?sources=" + SOURCES + "&apiKey=6a5b4f0943e447a092cc59f7fbe690ef");
    }

    public LiveData<ArrayList<DataModel>> getData() {
    // public MutableLiveData<ArrayList<DataModel>> getData() {
        Log.i("LOG", "LiveData-> " + data);
        if(data == null) {
            data = new MutableLiveData<>();
        }

        Log.i("LOG", "LiveData-> after new mutableLiveData<>() " + data);

        return data;
    }

    /* *
     * Non-static nested classes (inner classes) have access to other members of the enclosing class,
     * even if they are declared private.
     */
    public class ViewModelAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Log.i("LOG", "Get Context");

            // progressDialog = new ProgressDialog(MainActivity.this);
            /*
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
            */
        }

        @Override
        protected String doInBackground(String... param) {

            Log.i("LOG", "doInBackground(String... param)");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(param[0]).build();

            try {
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful())
                    return null;

                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Main UI
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                SQLiteDatabase sd = null;
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("articles");

                // Add new json objects to DataModel to supply the data variable; which is -> MutableLiveData<ArrayList<DataModel>>
                for(int i =  0; i < obj.length(); i++) {
                    model.add(new DataModel(
                            obj.getJSONObject(i).getJSONObject("source").getString("name"),
                            obj.getJSONObject(i).getString("author"),
                            obj.getJSONObject(i).getString("title"),
                            obj.getJSONObject(i).getString("description"),
                            obj.getJSONObject(i).getString("url"),
                            obj.getJSONObject(i).getString("urlToImage"),
                            obj.getJSONObject(i).getString("publishedAt"),
                            obj.getJSONObject(i).getString("content")));
                }

                // pass the model to the ViewModel
                data.setValue(model);
                // not this! data.setValue(data.getValue());
            } catch (JSONException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}