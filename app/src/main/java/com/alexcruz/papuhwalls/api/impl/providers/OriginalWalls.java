package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;

public class OriginalWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_original_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_originalwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "original_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_original;
    }

}
