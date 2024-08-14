package edu.sfsu.times.model;

import java.util.ArrayList;

public class DataModelViewModel {
    private static final DataModelViewModel obj = new DataModelViewModel();

    private final ArrayList<DataModel> dataModel;

    private DataModelViewModel() {
        dataModel = new ArrayList<>();
    }

    public static DataModelViewModel getInstance() {
        return obj;
    }

    public ArrayList<DataModel> getData() {
        return dataModel;
    }
}