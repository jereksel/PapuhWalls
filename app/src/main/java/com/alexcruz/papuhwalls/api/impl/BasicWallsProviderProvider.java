package com.alexcruz.papuhwalls.api.impl;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Pair;

import com.alexcruz.papuhwalls.R;
import com.alexcruz.papuhwalls.api.impl.providers.AicpWalls;
import com.alexcruz.papuhwalls.api.impl.providers.AllWalls;
import com.alexcruz.papuhwalls.api.impl.providers.AndroidWalls;
import com.alexcruz.papuhwalls.api.impl.providers.BlurWalls;
import com.alexcruz.papuhwalls.api.impl.providers.BrokenOsWalls;
import com.alexcruz.papuhwalls.api.impl.providers.BwWalls;
import com.alexcruz.papuhwalls.api.impl.providers.CandyWalls;
import com.alexcruz.papuhwalls.api.impl.providers.CloudsWalls;
import com.alexcruz.papuhwalls.api.impl.providers.DuWalls;
import com.alexcruz.papuhwalls.api.impl.providers.EosWalls;
import com.alexcruz.papuhwalls.api.impl.providers.LiquidsmoothWalls;
import com.alexcruz.papuhwalls.api.impl.providers.MiscWalls;
import com.alexcruz.papuhwalls.api.impl.providers.MustachesWalls;
import com.alexcruz.papuhwalls.api.impl.providers.NexusWalls;
import com.alexcruz.papuhwalls.api.impl.providers.OmniWalls;
import com.alexcruz.papuhwalls.api.impl.providers.OriginalWalls;
import com.alexcruz.papuhwalls.api.impl.providers.OrionWalls;
import com.alexcruz.papuhwalls.api.impl.providers.PaWalls;
import com.alexcruz.papuhwalls.api.impl.providers.PacromWalls;
import com.alexcruz.papuhwalls.api.impl.providers.PapuhWalls;
import com.alexcruz.papuhwalls.api.impl.providers.RrWalls;
import com.alexcruz.papuhwalls.api.impl.providers.SlimWalls;
import com.alexcruz.papuhwalls.api.impl.providers.SolidsWalls;
import com.alexcruz.papuhwalls.api.impl.providers.StarsWalls;
import com.alexcruz.papuhwalls.api.impl.providers.TeslaWalls;
import com.alexcruz.papuhwalls.api.impl.providers.TwistedAOSPWalls;
import com.alexcruz.papuhwalls.api.impl.providers.ValidusWalls;
import com.alexcruz.papuhwalls.api.interfaces.MapWallsProviderProvider;
import com.alexcruz.papuhwalls.api.interfaces.WallsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicWallsProviderProvider extends MapWallsProviderProvider {

    public BasicWallsProviderProvider(Context context, int startId) {
        super(context, startId);
    }

    @Override
    public Pair<List<String>, Map<String, List<WallsProvider>>> getWallData(Context context) {

        List<String> categories = new ArrayList<>();
        Map<String, List<WallsProvider>> wallsProviderMap = new ArrayMap<>();

        String customRoms = context.getResources().getString(R.string.section_rom_category);

        categories.add("");
        categories.add(customRoms);

        List<WallsProvider> noCategoryProviders = new ArrayList<>();
        noCategoryProviders.add(new AllWalls());
        noCategoryProviders.add(new AndroidWalls());
        noCategoryProviders.add(new BwWalls());
        noCategoryProviders.add(new BlurWalls());
        noCategoryProviders.add(new CloudsWalls());
        noCategoryProviders.add(new MiscWalls());
        noCategoryProviders.add(new MustachesWalls());
        noCategoryProviders.add(new NexusWalls());
        noCategoryProviders.add(new OriginalWalls());
        noCategoryProviders.add(new PapuhWalls());
        noCategoryProviders.add(new SolidsWalls());
        noCategoryProviders.add(new StarsWalls());

        wallsProviderMap.put("", noCategoryProviders);


        List<WallsProvider> customRomsProviders = new ArrayList<>();
        customRomsProviders.add(new AicpWalls());
        customRomsProviders.add(new BrokenOsWalls());
        customRomsProviders.add(new CandyWalls());
        customRomsProviders.add(new DuWalls());
        customRomsProviders.add(new EosWalls());
        customRomsProviders.add(new LiquidsmoothWalls());
        customRomsProviders.add(new OmniWalls());
        customRomsProviders.add(new OrionWalls());
        customRomsProviders.add(new PaWalls());
        customRomsProviders.add(new PacromWalls());
        customRomsProviders.add(new RrWalls());
        customRomsProviders.add(new SlimWalls());
        customRomsProviders.add(new TeslaWalls());
        customRomsProviders.add(new TwistedAOSPWalls());
        customRomsProviders.add(new ValidusWalls());

        wallsProviderMap.put(customRoms, customRomsProviders);

        return new Pair<>(categories, wallsProviderMap);

    }

}
