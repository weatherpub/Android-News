package edu.sfsu.times.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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

        // Refactored
        final TextView id = binding.tvIdArtist;
        final TextView artist = binding.tvStrArtist;
        final TextView born = binding.tvStrBornYear;
        final TextView formed = binding.tvStrFormedYear;
        final TextView style = binding.tvStrStyle;
        final TextView genre = binding.tvStrGenre;
        final TextView mood = binding.tvStrMood;
        final TextView website = binding.tvStrWebsite;
        final TextView facebook = binding.tvStrFacebook;
        final TextView biography = binding.tvStrBiographyEN;
        final TextView gender = binding.tvStrGender;
        final TextView country = binding.tvStrCountry;
        final ImageView thumbnail = binding.ivStrArtistThumb;

        // Live Data
        dashboardViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            Picasso.get().load(data.get(0).getStrArtistThumb()).into(thumbnail);
            id.setText(data.get(0).getIdArtist());
            artist.setText(data.get(0).getStrArtist());
            born.setText(data.get(0).getIntBornYear());
            formed.setText(data.get(0).getIntFormedYear());
            style.setText(data.get(0).getStrStyle());
            genre.setText(data.get(0).getStrGenre());
            mood.setText(data.get(0).getStrMood());
            website.setText(data.get(0).getStrWebsite());
            facebook.setText(data.get(0).getStrFacebook());
            biography.setText(data.get(0).getStrBiographyEN());
            gender.setText(data.get(0).getStrGender());
            country.setText(data.get(0).getStrCountry());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}