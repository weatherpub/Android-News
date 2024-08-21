package edu.sfsu.times.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
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

import com.squareup.picasso.Picasso;

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

        ImageView imgView = (ImageView) findViewById(R.id.home_detail_image);
        TextView tv_detail_name = (TextView) findViewById(R.id.home_detail_name);
        TextView tv_detail_author = (TextView) findViewById(R.id.home_detail_author);
        TextView tv_detail_title = (TextView) findViewById(R.id.home_detail_title);
        TextView tv_detail_description = (TextView) findViewById(R.id.home_detail_description);
        TextView tv_detail_url = (TextView) findViewById(R.id.home_detail_url);
        TextView tv_detail_url_to_image = (TextView) findViewById(R.id.home_detail_url_to_image);
        TextView tv_detail_published_at = (TextView) findViewById(R.id.home_detail_published_at);
        TextView tv_detail_content = (TextView) findViewById(R.id.home_detail_content);

        // Get ViewModel from HomeViewModel()
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        final Observer<ArrayList<DataModel>> dm = new Observer<ArrayList<DataModel>>() {
            @Override
            public void onChanged(ArrayList<DataModel> dataModels) {
                if(dataModel == null) {
                    dataModel = dataModels;
                    Picasso.get().load(dataModel.get(position).getUrlToImage()).into(imgView);
                    tv_detail_name.setText(dataModel.get(position).getName());
                    tv_detail_author.setText(dataModel.get(position).getAuthor());
                    tv_detail_title.setText(dataModel.get(position).getTitle());
                    tv_detail_description.setText(dataModel.get(position).getDescription());
                    tv_detail_url.setText(dataModel.get(position).getUrl());
                    tv_detail_url_to_image.setText(dataModel.get(position).getUrlToImage());
                    tv_detail_published_at.setText(dataModel.get(position).getPublishedAt());
                    tv_detail_content.setText(dataModel.get(position).getContent());
                }
            }
        };

        homeViewModel.getData().observe(this, dm);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}