package com.example.lenovo.ennsemble2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LENOVO on 01-04-2016.
 */
public class PagerAdapter1 extends FragmentStatePagerAdapter
{
    int numOfTabs;

    public PagerAdapter1(FragmentManager fm,int numOfTabs)
    {
        super(fm);
        this.numOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                Fragment1 fragment1 = new Fragment1();
                return fragment1;

            case 1:
                Fragment2 fragment2 = new Fragment2();
                return fragment2;


            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
