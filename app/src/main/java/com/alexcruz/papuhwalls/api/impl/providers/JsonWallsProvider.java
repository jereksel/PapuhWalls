package com.alexcruz.papuhwalls.api.impl.providers;

import android.content.Context;
import android.net.Uri;

import com.alexcruz.papuhwalls.JSONParser;
import com.alexcruz.papuhwalls.api.Wall;
import com.alexcruz.papuhwalls.api.interfaces.WallsProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonWallsProvider implements WallsProvider {

    public abstract int getTitleId();

    public abstract int getUrlId();

    public abstract String getJsonArrayName();

    private String jsonArrayString;

    @Override
    public String getName(Context context) {
        return context.getResources().getString(getTitleId());
    }

    @Override
    public List<Wall> getWalls(Context context) {

        try {

            List<Wall> walls = new ArrayList<>();

            JSONArray jsonarray = getjsonArray(context);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject json = jsonarray.getJSONObject(i);

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
    public int getNumberOfWalls(Context context) {
        return getjsonArray(context).length();
        //return -1;
    }

    private JSONArray getjsonArray(Context context) {

        if (jsonArrayString == null) {
            synchronized (this) {
                if (jsonArrayString == null) {
                    JSONObject json = JSONParser.getJSONfromURL(context.getResources().getString(getUrlId()));
                    try {
                        jsonArrayString = json.getJSONArray(getJsonArrayName()).toString();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        try {
            return new JSONArray(jsonArrayString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}


