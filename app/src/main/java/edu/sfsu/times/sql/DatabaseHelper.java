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
        dataModel.add(new DataModel("Business in America", "Patrick Mims", "Winning is Everything.", "A great book about investing and making money.", "amazon.com", "https://media.wired.com/photos/66a56f21bf2909f08a634953/191:100/w_1280,c_limit/Crypto-Bros-Business-2162975355.jpg", "2024", "456 Pages."));
        dataModel.add(new DataModel("Getting More Money", "Danny Glover", "Money is Necessary.", "All about money in today's world", "money.com", "https://i.blogs.es/f8f908/mining-iran/840_560.jpeg", "2222", "something for everyone"));
        dataModel.add(new DataModel("Simple Mind Simple Life", "Benjamin Richards", "Are women necessary?", "It's a man's world.", "feminism.org", "https://heise.cloudimg.io/bound/1200x1200/q85.png-lossy-85.webp-lossy-85.foil1/_www-heise-de_/imgs/18/4/6/5/3/7/2/3/2024-03-12-Bing_Designer-Phishing-3-3840px-13692f75f2fcb4d0.png", "2222", "something for everyone"));
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

        Log.i("LOG", "dm -> " + dm);
        /*
        Log.i("LOG", "dm -> " + dm.get(1).getAuthor());
        Log.i("LOG", "dm -> " + dm.get(2).getAuthor());
         */

        insert(sqLiteDatabase, updateModel(dm)); // <- updateModel() returns the models from the helper method.
    }

    private static int counter = 0;
    public static void insert(SQLiteDatabase sqLiteDatabase, ArrayList<DataModel> model) {
        Log.i("LOG", "inside of insert!");
        ContentValues cv = new ContentValues();

        for(DataModel m : model) {
            cv.put("NAME", m.getName());
            cv.put("AUTHOR", m.getAuthor());
            cv.put("TITLE", m.getTitle());
            cv.put("DESCRIPTION", m.getDescription());
            cv.put("URL", m.getUrl());
            cv.put("URL_TO_IMAGE", m.getUrlToImage());
            cv.put("PUBLISHED_AT", m.getPublishedAt());
            cv.put("CONTENT", m.getContent());
        }
        sqLiteDatabase.insert(DB_TABLE, null, cv);

        Log.i("LOG", "Inside of insert method 1");
        Log.i("LOG", "Inside of insert method 2" + model.size());

        /*
        cv.put("NAME", model.get(counter).getName());
        cv.put("AUTHOR", model.get(counter).getAuthor());
        cv.put("TITLE", model.get(counter).getAuthor());
        cv.put("DESCRIPTION", model.get(counter).getDescription());
        cv.put("URL", model.get(counter).getUrl());
        cv.put("URL_TO_IMAGE", model.get(counter).getUrlToImage());
        cv.put("PUBLISHED_AT", model.get(counter).getPublishedAt());
        cv.put("CONTENT", model.get(counter).getContent());
        counter = counter + 1;
        */

        //sqLiteDatabase.insert(DB_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}