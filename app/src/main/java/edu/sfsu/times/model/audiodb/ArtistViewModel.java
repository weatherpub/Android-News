package edu.sfsu.times.model.audiodb;

import java.util.ArrayList;

public class ArtistViewModel {
    private static final ArtistViewModel obj = new ArtistViewModel();

    private final ArrayList<ArtistModel> model;

    private ArtistViewModel() {
        model = new ArrayList<>();
    }

    public static ArtistViewModel getInstance() {
        return obj;
    }

    public ArrayList<ArtistModel> getData() {
        return model;
    }
}