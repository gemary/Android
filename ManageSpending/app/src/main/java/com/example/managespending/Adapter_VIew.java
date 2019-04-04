package com.example.managespending;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter_VIew extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> IntegerList = new ArrayList();




    public void   addFragment (Fragment fragment , String Image)
    {
        fragmentList.add(fragment);
        IntegerList.add(Image);

    }

    public Adapter_VIew(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return IntegerList.get(position);
    }
}
