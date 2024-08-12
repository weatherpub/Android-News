package edu.sfsu.times.model;

import java.util.ArrayList;

public class DataModelSingleton {

    private final ArrayList<DataModel> model;

    // note the private constructor
    private DataModelSingleton() {
        model = new ArrayList<>();
    }

    public static DataModelSingleton getInstance() {
        return new DataModelSingleton();
    }

    public ArrayList<DataModel> getData() {
        return model;
    }
}