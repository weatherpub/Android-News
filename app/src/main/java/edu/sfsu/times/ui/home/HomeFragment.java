package edu.sfsu.times.ui.home;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import edu.sfsu.times.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.rvHomeFragment;

        // save data to disk here

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            for(int j = 0; j < data.size(); j++) {
                Log.v("LOG", data.get(j).getContent());
            }
            //recyclerView.setAdapter();
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

        /*
        final TextView textView = binding.textHome;
        homeViewModel.getData().observe(getViewLifecycleOwner(), textView::setText);
        */

        /*
        final RecyclerView rv_homeFragment = binding.rvHomeFragment;
        */


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}