package com.android.consumermoviecatalogue.adapter.tab_layout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.consumermoviecatalogue.R;
import com.android.consumermoviecatalogue.ui.MovieFragment;
import com.android.consumermoviecatalogue.ui.TvShowFragment;

public class MainActivityAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MainActivityAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TvShowFragment();
        } else {
            return new MovieFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_tv_show);
        } else if (position == 1) {
            return mContext.getString(R.string.category_movie);
        }
        return super.getPageTitle(position);
    }

}
