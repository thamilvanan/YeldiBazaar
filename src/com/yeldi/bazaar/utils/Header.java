/**
 * 
 */
package com.yeldi.bazaar.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author :Thamilvanan G
 * @Email :<thamilvanan@yeldi.com>
 * @Date :Aug 28, 2013
 * @Application :fdroid
 * 
 */
public class Header extends TextView {
	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Header(Context context) {
		super(context);
		init();
	}

	private void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/Paint_Peel_Initials.ttf");
			setTypeface(tf);
		}
	}

}
