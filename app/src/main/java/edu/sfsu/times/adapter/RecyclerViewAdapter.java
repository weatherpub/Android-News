package edu.sfsu.times.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.sfsu.times.R;
import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.model.DataModelViewModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private final ArrayList<DataModel> model;

    public RecyclerViewAdapter() {
        DataModelViewModel dms = DataModelViewModel.getInstance(); // request an object from the singleton.
        model = dms.getData();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        View itemView = holder.itemView;
        DataModel item = model.get(position);

        holder.title.setText(String.format("%s", item.getTitle()));
        Picasso.get().load(item.getUrlToImage()).resize(250, 200).centerCrop().into(holder.urlToImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView urlToImage;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            urlToImage = (ImageView) view.findViewById(R.id.tv_url_to_image);
        }
    }
}