package com.example.navigationdrawerwithactionbar.fragmentactivities;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.navigationdrawerwithactionbar.R;
import com.example.navigationdrawerwithactionbar.fragments.AboutFragment;
import com.example.navigationdrawerwithactionbar.fragments.DrawerLayoutListFragment;
import com.example.navigationdrawerwithactionbar.fragments.VideoListFragment;

public class MainActivity extends SherlockFragmentActivity {
	private DrawerLayout mDrawerLayout;
	private Stack<Fragment> fragmentStack;
	private AboutFragment mAboutFragment;
	private VideoListFragment mVideoListFragment;
	private FrameLayout mFrameHoldingList;
	private DrawerLayoutListFragment mDrawerLayoutListFragment;
	private ActionBar mActionBar;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mFrameHoldingList = (FrameLayout)findViewById(R.id.fragmentList);
		mActionBar = getSupportActionBar();
		mActionBar.setTitle("");
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		
		setDrawerMenu();
		
		fragmentStack = new Stack<Fragment>();
		
		mActionBarDrawerToggle = new ActionBarDrawerToggle(
				((Activity)MainActivity.this), 
				mDrawerLayout,
				R.drawable.ic_launcher,
				R.string.app_name, 
				R.string.app_name){
 
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            	getSupportActionBar().setTitle("");
            	super.onDrawerClosed(view);
            }
 
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            	getSupportActionBar().setTitle(getString(R.string.app_name));
            	super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
	}
	
	private void setDrawerMenu() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();
		mDrawerLayoutListFragment = new DrawerLayoutListFragment();
		ft.replace(R.id.fragmentList, mDrawerLayoutListFragment);
		ft.commit();
	}
	
	public void addToStack(String what) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();
		getSupportActionBar().setTitle(what);

		if(what.equals(getString(R.string.about))) {
			if(fragmentStack.size() > 0) {
				if(fragmentStack.lastElement() instanceof AboutFragment) {
					mAboutFragment = (AboutFragment)fragmentStack.pop();
				}else if(fragmentStack.lastElement() instanceof VideoListFragment) {
					mAboutFragment = new AboutFragment();
					fragmentStack.push(mAboutFragment);
				}
			}else {
				mAboutFragment = new AboutFragment();
				fragmentStack.push(mAboutFragment);
			}
			// Adding a fragment to the fragment transaction
			ft.replace(R.id.content_drawer, mAboutFragment);
		}else if(what.equals(getString(R.string.video_list))){
			if(fragmentStack.size() > 0) {
				if(fragmentStack.lastElement() instanceof AboutFragment) {
					mVideoListFragment = new VideoListFragment();
					fragmentStack.push(mVideoListFragment);
				}else if(fragmentStack.lastElement() instanceof VideoListFragment) {
					mVideoListFragment =(VideoListFragment)fragmentStack.pop();
					fragmentStack.push(mVideoListFragment);
				}
			}else {
				mVideoListFragment = new VideoListFragment();
				fragmentStack.push(mVideoListFragment);
			}
			// Adding a fragment to the fragment transaction
			ft.replace(R.id.content_drawer, mVideoListFragment);
		}
		
		// Committing the transaction
		ft.commit();
		
		if(mDrawerLayout.isDrawerOpen(mFrameHoldingList)) {
			mDrawerLayout.closeDrawer(mFrameHoldingList);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if(mDrawerLayout.isDrawerOpen(mFrameHoldingList)) {
				mDrawerLayout.closeDrawer(mFrameHoldingList);
			}else {
				mDrawerLayout.openDrawer(mFrameHoldingList);
			}
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
