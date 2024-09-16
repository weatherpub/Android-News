package edu.sfsu.times.model.audiodb;

import java.util.ArrayList;

public class TrackViewModel {
    private static final TrackViewModel obj = new TrackViewModel();

    private final ArrayList<TrackModel> model;

    private TrackViewModel() {
        model = new ArrayList<>();
    }

    public static TrackViewModel getInstance() {
        return obj;
    }

    public ArrayList<TrackModel> getData() {
        return model;
    }
}