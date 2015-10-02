package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class CloudsWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_clouds_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_cloudswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "clouds_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_clouds;
    }

}
