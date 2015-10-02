package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class PaWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_pa_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_pawalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "pa_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_pa;
    }


}
