package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class LiquidsmoothWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_liquidsmooth_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_liquidsmoothwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "liquidsmooth_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_liquidsmooth;
    }

}
