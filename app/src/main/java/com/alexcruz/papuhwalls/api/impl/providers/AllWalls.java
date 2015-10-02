package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class AllWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_all_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_allwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "all_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_allwalls;
    }

}
