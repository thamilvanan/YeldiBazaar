package com.yeldi.yeldibazaar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferenceChangeListener;

/**
 * Handles shared preferences for FDroid, looking after the names of
 * preferences, default values and caching. Needs to be setup in the FDroidApp
 * (using {@link Preferences#setup(android.content.Context)} before it gets
 * accessed via the {@link com.yeldi.yeldibazaar.Preferences#get()}
 * singleton method.
 */
public class Preferences implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final SharedPreferences preferences;

    private Preferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    private static final String PREF_COMPACT_LAYOUT    = "compactlayout";
    private static final boolean DEFAULT_COMPACT_LAYOUT = false;

    private boolean compactLayout = DEFAULT_COMPACT_LAYOUT;

    private Map<String,Boolean> initialized = new HashMap<String,Boolean>();
    private List<ChangeListener> compactLayoutListeners = new ArrayList<ChangeListener>();

    private boolean isInitialized(String key) {
        return initialized.containsKey(key) && initialized.get(key);
    }

    private void initialize(String key) {
        initialized.put(key, true);
    }

    private void uninitialize(String key) {
        initialized.put(key, false);
    }

    public boolean hasCompactLayout() {
        if (!isInitialized(PREF_COMPACT_LAYOUT)) {
            initialize(PREF_COMPACT_LAYOUT);
            compactLayout = preferences.getBoolean(PREF_COMPACT_LAYOUT, DEFAULT_COMPACT_LAYOUT);
        }
        return compactLayout;
    }

    public void registerCompactLayoutChangeListener(ChangeListener listener) {
        compactLayoutListeners.add(listener);
    }

    public void unregisterCompactLayoutChangeListener(ChangeListener listener) {
        compactLayoutListeners.remove(listener);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("FDroid", "Invalidating preference '" + key + "'.");
        uninitialize(key);

        if (key.equals(PREF_COMPACT_LAYOUT)) {
            for ( ChangeListener listener : compactLayoutListeners )  {
                listener.onPreferenceChange();
            }
        }
    }

    private static Preferences instance;

    public static void setup(Context context) {
        if (instance != null) {
            String error = "Attempted to reinitialize preferences after it " +
                    "has already been initialized in FDroidApp";
            Log.e("FDroid", error);
            throw new RuntimeException(error);
        }
        instance = new Preferences(context);
    }

    public static Preferences get() {
        if (instance == null) {
            String error = "Attempted to access preferences before it " +
                    "has been initialized in FDroidApp";
            Log.e("FDroid", error);
            throw new RuntimeException(error);
        }
        return instance;
    }

    public static interface ChangeListener {
        public void onPreferenceChange();
    }

}
