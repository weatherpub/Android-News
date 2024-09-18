package edu.sfsu.times.ui.dashboard;

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

import edu.sfsu.times.model.audiodb.ArtistModel;
import edu.sfsu.times.model.audiodb.ArtistViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ArtistModel>> mutableLiveData;
    private final ArrayList<ArtistModel> model;
    /*
     09.12.24
     create subdirectories for the models
     Album/Model
     Artist/Model
     Dashboard/Model
     the problem is the naming of the models are named incorrectly.
     1. solve by renaming the models, then fix DashboardViewModel, DashboardRecyclerViewAdapter
    */
    public DashboardViewModel() {

        mutableLiveData = new MutableLiveData<>();
        ArtistViewModel dms = ArtistViewModel.getInstance();
        model = dms.getData(); // albumModel

        new AsyncTaskViewModel().execute("https://www.theaudiodb.com/api/v1/json/2/artist.php?i=112024");
    }

    public LiveData<ArrayList<ArtistModel>> getData() {

        Log.i("LOG", "LiveData<ArrayList<ArtistModel>> - ");

        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        return mutableLiveData;
    }

    /* Inner Class */
    public class AsyncTaskViewModel extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.i("LOG", "DashboardViewModel -> doInBackground(String... param)");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();

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

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("LOG", "ArtistViewModel version");
            Log.i("LOG", "artist result -> " + result);

            // JSONObject jsonObject = null;
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("artists");

                // Add new json objects to DataModel to supply the data variable; which is -> MutableLiveData<ArrayList<DataModel>>
                for(int i = 0; i < obj.length(); i++) {
                    model.add(new ArtistModel(
                            obj.getJSONObject(i).getString("idArtist"),
                            obj.getJSONObject(i).getString("strArtist"),
                            obj.getJSONObject(i).getString("strArtistStripped"),
                            obj.getJSONObject(i).getString("strArtistAlternate"),
                            obj.getJSONObject(i).getString("strLabel"),
                            obj.getJSONObject(i).getString("idLabel"),
                            obj.getJSONObject(i).getString("intFormedYear"),
                            obj.getJSONObject(i).getString("intBornYear"),
                            obj.getJSONObject(i).getString("intDiedYear"),
                            obj.getJSONObject(i).getString("strDisbanded"),
                            obj.getJSONObject(i).getString("strStyle"),
                            obj.getJSONObject(i).getString("strGenre"),
                            obj.getJSONObject(i).getString("strMood"),
                            obj.getJSONObject(i).getString("strWebsite"),
                            obj.getJSONObject(i).getString("strFacebook"),
                            obj.getJSONObject(i).getString("strTwitter"),
                            obj.getJSONObject(i).getString("strBiographyEN"),
                            obj.getJSONObject(i).getString("strBiographyDE"),
                            obj.getJSONObject(i).getString("strBiographyFR"),
                            obj.getJSONObject(i).getString("strBiographyCN"),
                            obj.getJSONObject(i).getString("strBiographyIT"),
                            obj.getJSONObject(i).getString("strBiographyJP"),
                            obj.getJSONObject(i).getString("strBiographyRU"),
                            obj.getJSONObject(i).getString("strBiographyES"),
                            obj.getJSONObject(i).getString("strBiographyPT"),
                            obj.getJSONObject(i).getString("strBiographySE"),
                            obj.getJSONObject(i).getString("strBiographyNL"),
                            obj.getJSONObject(i).getString("strBiographyHU"),
                            obj.getJSONObject(i).getString("strBiographyNO"),
                            obj.getJSONObject(i).getString("strBiographyIL"),
                            obj.getJSONObject(i).getString("strBiographyPL"),
                            obj.getJSONObject(i).getString("strGender"),
                            obj.getJSONObject(i).getString("strCountry"),
                            obj.getJSONObject(i).getString("strCountryCode"),
                            obj.getJSONObject(i).getString("strArtistThumb"),
                            obj.getJSONObject(i).getString("strArtistLogo"),
                            obj.getJSONObject(i).getString("strArtistCutout"),
                            obj.getJSONObject(i).getString("strArtistClearart"),
                            obj.getJSONObject(i).getString("strArtistWideThumb"),
                            obj.getJSONObject(i).getString("strArtistFanart"),
                            obj.getJSONObject(i).getString("strArtistFanart2"),
                            obj.getJSONObject(i).getString("strArtistFanart3"),
                            obj.getJSONObject(i).getString("strArtistFanart4"),
                            obj.getJSONObject(i).getString("strArtistBanner"),
                            obj.getJSONObject(i).getString("strMusicBrainzID"),
                            obj.getJSONObject(i).getString("strISNIcode"),
                            obj.getJSONObject(i).getString("strLastFMChart"),
                            obj.getJSONObject(i).getString("strLocked")));
                    // pass the model to the ViewModel
                    Log.i("LOG", "mutableLiveData.setValue(model): -> " + model);
                }
                mutableLiveData.setValue(model);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}