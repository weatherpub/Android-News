package edu.sfsu.times.model.audiodb;

import java.util.ArrayList;

public class AlbumViewModel {
    private static final AlbumViewModel obj = new AlbumViewModel();

    private final ArrayList<AlbumModel> albumModel;

    private AlbumViewModel() {
        albumModel = new ArrayList<>();
    }

    public static AlbumViewModel getInstance() {
        return obj;
    }

    public ArrayList<AlbumModel> getData() {
        return albumModel;
    }
}