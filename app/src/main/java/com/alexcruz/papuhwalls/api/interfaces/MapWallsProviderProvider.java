package com.alexcruz.papuhwalls.api.interfaces;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Pair;

import com.alexcruz.papuhwalls.MainActivity;
import com.alexcruz.papuhwalls.Preferences;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MapWallsProviderProvider implements WallsProviderProvider {

    List<String> categories;
    Map<String, List<WallsProvider>> map;

    List<WallsProvider> combinedCategories = new ArrayList<>();

    int startId;

    public MapWallsProviderProvider(Context context, int startId) {
        Pair<List<String>, Map<String, List<WallsProvider>>> temp = getWallData(context);
        categories = temp.first;
        map = temp.second;

        //Combine lists (we're gonna need this for returnProvider)

        for (String category : categories) {
            combinedCategories.addAll(map.get(category));
        }

        this.startId = startId;

    }

    abstract public Pair<List<String>, Map<String, List<WallsProvider>>> getWallData(Context context);

    @Override
    public List<WallsProvider> getWallsProviders() {
        return null;
    }

    @Override
    public List<IDrawerItem> getDrawerItems(Context context) {

        int i = 0;

        Preferences preferences = new Preferences(context.getApplicationContext());

        List<IDrawerItem> drawerItems = new ArrayList<>();

        for (String category : categories) {

            if (!category.equals("")) {
                drawerItems.add(new SectionDrawerItem().withName(category).withTypeface(Typeface.DEFAULT_BOLD).withTextColor(preferences.DrawerText()));
            }

            for (WallsProvider wallsProvider : map.get(category)) {
                drawerItems.add(new PrimaryDrawerItem().withName(wallsProvider.getName(context)).withIcon(wallsProvider.getIconId()).withIconTintingEnabled(true).withSelectedIconColor(preferences.SelectedIcon()).withIconColor(preferences.NormalIcon()).withSelectedTextColor(MainActivity.tint(preferences.SelectedDrawerText(), 1.0)).withSelectedColor(MainActivity.tint(preferences.DrawerSelector(), 1.0)).withTextColor(preferences.DrawerText()).withIdentifier(i + startId).withBadge(String.valueOf(wallsProvider.getNumberOfWalls())).withBadgeStyle(new BadgeStyle().withTextColor(preferences.BadgeText()).withColor(preferences.BadgeBackground())));
                i++;
            }

        }


        return drawerItems;
    }

    @Override
    public WallsProvider returnProvider(int id) {
        try {
            return combinedCategories.get(id - startId);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
