package edu.sfsu.times.ui.home;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.PrintWriter;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;

import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.rvHomeFragment;

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            // Write file to disk. Thread 1
            // Add new thread here.

            // This works, by writing data to disk.
            try {
                File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_home.txt");
                PrintWriter printWriter = new PrintWriter(file);

                printWriter.write(data.get(0).getAuthor());

                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Read file - thread 2
            for(int j = 0; j < data.size(); j++) {
                Log.v("LOG", data.get(j).getContent());
            }

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        return root;

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //File file = new File("_home.txt");

                Log.i("LOG", binding.toString());
                try {
                    File file = new File(requireContext().getFilesDir(), fmt.format("_home.txt")); // new name
                    //FileWriter fileWriter = new FileWriter(file);

                    PrintWriter printWriter = new PrintWriter(file);
                    //printWriter.write(result.toString());
                    printWriter.write("I love white women");
                    printWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}