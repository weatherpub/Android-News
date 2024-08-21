package edu.sfsu.times.ui.notifications;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.model.DataModelViewModel;

public class NotificationsViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<DataModel>> data;

    private final ArrayList<DataModel> model;

    /*
    public HomeViewModel() {
        data = new MutableLiveData<>();
        DataModelViewModel dms = DataModelViewModel.getInstance();
        model = dms.getData();
    }
    */

    public NotificationsViewModel(MutableLiveData<ArrayList<DataModel>> data, ArrayList<DataModel> model) {
        String SOURCES = "bbc-news";
        String QUERY = "nvidia";

        this.data = data;
        this.model = model;

        //new ViewModelAsyncTask().execute("https://newsapi.org/v2/everything?q=" + QUERY + "&apiKey=6e5104549c7f49b0b6e2ff7a036b9939");
        new NotificationAsyncTask().execute("https://newsapi.org/v2/top-headlines?sources=" + SOURCES + "&apiKey=6a5b4f0943e447a092cc59f7fbe690ef");
    }

    public LiveData<ArrayList<DataModel>> getData() {
        return data;
    }

    public class NotificationAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return "";
        }
    }
}