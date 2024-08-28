package edu.sfsu.times.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.sfsu.times.model.DataModel;
import edu.sfsu.times.ui.home.HomeViewModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    ArrayList<DataModel> dm;
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
    public static final int DB_VERSION = 1;
    public final int numberOfObjects = 40;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.i("LOG", "DB_NAME " + DB_NAME);
        Log.i("LOG", "DB_TABLE " + DB_TABLE);
        Log.i("LOG", "DB_VERSION " + DB_VERSION);
    }

    /*
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    */


    // a helper method to update the  model
    public ArrayList<DataModel> updateModel(ArrayList<DataModel> dataModel) {
        dataModel.add(new DataModel("Business in America", "Patrick Mims", "Winning is Everything", "A great book about investing and making money.", "http://www.amazon.com/business", "https://www.url.com", "2024", "456 Pages."));
        dataModel.add(new DataModel("Michael", "Mills", "Money is Necessary", "All about money in today's world", "http://money.com", "https://www.url.com", "2222", "something for everyone"));
        // dataModel.add...

        return dataModel;
    }

    // Mandatory methods: onCreate(), onUpgrade()
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        dm = new ArrayList<>();

        sqLiteDatabase.execSQL("CREATE TABLE ARTICLES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "AUTHOR TEXT, "
                + "TITLE TEXT, "
                + "DESCRIPTION TEXT, "
                + "URL TEXT, "
                + "URL_TO_IMAGE TEXT, "
                + "PUBLISHED_AT TEXT, "
                + "CONTENT TEXT);");

        insert(sqLiteDatabase, updateModel(dm)); // <- updateModel() returns the models from the helper method.
    }

    private static int counter = 0;
    public static void insert(SQLiteDatabase sqLiteDatabase, ArrayList<DataModel> model) {
        Log.i("LOG", "inside of insert!");
        ContentValues cv = new ContentValues();

        Log.i("LOG", "Inside of insert method 1");
        Log.i("LOG", "Inside of insert method 2" + model.size());

        cv.put("NAME", model.get(counter).getName());
        cv.put("AUTHOR", model.get(counter).getAuthor());
        cv.put("TITLE", model.get(counter).getAuthor());
        cv.put("DESCRIPTION", model.get(counter).getDescription());
        cv.put("URL", model.get(counter).getUrl());
        cv.put("URL_TO_IMAGE", model.get(counter).getUrlToImage());
        cv.put("PUBLISHED_AT", model.get(counter).getPublishedAt());
        cv.put("CONTENT", model.get(counter).getContent());

        counter = counter + 1;

        sqLiteDatabase.insert(DB_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}