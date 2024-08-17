package edu.sfsu.times.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
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
    public static final String DB_NAME = "SD";
    public static final int DB_VERSION = 1;
    public static final int numberOfObjects = 40;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
        Log.i("LOG", "Inside of DatabaseHelper() method -> " + DB_NAME);
        Log.i("LOG", "Inside of DatabaseHelper() method -> " + name);

    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Mandatory methods: onCreate(), onUpgrade()
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PROFILE ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BODY_HAIR TEXT, "
                + "BUILD TEXT, "
                + "ETHNICITY TEXT, "
                + "HEIGHT TEXT, "
                + "LOCATION TEXT, "
                + "ROLE TEXT, "
                + "WEIGHT TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}