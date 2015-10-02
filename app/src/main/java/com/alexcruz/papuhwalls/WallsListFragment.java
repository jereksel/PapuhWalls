package com.alexcruz.papuhwalls;


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

import com.alexcruz.papuhwalls.api.Wall;
import com.alexcruz.papuhwalls.api.interfaces.WallsProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class WallsListFragment extends Fragment {

    private int mColumnCountPortrait = 2;
    GridView mGridView;
    WallsGridAdapter mGridAdapter;
    private ViewGroup root;
    private Context context;
    private int mColumnCount;
    private int numColumns = 2;

    private WallsProvider provider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        provider = (WallsProvider) getArguments().getSerializable("provider");

        ActionBar toolbar = ((ActionBarActivity) context).getSupportActionBar();
        toolbar.setTitle(provider.getName(this.getActivity().getApplicationContext()));

        root = (ViewGroup) inflater.inflate(R.layout.wallpapers, null);

        int newColumnCount = mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }

        new DownloadJSON().execute();
        return root;

    }

    private class DownloadJSON extends AsyncTask<Void, Void, List<Wall>> {

        @Override
        protected List<Wall> doInBackground(Void... params) {
            return provider.getWalls(WallsListFragment.this.getContext());
        }

        @Override
        protected void onPostExecute(final List<Wall> walls) {

            mGridView = (GridView) root.findViewById(R.id.gridView);
            mGridView.setNumColumns(numColumns);
            mGridAdapter = new WallsGridAdapter(context, walls, numColumns);
            mGridView.setAdapter(mGridAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String wallurl = walls.get(position).getLocation().toString();
                    Intent intent = new Intent(context, WallsFragment.class);
                    intent.putExtra("wall", wallurl);
                    context.startActivity(intent);
                }
            });
        }
    }

}
