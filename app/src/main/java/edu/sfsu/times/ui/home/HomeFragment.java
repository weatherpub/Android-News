package edu.sfsu.times.ui.home;

import static edu.sfsu.times.sql.DatabaseHelper.insert;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

import edu.sfsu.times.MainActivity;
import edu.sfsu.times.R;
import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentHomeBinding;
import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.sql.DatabaseHelper;

public class HomeFragment extends Fragment {

    // private ProgressBar spinner;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        recyclerView = binding.rvHomeFragment;

        // Update UI (RecyclerView)
        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {
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