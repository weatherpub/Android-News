package edu.sfsu.times.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();

        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        // dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // recyclerView = binding.rvDashboardFragment;

        dashboardViewModel.getData().observe(getViewLifecycleOwner(), data -> {
          //  recyclerView.setAdapter(adapter);
          //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}