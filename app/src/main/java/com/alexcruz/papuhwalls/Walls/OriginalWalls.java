package com.alexcruz.papuhwalls.Walls;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alexcruz.papuhwalls.JSONParser;
import com.alexcruz.papuhwalls.R;
import com.alexcruz.papuhwalls.WallsFragment;
import com.alexcruz.papuhwalls.WallsGridAdapter;
import com.github.mrengineer13.snackbar.SnackBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OriginalWalls extends AbsWalls {

    @Override
    public int getTitleId() {
        return R.string.section_original_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_originalwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "original_walls";
    }

}
