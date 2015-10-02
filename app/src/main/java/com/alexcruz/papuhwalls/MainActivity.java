package com.alexcruz.papuhwalls;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alexcruz.papuhwalls.api.impl.BasicWallsProviderProvider;
import com.alexcruz.papuhwalls.api.interfaces.WallsProvider;
import com.alexcruz.papuhwalls.api.interfaces.WallsProviderProvider;
import com.github.mrengineer13.snackbar.SnackBar;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.File;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;

public class MainActivity extends ActionBarActivity {

    private String Settings;
    private String MuzeiSettings;
    private String AboutApp;

    private Drawer result = null;
    private AccountHeader headerResult = null;
    public int currentItem;
    private Preferences mPrefs;
    private Context context;
    private SharedPreferences prefs;

    private static final int PROFILE_SETTING = 1;
    private final static int REQUEST_READ_STORAGE_PERMISSION = 1;

    private WallsProviderProvider wallsProviderProvider;

    Preferences Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.Preferences = new Preferences(getApplicationContext());

        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(getString(R.string.theme_dark_material_drawer_icons), false)) {
            setTheme(R.style.DarkMaterialDrawerIcons);
        } else {
            // Do absolutely NOTHING
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = this;
        mPrefs = new Preferences(MainActivity.this);
        CustomActivityOnCrash.install(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs.getBoolean(Preferences.IS_FIRST__RUN, true)) {
            startActivity(new Intent(this, Slides.class));
            finish();
        }

        if (Build.VERSION.SDK_INT >= 23 && PermissionChecker
                .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_STORAGE_PERMISSION);
        } else {
            // Do absolutely NOTHING
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            toolbar.setBackgroundColor(Preferences.Theme());
            getWindow().getDecorView().setBackgroundColor(Preferences.Background());
            getWindow().setStatusBarColor(Preferences.Theme());
        }

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            if (Preferences.getNavigationTint()) {
                getWindow().setNavigationBarColor(Preferences.NavBarTheme());
            }
        }

        final String Home = getResources().getString(R.string.section_home);
        Settings = getResources().getString(R.string.settings);
        MuzeiSettings = getResources().getString(R.string.muzei_settings);
        AboutApp = getResources().getString(R.string.section_aboutapp);

        currentItem = 1;

        final IProfile profile = new ProfileDrawerItem().withName("Alex Cruz aka Mazda").withIcon(getResources().getDrawable(R.drawable.alexcruz)).withIdentifier(1);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSelectionFirstLine(getResources().getString(R.string.app_long_name))
                .withSelectionSecondLine(getResources().getString(R.string.app_dev_name))
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        profile
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            if (headerResult.getProfiles() != null) {
                                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]
                                        {getResources().getString(R.string.email_address)});
                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                        getResources().getText(R.string.artsource_name));
                                emailIntent.setType("plain/text");
                                startActivity(Intent.createChooser(emailIntent, "Contact Alex"));
                            } else {
                                headerResult.addProfiles(profile);
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        wallsProviderProvider = new BasicWallsProviderProvider(this, 2);

        List<IDrawerItem> drawerItems = wallsProviderProvider.getDrawerItems(this);

        //Add home on start
        drawerItems.add(0, new PrimaryDrawerItem().withName(Home).withIcon(R.drawable.ic_home).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(1));

        final int settingsId = 1000;
        final int muzeiId = 1001;
        final int aboutId = 1002;

        drawerItems.add(new DividerDrawerItem());
        drawerItems.add(new SecondaryDrawerItem().withName(Settings).withIcon(R.drawable.ic_settings).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(settingsId));
        drawerItems.add(new SecondaryDrawerItem().withName(MuzeiSettings).withIcon(R.drawable.ic_muzei).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(muzeiId));
        drawerItems.add(new SecondaryDrawerItem().withName(AboutApp).withIcon(R.drawable.ic_about).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(aboutId));


        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withHeader(R.layout.header)
                .withSavedInstance(savedInstanceState)
                .withFooterDivider(true)
                .withSliderBackgroundColor(Preferences.Drawer())
                .withStatusBarColor(tint(Preferences.Theme(), 0.8))
                .addDrawerItems(
                        drawerItems.toArray(new IDrawerItem[drawerItems.size()])
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                        boolean isMuzeiInstalled = Preferences.isAppInstalled(context, "net.nurik.roman.muzei");

                        if (drawerItem != null) {

                            int currentId = drawerItem.getIdentifier();

                            WallsProvider wallsProvider = wallsProviderProvider.returnProvider(currentId);

                            if (wallsProvider != null) {

                                if (isConnected) {
                                    switchWalls(wallsProvider);
                                } else {
                                    showNotConnectedDialog();
                                }

                            } else if (currentId == settingsId) {
                                Intent SettingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivityForResult(SettingsIntent, 0);
                            } else if (currentId == muzeiId && isMuzeiInstalled) {
                                Intent launchMuzeiIntent = new Intent(MainActivity.this, Settings.class);
                                startActivityForResult(launchMuzeiIntent, 0);
                            } else if (currentId == aboutId) {
                                switchFragment(currentId, AboutApp, "Credits");
                            } else if (currentId == 1) {
                                switchFragment(1, Home, "BaseActivity");
                            }

                        }

                        return false;
                    }
                })
                .build();
        result.setSelection(1, true);
        boolean isMuzeiInstalled = Preferences.isAppInstalled(context, "net.nurik.roman.muzei");
        if (!isMuzeiInstalled) {
            result.removeItem(muzeiId);
        } else {
            // Do absolutely NOTHING
        }
        if (android.os.Build.VERSION.SDK_INT < 21) {
            result.removeItem(settingsId);
        } else {
            // Do absolutely NOTHING
        }
    }

    public static int tint(int color, double factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a, Math.max((int) (r * factor), 0), Math.max((int) (g * factor), 0), Math.max((int) (b * factor), 0));
    }

    void switchWalls(WallsProvider wallsProvider) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("provider", wallsProvider);

        currentItem = -1;
        getSupportActionBar().setTitle(wallsProvider.getName(this));
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, "com.alexcruz.papuhwalls.WallsListFragment", bundle));
        tx.commit();
    }

    void switchFragment(int itemId, String title, String fragment) {
        currentItem = itemId;
        getSupportActionBar().setTitle(title);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, "com.alexcruz.papuhwalls." + fragment));
        tx.commit();
    }

    public void FloatingActionButton(final View view) {
        final String name = "Floating Action Button";
        final String url = "https://goo.gl/sGwRWj";
        final String copyright = "Copyright 2015 Dmytro Tarianyk";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialDialogs(final View view) {
        final String name = "Material Dialogs";
        final String url = "https://goo.gl/IGTokc";
        final String copyright = "Copyright (c) 2015 Aidan Michael Follestad";
        final License license = new MITLicense();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialDrawer(final View view) {
        final String name = "Material Drawer";
        final String url = "https://goo.gl/dD26uE";
        final String copyright = "Copyright 2015 Mike Penz";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Picasso(final View view) {
        final String name = "Picasso";
        final String url = "https://goo.gl/aKSVaH";
        final String copyright = "Copyright 2013 Square, Inc.";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Okhttp(final View view) {
        final String name = "Okhttp";
        final String url = "https://goo.gl/J6JvY3";
        final String copyright = "Copyright 2011 Square, Inc.";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Snackbar(final View view) {
        final String name = "Snackbar";
        final String url = "https://goo.gl/hg7GoU";
        final String copyright = "Copyright 2014 Jon Wimer";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Crash(final View view) {
        final String name = "Custom Activity On Crash";
        final String url = "https://goo.gl/Ym1qXK";
        final String copyright = "Copyright (c) 2014-2015 Eduard Ereza";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void AppIntro(final View view) {
        final String name = "AppIntro";
        final String url = "https://goo.gl/5C5Np8";
        final String copyright = "Copyright 2015 Paolo Rotolo";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialRipple(final View view) {
        final String name = "Material Ripple Layout";
        final String url = "https://goo.gl/BRPpkJ";
        final String copyright = "Copyright 2015 Balys Valentukevicius";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialPreference(final View view) {
        final String name = "Material Preference";
        final String url = "https://goo.gl/ugkiRC";
        final String copyright = "Copyright (c) 2015 Jens Driller";
        final License license = new MITLicense();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void LicensesDialog(final View view) {
        final String name = "Licenses Dialog";
        final String url = "https://goo.gl/AJ0Prh";
        final String copyright = "Copyright 2013 Philip Schiffer";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else if (result != null && currentItem != 1) {
            result.setSelection(1);
        } else if (result != null) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.changelog:
                changelog();
                break;
            case R.id.notify:
                showNotify();
                break;
            case R.id.clearcache:
                clearApplicationData();
                break;
        }
        return true;
    }

    String Notify;

    private void showNotify() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title(R.string.notify)
                .customView(R.layout.notify, wrapInScrollView)
                .positiveText("SUBSCRIBE")
                .negativeText("No thanks")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Notify = getResources().getString(R.string.notify_url);
                        Intent notifyurl = new Intent(Intent.ACTION_VIEW, Uri.parse(Notify));
                        startActivity(notifyurl);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                    }
                })
                .iconRes(R.drawable.ic_notify)
                .show();
    }

    private void changelog() {
        new MaterialDialog.Builder(this)
                .title(R.string.changelog_dialog_title)
                .adapter(new Changelog(this, R.array.fullchangelog), null)
                .positiveText(R.string.ok)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                    }
                })
                .show();
    }

    private void showNotConnectedDialog() {
        new SnackBar.Builder(this)
                .withMessageId(R.string.no_conn_content)
                .withActionMessageId(R.string.ok)
                .withStyle(SnackBar.Style.ALERT)
                .withDuration(SnackBar.MED_SNACK)
                .show();
    }
}
