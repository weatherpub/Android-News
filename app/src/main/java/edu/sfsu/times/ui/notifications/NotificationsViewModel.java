package edu.sfsu.times.ui.notifications;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.sfsu.times.R;
import edu.sfsu.times.sql.DatabaseHelper;

public class NotificationsViewModel extends ViewModel {
    private final Handler handler;

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {

        Log.i("LOG", "NotificationViewModel constructor");

        HandlerThread workerThread = new HandlerThread("WorkerThread");
        workerThread.start();
        handler = new Handler(workerThread.getLooper());

//        new SQLHandlerViewModel().doBackgroundExecution();

        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");

//        workerThread.quitSafely();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /* *
     * Non-static nested classes (inner classes) have access to other members of the enclosing class,
     * even if they are declared private.
     */
    public class SQLHandlerViewModel {
        private void doBackgroundExecution() {
            Log.i("LOG", "SQLHandlerViewModel class...");
                        /*
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("LOG", "doBackgroundExecution() before db conn");

                    SQLiteOpenHelper dbHelper = new DatabaseHelper(context.getApplicationContext());

                    Log.i("LOG", "doBackgroundExecution() after db conn");
                    try {
                        Log.i("LOG", "doBackgroundExecution() after try");

                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        Log.i("LOG", "This is a time consuming operation 4.0");

                        Cursor cursor = db.query("ARTICLES",
                                new String[]{"NAME", "AUTHOR", "TITLE", "DESCRIPTION", "URL", "URL_TO_IMAGE", "PUBLISHED_AT", "CONTENT"},
                                null, null, null, null, null);

                        Log.i("LOG", "doBackgroundExecution() after cursor reference");

                        if (cursor.moveToFirst()) {
                            String name_column = cursor.getString(0);
                            String author_column = cursor.getString(1);
                            String title_column = cursor.getString(2);
                            String description_column = cursor.getString(3);
                            String url_column = cursor.getString(4);
                            String url_to_img_column = cursor.getString(5);
                            String published_column = cursor.getString(6);
                            String content_column = cursor.getString(7);
                            /*
                            TextView name = findViewById(R.id.tv_name);
                            TextView author = (TextView) context.findViewById(R.id.tv_author);
                            TextView title = (TextView) context.findViewById(R.id.tv_title);
                            TextView description = (TextView) context.findViewById(R.id.tv_description);
                            TextView url = (TextView) context.findViewById(R.id.tv_url);
                            TextView url_to_img = (TextView) context.findViewById(R.id.tv_url_to_image);
                            TextView published = (TextView) context.findViewById(R.id.tv_published_at);
                            TextView content = (TextView) context.findViewById(R.id.tv_content);

                            name.setText(name_column);
                            author.setText(author_column);
                            title.setText(title_column);
                            description.setText(description_column);
                            url.setText(url_column);
                            published.setText(published_column);
                            url_to_img.setText(url_to_img_column);
                            content.setText(content_column);

                            Log.i("LOG", "tv_name -> " + name_column);
                            Log.i("LOG", "tv_author -> " + author_column);
                            Log.i("LOG", "tv_title -> " + title_column);
                            Log.i("LOG", "tv_description -> " + description_column);
                            Log.i("LOG", "tv_url -> " + url_column);
                            Log.i("LOG", "tv_url_to_image -> " + url_to_img_column);
                            Log.i("LOG", "tv_published -> " + published_column);
                            Log.i("LOG", "tv_content -> " + content_column);
                        }
                        cursor.close();
                        db.close();
                    } catch(SQLException e) {
                        Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
                        */
        }
    }
}