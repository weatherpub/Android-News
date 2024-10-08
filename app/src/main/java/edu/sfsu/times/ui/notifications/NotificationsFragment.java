package edu.sfsu.times.ui.notifications;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import edu.sfsu.times.R;
import edu.sfsu.times.adapter.RecyclerViewAdapter;
import edu.sfsu.times.databinding.FragmentNotificationsBinding;
import edu.sfsu.times.sql.DatabaseHelper;
import edu.sfsu.times.ui.home.HomeViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getContext();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.i("LOG", "Inside of run() 1");

        SQLiteOpenHelper helper = new DatabaseHelper(context);

        Log.i("LOG", "Inside of run() 2");

        // pull data from the DatabaseHelper()
        try {
            Log.i("LOG", "Inside of run() 3");
            int i = 0;
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor cursor = db.query("ARTICLES",
                    new String[] {"NAME", "AUTHOR", "TITLE", "DESCRIPTION", "URL", "URL_TO_IMAGE", "PUBLISHED_AT", "CONTENT"},
                    null, null, null, null, null);

            ImageView imageView = (ImageView) root.findViewById(R.id.home_detail_image);
            TextView name = (TextView) root.findViewById(R.id.tv_name);
            TextView author = (TextView) root.findViewById(R.id.tv_author);
            TextView title = (TextView) root.findViewById(R.id.tv_title);
            TextView description = (TextView) root.findViewById(R.id.tv_description);
            TextView url = (TextView) root.findViewById(R.id.tv_url);
            TextView url_to_img = (TextView) root.findViewById(R.id.tv_url_to_image);
            TextView published = (TextView) root.findViewById(R.id.tv_published_at);
            TextView content = (TextView) root.findViewById(R.id.tv_content);

            int j = 0;
            int k = 0;
            if(cursor.moveToFirst()) {
                Log.i("LOG", "k-> " + k);
                k = k + 1;
                do {
                    Log.i("LOG", "j-> " + j);
                    j = j + 1;
                    String name_column = cursor.getString(0);
                    String author_column = cursor.getString(1);
                    String title_column = cursor.getString(2);
                    String description_column = cursor.getString(3);
                    String url_column = cursor.getString(4);
                    String url_to_img_column = cursor.getString(5);
                    String published_column = cursor.getString(6);
                    String content_column = cursor.getString(7);

                    Picasso.get().load(url_to_img_column).into(imageView);
                    name.setText(name_column);
                    author.setText(author_column);
                    title.setText(title_column);
                    description.setText(description_column);
                    url.setText(url_column);
                    published.setText(published_column);
                    url_to_img.setText(url_to_img_column);
                    content.setText(cursor.getString(7));
                } while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        /*
            Need to do more research on this.
            Would like db results in a grid.

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        */
        final TextView textView = binding.tvName;
        // notificationsViewModel.getData().observe(getViewLifecycleOwner(), textView::setText()); // <- another problem here. comment this line and line #28 to debug
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText); // <- another problem here. comment this line and line #28 to debug
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}