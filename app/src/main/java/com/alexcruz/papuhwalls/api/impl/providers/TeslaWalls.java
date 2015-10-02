package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class TeslaWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_tesla_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_teslawalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "tesla_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_tesla;
    }

}
