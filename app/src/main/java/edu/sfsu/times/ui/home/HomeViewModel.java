package edu.sfsu.times.ui.home;
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
    private final MutableLiveData<ArrayList<DataModel>> data;

    private final ArrayList<DataModel> model;

    public HomeViewModel() {
        String SOURCES = "bbc-news";
        String QUERY = "nvidia";

        data = new MutableLiveData<>();
        DataModelViewModel dms = DataModelViewModel.getInstance();
        model = dms.getData();
        /* *
         * https://newsapi.org/v2/everything?q=bitcoin&apiKey=6e5104549c7f49b0b6e2ff7a036b9939
         * https://newsapi.org/v2/everything?q=facebook&apiKey=6e5104549c7f49b0b6e2ff7a036b9939
         * https://newsapi.org/v2/top-headlines?sources=" + SOURCES + "&apiKey=6a5b4f0943e447a092cc59f7fbe690ef
         */

        new ViewModelAsyncTask().execute("https://newsapi.org/v2/everything?q=" + QUERY + "&apiKey=6e5104549c7f49b0b6e2ff7a036b9939");
    }

    public LiveData<ArrayList<DataModel>> getData() {
        return data;
    }

    /* *
     * Non-static nested classes (inner classes) have access to other members of the enclosing class,
     * even if they are declared private.
     */
    public class ViewModelAsyncTask extends AsyncTask<String, Void, String> {

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

        // this is the main ui
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
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

                data.setValue(data.getValue());
                //data.setValue(model); // populate the live data for the fragment

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
/*
@Override
protected void onPostExecute(String result) { // onPostExecute - runs on the main thread.
    super.onPostExecute(result);

    @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    // background thread to open and parse the downloaded json.
    final Handler handler = new Handler();

    new Thread(new Runnable() {
        @Override
        public void run() {
            Log.v("LOG", "Inside of the new Runnable Thread!");

            try {
                File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_home.txt");
                FileWriter fileWriter = new FileWriter(file);

                // Write JSON response to disk.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(result.toString());
                bufferedWriter.close();

                // Read JSON response from disk.
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();

                while(line != null) {
                    stringBuilder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

                // This is being populated from disk.
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray obj = jsonObject.getJSONArray("articles");

                for(int i =  0; i < obj.length(); i++) {
                    newsModel.add(new NewsModel(
                            obj.getJSONObject(i).getJSONObject("source").getString("name"),
                            obj.getJSONObject(i).getString("author"),
                            obj.getJSONObject(i).getString("title"),
                            obj.getJSONObject(i).getString("description"),
                            obj.getJSONObject(i).getString("url"),
                            obj.getJSONObject(i).getString("urlToImage"),
                            obj.getJSONObject(i).getString("publishedAt"),
                            obj.getJSONObject(i).getString("content")));
                }
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }

            // Handler allows the UI to be updated.
            handler.post(new Runnable() {
                @Override
                public void run() {
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(newsModel);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter.setListener(new RecyclerViewAdapter.Listener() {
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent(getActivity(), ContentActivity.class);
                            Log.v("LOG", "[ July 17, 2024 onClick intent in HomeFragment was clicked ] => " + position);
                            String url = newsModel.get(position).getUrlToImage();
                            String content = newsModel.get(position).getContent();

                            intent.putExtra("image", url);
                            intent.putExtra("content", content);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }).start();
}
 */