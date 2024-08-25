package edu.sfsu.times.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;

import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.ui.home.HomeViewModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    private ArrayList<DataModel> dm;
    /*
         A static variable is shared among all instances of a class

         public class Demo {
             private final String name;
         }

         example: Demo demo1 = new Demo();
         example: Demo demo2 = new Demo();

         demo1 and demo2 share the 'name' instance variable

         Constants in a class are shared by all objects of the class.
         Thus, constants should be declared as final static.

         If you want all instances of a class to share data, use static variables, also known as class variables.
     */

    // 'public static' allows constants to be accessed in other classes
    public static final String DB_NAME = "NEWS";
    public static final String DB_TABLE = "ARTICLES";
    public final int DB_VERSION = 1;
    public final int numberOfObjects = 40;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        Log.i("LOG", "Inside of DatabaseHelper() method -> " + DB_NAME);
        Log.i("LOG", "Inside of DatabaseHelper() method -> " + DB_VERSION);
    }

    /*
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    */

    // Mandatory methods: onCreate(), onUpgrade()
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        dm = new ArrayList<>();

        // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        sqLiteDatabase.execSQL("CREATE TABLE " + DB_TABLE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT,"
                + "AUTHOR TEXT,"
                + "TITLE TEXT,"
                + "DESCRIPTION TEXT,"
                + "URL TEXT,"
                + "URL_TO_IMAGE TEXT,"
                + "PUBLISHED_AT TEXT,"
                + "CONTENT TEXT);");

        insert(sqLiteDatabase, dm);
    }

    private static void insert(SQLiteDatabase sqLiteDatabase, ArrayList<DataModel> model) {
        ContentValues cv = new ContentValues();

        Log.i("LOG", "Inside of insert method 1");
        Log.i("LOG", "Inside of insert method 2" + model.size());

        cv.put("NAME", model.get(0).getName());
        cv.put("AUTHOR", model.get(0).getAuthor());
        cv.put("TITLE", model.get(0).getAuthor());
        cv.put("DESCRIPTION", model.get(0).getDescription());
        cv.put("URL", model.get(0).getUrl());
        cv.put("URL_TO_IMAGE", model.get(0).getUrlToImage());
        cv.put("PUBLISHED_AT", model.get(0).getPublishedAt());
        cv.put("CONTENT", model.get(0).getContent());

        sqLiteDatabase.insert(DB_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}