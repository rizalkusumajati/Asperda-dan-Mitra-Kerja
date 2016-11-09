package id.anterin.asperda_dan_mitra_kerja.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini2 on 11/2/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Context context;

    public PagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);

        notifyDataSetChanged();
    }
    public int getItemPosition(Object obj){


        return POSITION_NONE;
    }
    public void remove(int position){
        mFragmentList.remove(position);

        notifyDataSetChanged();
    }

    public void addOnIndex(Fragment fragment, int position){
        mFragmentList.add(position,fragment);
        notifyDataSetChanged();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
