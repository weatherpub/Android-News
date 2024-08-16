package edu.sfsu.times.ui.home;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentHomeBinding;
import edu.sfsu.times.model.DataModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        recyclerView = binding.rvHomeFragment;

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            /*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_home.txt");
                        PrintWriter printWriter = new PrintWriter(file);

                        Log.v("LOG", "Written to disk -> " + data);

                        // this will only print the objects
                        printWriter.write(data.toString());

                        printWriter.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            */

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