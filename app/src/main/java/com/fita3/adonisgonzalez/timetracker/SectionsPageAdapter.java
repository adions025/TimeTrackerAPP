package com.fita3.adonisgonzalez.timetracker;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adonisgonzalez on 21/12/17.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter{

    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> fragmentNameList = new ArrayList<>();

    public void addFragment(Fragment fragment, String name){
        fragmentsList.add(fragment);
        fragmentNameList.add(name);
    }

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentNameList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
