package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class EosWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_eos_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_eoswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "eos_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_eos;
    }
    
}
