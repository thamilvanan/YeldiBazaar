/**
 * 
 */
package com.yeldi.yeldibazaar.views.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeldi.bazaar.utils.StaggeredAdapter;
import com.yeldi.bazaar.utils.StaggeredGridView;
import com.yeldi.yeldibazaar.FDroid;
import com.yeldi.yeldibazaar.R;

/**
 * @author :Thamilvanan G
 * @Email :<thamilvanan@yeldi.com>
 * @Date :Aug 29, 2013
 * @Application :fdroid
 * 
 */
public class HomeFragment extends Fragment {
	private StaggeredGridView staggeredGridView;
	private StaggeredAdapter staggeredAdapter;
	private String urls[] = {"http://s16.postimg.org/ruh8nfjxx/etap.jpg",
			"http://www.freeimagehosting.net/t/ei6ty.jpg",
			"http://www.freeimagehosting.net/t/qc8pm.jpg",
			"http://i43.tinypic.com/sgr7r4.jpg",
			"http://www.freeimagehosting.net/t/q5rv1.jpg",
			"http://www.freeimagehosting.net/t/gql8x.jpg",
			"http://www.freeimagehosting.net/t/carbn.jpg",
			"http://www.freeimagehosting.net/t/hs4am.jpg"

	};
	private ArrayList<String> img_url = new ArrayList<String>();
	private FDroid parent;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home, null);
		staggeredGridView = (StaggeredGridView) view
				.findViewById(R.id.staggeredGridView);
		Collections.shuffle(Arrays.asList(urls));
		staggeredAdapter = new StaggeredAdapter(getActivity(), R.id.imageView1,
				urls);
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);

		staggeredGridView.setItemMargin(margin); // set the GridView margin

		staggeredGridView.setPadding(0, 0, 0, 0); // have the margin
		staggeredGridView.setAdapter(staggeredAdapter);
		staggeredAdapter.notifyDataSetChanged();

		return view;
	}

}
