package com.alexcruz.papuhwalls.Walls;

import com.alexcruz.papuhwalls.R;


public class DuWalls extends AbsWalls {

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

}
