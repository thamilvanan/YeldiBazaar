/*
 * Copyright (C) 2010-12  Ciaran Gultnieks, ciaran@ciarang.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.yeldi.yeldibazaar;

import java.io.File;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;
import com.yeldi.yeldibazaar.R;
import com.yeldi.yeldibazaar.compat.ActionBarCompat;

public class PreferencesActivity extends PreferenceActivity implements
        OnPreferenceClickListener {


    private boolean ignoreTouchscreenChanged = false;
    private boolean showIncompatibleChanged = false;
    private boolean lightThemeChanged = false;

    Intent ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("lightTheme", false))
            setTheme(R.style.AppThemeLight);

        super.onCreate(savedInstanceState);
        ActionBarCompat.create(this).setDisplayHomeAsUpEnabled(true);
        addPreferencesFromResource(R.xml.preferences);
        for (String prefkey : new String[] { "ignoreTouchscreen",
                "showIncompatible", "lightTheme" }) {
            Preference pref = findPreference(prefkey);
            pref.setOnPreferenceClickListener(this);
        }
        ret = new Intent();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals("ignoreTouchscreen"))
            ignoreTouchscreenChanged ^= true;
        else if (key.equals("showIncompatible"))
            showIncompatibleChanged ^= true;
        else
            lightThemeChanged ^= true;

        if (lightThemeChanged)
            ret.putExtra("restart", true);
        else if (ignoreTouchscreenChanged || showIncompatibleChanged)
            ret.putExtra("update", true);

        setResult(RESULT_OK, ret);
        return true;
    }

}
