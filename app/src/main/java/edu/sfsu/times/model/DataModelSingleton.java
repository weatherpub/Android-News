package edu.sfsu.times.model;

import java.util.ArrayList;

public class DataModelSingleton {
    private static final DataModelSingleton obj = new DataModelSingleton();

    private final ArrayList<DataModel> dataModel;

    private DataModelSingleton() {
        dataModel = new ArrayList<>();
    }

    public static DataModelSingleton getInstance() {
        return obj;
    }

    public ArrayList<DataModel> getData() {
        return dataModel;
    }
}