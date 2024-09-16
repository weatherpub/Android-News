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

import edu.sfsu.times.model.audiodb.AlbumModel;
import edu.sfsu.times.model.audiodb.AlbumViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<AlbumModel>> mutableLiveData;
    private final ArrayList<AlbumModel> model;
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
        AlbumViewModel dms = AlbumViewModel.getInstance();
        model = dms.getData(); // albumModel

        final String q = "daft_punk";
        new AsyncTaskViewModel().execute("https://www.theaudiodb.com/api/v1/json/2/searchalbum.php?s=" + q);
    }

    public LiveData<ArrayList<AlbumModel>> getData() {
        Log.i("LOG", "LiveData<ArrayList<AlbumModel>> - ");
        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        return mutableLiveData;
    }

    /* Inner Class */
    public class AsyncTaskViewModel extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.i("LOG", "doInBackground(String... param)");

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
            Log.i("LOG", "result -> " + result);

            // JSONObject jsonObject = null;
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("album");

                // Add new json objects to DataModel to supply the data variable; which is -> MutableLiveData<ArrayList<DataModel>>
                for(int i =  0; i < obj.length(); i++) {
                    model.add(new AlbumModel(
                            obj.getJSONObject(i).getString("idAlbum"),
                            obj.getJSONObject(i).getString("idArtist"),
                            obj.getJSONObject(i).getString("idLabel"),
                            obj.getJSONObject(i).getString("strAlbum"),
                            obj.getJSONObject(i).getString("strAlbumStripped"),
                            obj.getJSONObject(i).getString("strArtist"),
                            obj.getJSONObject(i).getString("strArtistStripped"),
                            obj.getJSONObject(i).getString("intYearReleased"),
                            obj.getJSONObject(i).getString("strStyle"),
                            obj.getJSONObject(i).getString("strGenre"),
                            obj.getJSONObject(i).getString("strLabel"),
                            obj.getJSONObject(i).getString("strReleaseFormat"),
                            obj.getJSONObject(i).getString("intSales"),
                            obj.getJSONObject(i).getString("strAlbumThumb"),
                            obj.getJSONObject(i).getString("strAlbumThumbHQ"),
                            obj.getJSONObject(i).getString("strAlbumThumbBack"),
                            obj.getJSONObject(i).getString("strAlbumCDart"),
                            obj.getJSONObject(i).getString("strAlbumSpine"),
                            obj.getJSONObject(i).getString("strAlbum3DCase"),
                            obj.getJSONObject(i).getString("strAlbum3DFlat"),
                            obj.getJSONObject(i).getString("strAlbum3DFace"),
                            obj.getJSONObject(i).getString("strAlbum3DThumb"),
                            obj.getJSONObject(i).getString("strDescriptionEN"),
                            obj.getJSONObject(i).getString("strDescriptionDE"),
                            obj.getJSONObject(i).getString("strDescriptionFR"),
                            obj.getJSONObject(i).getString("strDescriptionCN"),
                            obj.getJSONObject(i).getString("strDescriptionIT"),
                            obj.getJSONObject(i).getString("strDescriptionJP"),
                            obj.getJSONObject(i).getString("strDescriptionRU"),
                            obj.getJSONObject(i).getString("strDescriptionES"),
                            obj.getJSONObject(i).getString("strDescriptionPT"),
                            obj.getJSONObject(i).getString("strDescriptionSE"),
                            obj.getJSONObject(i).getString("strDescriptionNL"),
                            obj.getJSONObject(i).getString("strDescriptionHU"),
                            obj.getJSONObject(i).getString("strDescriptionNO"),
                            obj.getJSONObject(i).getString("strDescriptionIL"),
                            obj.getJSONObject(i).getString("strDescriptionPL"),
                            obj.getJSONObject(i).getString("intLoved"),
                            obj.getJSONObject(i).getString("intScore"),
                            obj.getJSONObject(i).getString("intScoreVotes"),
                            obj.getJSONObject(i).getString("strReview"),
                            obj.getJSONObject(i).getString("strMood"),
                            obj.getJSONObject(i).getString("strTheme"),
                            obj.getJSONObject(i).getString("strSpeed"),
                            obj.getJSONObject(i).getString("strLocation"),
                            obj.getJSONObject(i).getString("strMusicBrainzID"),
                            obj.getJSONObject(i).getString("strMusicBrainzArtistID"),
                            obj.getJSONObject(i).getString("strAllMusicID"),
                            obj.getJSONObject(i).getString("strBBCReviewID"),
                            obj.getJSONObject(i).getString("strRateYourMusicID"),
                            obj.getJSONObject(i).getString("strDiscogsID"),
                            obj.getJSONObject(i).getString("strWikidataID"),
                            obj.getJSONObject(i).getString("strWikipediaID"),
                            obj.getJSONObject(i).getString("strGeniusID"),
                            obj.getJSONObject(i).getString("strLyricWikiID"),
                            obj.getJSONObject(i).getString("strMusicMozID"),
                            obj.getJSONObject(i).getString("strItunesID"),
                            obj.getJSONObject(i).getString("strAmazonID"),
                            obj.getJSONObject(i).getString("strLocked")));
                    // pass the model to the ViewModel
                    //mutableLiveData.setValue(model);
                    Log.i("LOG", "mutableLiveData.setValue(model): -> " + model);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}