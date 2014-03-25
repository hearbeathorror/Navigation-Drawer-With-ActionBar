package com.example.navigationdrawerwithactionbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.navigationdrawerwithactionbar.R;

public class DrawerAdapter extends ArrayAdapter<String>{
	private Context mContext;
	private int RESOURCE;
	private LayoutInflater mInflater;
	private String[] mMenuItems;
	
	public DrawerAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		mContext = context;
		RESOURCE = resource;
		mMenuItems = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder vh;
		
		if(view == null) {
			mInflater= (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = mInflater.inflate(RESOURCE, parent,false);
			vh = new ViewHolder();
			vh.txtMenuItem = (TextView)view.findViewById(R.id.menuItem);
			view.setTag(vh);
		}else {
			vh = (ViewHolder)view.getTag();
		}
		
		vh.txtMenuItem.setText(mMenuItems[position]);
		return view;
	}
	
	static class ViewHolder {
		private TextView txtMenuItem;
	}
}
