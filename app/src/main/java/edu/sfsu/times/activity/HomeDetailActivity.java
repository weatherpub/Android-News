package edu.sfsu.times.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.sfsu.times.R;
import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.ui.home.HomeViewModel;

public class HomeDetailActivity extends AppCompatActivity {

    public static final String IMAGE_ID = "position";
    private ArrayList<DataModel> dataModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_detail);


        // convert the string to an integer - it just works
        int position = (Integer) getIntent().getExtras().get(IMAGE_ID);

        Log.i("LOG", "HomeDetailActivity received position 2 -> " + position);

        /* * * * * * * * * * * * * * * * * */
        TextView tv_detail_name = (TextView) findViewById(R.id.home_detail_name);

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        final Observer<ArrayList<DataModel>> dm = new Observer<ArrayList<DataModel>>() {
            @Override
            public void onChanged(ArrayList<DataModel> dataModels) {
                if(dataModel == null) {
                    dataModel = dataModels;
                    Log.i("LOG", "dataModel -> " + dataModel);
                    //recyclerViewAdapter = new RecyclerViewAdapter();
                } else {
                    Log.i("LOG", "dataModel -> is empty");
                }
            }
        };

        homeViewModel.getData().observe(this, dm);

        /*
        final Observer<LiveData<DataModel>> dataObserver = new Observer<LiveData<DataModel>>() {
            @Override
            public void onChanged(LiveData<DataModel> dataModelLiveData) {
                tv_detail_name.setText(dataModelLiveData.getValue().getName());
            }
        };

        homeViewModel.getData().observe(this, dataObserver);
        */

        /*
        final Observer<LiveData<ArrayList<DataModel>>> nameObserver = new Observer<DataModel>() {
            @Override
            public void onChanged(@Nullable final DataModel dm) {
                tv_detail_name.setText(dm.getContent());
            }
        };

        homeViewModel.get

        homeViewModel.getData().observe(this, data > {
            Log.i("LOG", "dataModels -> " + dataModels.get(0).getName());
        });
        */
        /*
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(final String newName) {
                tv_detail_name.setText(newName);
            }
        };
        homeViewModel.getDataMod().observe(this, nameObserver);
        */

        /*
        MutableLiveData<String> stringMutableLiveData;

        stringMutableLiveData = homeViewModel.getDataMod();
        */


        /*
        Intent intent = getIntent();

        ImageView imgView = (ImageView) findViewById(R.id.home_detail_image);
        TextView tv_author = (TextView) findViewById(R.id.home_detail_author);
        TextView tv_title = (TextView) findViewById(R.id.home_detail_title);
        TextView tv_description = (TextView) findViewById(R.id.home_detail_description);
        TextView tv_url = (TextView) findViewById(R.id.home_detail_url);
        TextView tv_published_at = (TextView) findViewById(R.id.home_detail_published_at);
        TextView tv_content = (TextView) findViewById(R.id.home_detail_content);

        String str_image = intent.getStringExtra("image");
        String str_name = intent.getStringExtra("name");
        String str_author = intent.getStringExtra("author");
        String str_title = intent.getStringExtra("title");
        String str_description = intent.getStringExtra("description");
        String str_url = intent.getStringExtra("url");
        String str_url_to_image = intent.getStringExtra("urlToImage");
        String str_publishedAt = intent.getStringExtra("publishedAt");
        String str_content = intent.getStringExtra("content");

        Picasso.get().load(str_url_to_image).into(imgView);
        tv_detail_name.setText(str_name);
        */

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}