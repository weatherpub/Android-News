package edu.sfsu.times.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;


}