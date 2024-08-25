package edu.sfsu.times.ui.notifications;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.sfsu.times.R;
import edu.sfsu.times.databinding.FragmentNotificationsBinding;
import edu.sfsu.times.sql.DatabaseHelper;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        // getContext() - return the context this fragment is currently associated with.
        SQLiteOpenHelper helper = new DatabaseHelper(getContext());

        try {
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor cursor = db.query("ARTICLES",
                    new String[] {"NAME", "AUTHOR", "TITLE", "DESCRIPTION", "URL", "URL_TO_IMAGE", "PUBLISHED_AT", "CONTENT"},
                    null, null, null, null, null);

            if(cursor.moveToFirst()) {
                String name_column = cursor.getString(0);
                String author_column = cursor.getString(1);
                String title_column = cursor.getString(2);
                String description_column = cursor.getString(3);
                String url_column = cursor.getString(4);
                String url_to_img_column = cursor.getString(5);
                String published_column = cursor.getString(6);
                String content_column = cursor.getString(7);

                TextView name = (TextView) getActivity().findViewById(R.id.tv_name);
                /*
                TextView author = (TextView) getActivity().findViewById(R.id.tv_author);
                TextView title = (TextView) getActivity().findViewById(R.id.tv_title);
                TextView description = (TextView) getActivity().findViewById(R.id.tv_description);
                TextView url = (TextView) getActivity().findViewById(R.id.tv_url);
                TextView url_to_img = (TextView) getActivity().findViewById(R.id.tv_url_to_image);
                TextView published = (TextView) getActivity().findViewById(R.id.tv_published_at);
                TextView content = (TextView) getActivity().findViewById(R.id.tv_content);
                */

                Log.i("LOG", "tv_name -> " + name_column);
                Log.i("LOG", "tv_author-> " + author_column);
                Log.i("LOG", "tv_title -> " + title_column);
                Log.i("LOG", "tv_description -> " + description_column);
                Log.i("LOG", "tv_url -> " + url_column);
                Log.i("LOG", "tv_url_to_image -> " + url_to_img_column);
                Log.i("LOG", "tv_published -> " + published_column);
                Log.i("LOG", "tv_content -> " + content_column);

                /*
                name.setText(name_column);
                author.setText(author_column);
                title.setText(title_column);
                description.setText(description_column);
                url.setText(url_column);
                url_to_img.setText(name_column);
                content.setText(name_column);

                author.setText(author_column);
                title.setText(title_column);
                description.setText(description_column);
                url.setText(url_column);
                url_to_img.setText(url_to_img_column);
                published.setText(published_column);
                content.setText(content_column);
                */
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textNotifications;
        final TextView textView = binding.tvName;
        // notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}