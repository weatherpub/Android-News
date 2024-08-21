package edu.sfsu.times.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sfsu.times.activity.HomeDetailActivity;
import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentHomeBinding;
import edu.sfsu.times.model.DataModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<DataModel> model;
    private RecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        recyclerView = binding.rvHomeFragment;

        // From  Android Documentation - [ example code ]
        final Observer<DataModel> nameObserver = new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {

            }
        };
        // end [ example code ]

        adapter.setListener(new RecyclerViewAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Log.i("LOG", "adapter.setListener -> " + position);
                Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                intent.putExtra(HomeDetailActivity.IMAGE_ID, position);
                getActivity().startActivity(intent);
            }
        });
                /*
                intent.putExtra("name", mod.observe(getViewLifecycleOwner(), dataModels -> ));
                intent.putExtra("author", author);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("url", url);
                intent.putExtra("urlToImage", urlToImage);
                intent.putExtra("publishedAt", publishedAt);
                intent.putExtra("content", content);

                // country.setText(data.get(0).getCountry());
                // name.setText(data.get(0).getName());
                // Log.i("LOG", "data.get(position).getName() -> " + dataModel.
                    String name = data.get(position).getName();
                    String author = data.get(position).getAuthor();
                    String title = data.get(position).getTitle();
                    String description = data.get(position).getDescription();
                    String url = data.get(position).getUrl();
                    String urlToImage = data.get(position).getUrlToImage();
                    String publishedAt = data.get(position).getPublishedAt();
                    String content = data.get(position).getContent();

                    intent.putExtra("name", name);
                    intent.putExtra("author", author);
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("url", url);
                    intent.putExtra("urlToImage", urlToImage);
                    intent.putExtra("publishedAt", publishedAt);
                    intent.putExtra("content", content);

                getActivity().startActivity(intent);
        */

        homeViewModel.getData().observe(getViewLifecycleOwner(),
                new Observer<ArrayList<DataModel>>() {
                    @Override
                    public void onChanged(ArrayList<DataModel> dataModels) {
                        Log.i("LOG", "onChanged ");
                    }
                }
        );


        // Update UI (RecyclerView)
        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            // Log.i("LOG", "data.get(position).getName() -> " + data.get(0).getName());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}