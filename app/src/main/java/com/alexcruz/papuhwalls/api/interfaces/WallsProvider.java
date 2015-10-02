package com.alexcruz.papuhwalls.api.interfaces;

import android.content.Context;

import com.alexcruz.papuhwalls.api.Wall;

import java.io.Serializable;
import java.util.List;

public interface WallsProvider extends Serializable {

    //Because names are in strings.xml
    String getName(Context context);

    List<Wall> getWalls(Context context);

    int getNumberOfWalls(Context context);

    int getIconId();

}
