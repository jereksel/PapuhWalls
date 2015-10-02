package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class RrWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_rr_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_rrwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "rr_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_rr;
    }

}
