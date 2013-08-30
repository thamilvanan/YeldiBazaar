package com.yeldi.yeldibazaar.views;

import android.content.Context;

public class AvailableAppListAdapter extends AppListAdapter {
    public AvailableAppListAdapter(Context context) {
        super(context);
    }

    @Override
    protected boolean showStatusUpdate() {
        return true;
    }

    @Override
    protected boolean showStatusInstalled() {
        return true;
    }
}
