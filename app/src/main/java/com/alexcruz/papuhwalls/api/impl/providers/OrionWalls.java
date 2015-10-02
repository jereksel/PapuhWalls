package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class OrionWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_orion_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_orionwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "orion_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_orion;
    }

}
