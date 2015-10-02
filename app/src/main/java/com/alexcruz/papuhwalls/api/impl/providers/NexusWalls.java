package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class NexusWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_nexus_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_nexuswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "nexus_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_nexus;
    }

}
