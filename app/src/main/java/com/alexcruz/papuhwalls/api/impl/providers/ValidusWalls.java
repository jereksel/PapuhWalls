package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class ValidusWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_validus_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_validuswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "validus_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_validus;
    }

}
