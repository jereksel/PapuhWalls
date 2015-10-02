package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class BrokenOsWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_brokenos_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_brokenoswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "brokenos_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_brokenos;
    }

}
