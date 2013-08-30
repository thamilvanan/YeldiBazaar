package com.yeldi.yeldibazaar.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.yeldi.yeldibazaar.FDroid;
import com.yeldi.yeldibazaar.R;
import com.yeldi.yeldibazaar.views.fragments.AvailableAppsFragment;
import com.yeldi.yeldibazaar.views.fragments.CanUpdateAppsFragment;
import com.yeldi.yeldibazaar.views.fragments.HomeFragment;
import com.yeldi.yeldibazaar.views.fragments.InstalledAppsFragment;

/**
 * Used by the FDroid activity in conjunction with its ViewPager to support
 * 
 * swiping of tabs for both old devices (< 3.0) and new devices.
 */
public class AppListFragmentPageAdapter extends FragmentPagerAdapter {

	private FDroid parent = null;

	public AppListFragmentPageAdapter(FDroid parent) {
		super(parent.getSupportFragmentManager());
		this.parent = parent;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = null;
		if (i == 0) {
			fragment = new HomeFragment();
		} else if (i == 1) {
			fragment = new AvailableAppsFragment();
		} else if (i == 2) {
			fragment = new InstalledAppsFragment();
		} else if (i == 3) {
			fragment = new CanUpdateAppsFragment();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 4;
	}

	public String getPageTitle(int i) {
		switch (i) {
			case 0 :
				return parent.getString(R.string.tab_home);
			case 1 :
				return parent.getString(R.string.tab_noninstalled);
			case 2 :
				return parent.getString(R.string.tab_installed);
			case 3 :
				String updates = parent.getString(R.string.tab_updates);
				updates += " ("
						+ parent.getManager().getCanUpdateAdapter().getCount()
						+ ")";
				return updates;
			default :
				return "";
		}
	}

}
