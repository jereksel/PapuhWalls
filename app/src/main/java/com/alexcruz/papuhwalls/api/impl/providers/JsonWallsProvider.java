package com.alexcruz.papuhwalls.api.impl.providers;

import android.content.Context;
import android.net.Uri;

import com.alexcruz.papuhwalls.JSONParser;
import com.alexcruz.papuhwalls.api.Wall;
import com.alexcruz.papuhwalls.api.interfaces.WallsProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonWallsProvider implements WallsProvider {

    public abstract int getTitleId();

    public abstract int getUrlId();

    public abstract String getJsonArrayName();

    @Override
    public String getName(Context context) {
        return context.getResources().getString(getTitleId());
    }

    @Override
    public List<Wall> getWalls(Context context) {

        try {

            List<Wall> walls = new ArrayList<>();

            JSONObject json = JSONParser.getJSONfromURL(context.getResources().getString(getUrlId()));
            JSONArray jsonarray = json.getJSONArray(getJsonArrayName());

            for (int i = 0; i < jsonarray.length(); i++) {
                json = jsonarray.getJSONObject(i);

                String name = json.getString("name");
                String author = json.getString("author");
                Uri location = Uri.parse(json.getString("url"));

                walls.add(new Wall(location, name, author));
            }

            return walls;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getNumberOfWalls() {
        return -1;
    }

}


