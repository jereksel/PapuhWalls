package com.alexcruz.papuhwalls.api.impl.providers;

import com.alexcruz.papuhwalls.R;

public class BlurWalls extends JsonWallsProvider {

    @Override
    public int getTitleId() {
        return R.string.section_blur_walls;
    }

    @Override
    public int getUrlId() {
        return R.string.json_blurwalls_url;
    }

    @Override
    public String getJsonArrayName() {
        return "blur_walls";
    }

    @Override
    public int getIconId() {
        return R.drawable.ic_blur;
    }

}
