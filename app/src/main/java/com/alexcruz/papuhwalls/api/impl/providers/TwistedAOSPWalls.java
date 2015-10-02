package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class TwistedAOSPWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_twistedaosp_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_twistedaospwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "twistedaosp_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_twistedaosp;
    }

}
