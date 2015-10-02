package com.alexcruz.papuhwalls.api.interfaces;

import android.content.Context;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

//NOTE: I know how stupid it sounds
public interface WallsProviderProvider {

    List<WallsProvider> getWallsProviders();

    List<IDrawerItem> getDrawerItems(Context context);

    WallsProvider returnProvider(int id);

}
