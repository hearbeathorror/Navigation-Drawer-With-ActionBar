package com.example.navigationdrawerwithactionbar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.navigationdrawerwithactionbar.R;
import com.example.navigationdrawerwithactionbar.adapters.DrawerAdapter;
import com.example.navigationdrawerwithactionbar.fragmentactivities.MainActivity;

public class DrawerLayoutListFragment extends Fragment implements OnItemClickListener {
	private ListView mDrawerList;
	private DrawerAdapter mDrawerAdapter;
	private String[] mDrawerMenuItems;
	private View mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_drawer_menu_list, container,false);
		mDrawerList = (ListView)mView.findViewById(R.id.left_drawer);
		return mView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mDrawerMenuItems = getResources().getStringArray(R.array.navigation_items);
		mDrawerAdapter = new DrawerAdapter(getActivity(), 
				R.layout.individual_list_row, mDrawerMenuItems);
		mDrawerList.setAdapter(mDrawerAdapter);
		mDrawerList.setOnItemClickListener(this);
		mDrawerList.setSelection(0);
		
		if(savedInstanceState == null) {
			((MainActivity)getActivity()).addToStack(getString(R.string.about));
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		String menuSelected = mDrawerMenuItems[position];
		((MainActivity)getActivity()).addToStack(menuSelected);
		mDrawerList.setSelection(position);
	}
}
