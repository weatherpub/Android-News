package edu.sfsu.times.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import edu.sfsu.times.model.audiodb.AlbumModel;
import edu.sfsu.times.model.audiodb.AlbumViewModel;

public class DashboardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private final ArrayList<AlbumModel> model;

    public DashboardRecyclerViewAdapter() {
        AlbumViewModel dms = AlbumViewModel.getInstance(); // request an object from the singleton.
        model = dms.getData();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        View itemView = holder.itemView;

        AlbumModel item = model.get(position);

        holder.title.setText(String.format("%s", item.getIdAlbum()));
        Picasso.get().load(item.getStrAlbumThumb()).resize(250, 200).centerCrop().into(holder.urlToImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}