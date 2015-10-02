package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class DuWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_du_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_duwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "du_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_dirtyunicorns;
    }

}
