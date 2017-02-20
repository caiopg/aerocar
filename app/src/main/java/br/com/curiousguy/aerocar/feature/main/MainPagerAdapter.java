package br.com.curiousguy.aerocar.feature.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import br.com.curiousguy.aerocar.BaseFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MainPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);

        if(fragments != null) {
            this.fragments = fragments;
        } else {
            throw new NullPointerException("Fragment list is null.");
        }

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
