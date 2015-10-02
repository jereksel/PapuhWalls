package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class MiscWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_misc_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_miscwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "misc_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_misc;
    }

}
