package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;


public class MustachesWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_mustaches_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_mustacheswalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "mustaches_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_mustaches;
    }
}
