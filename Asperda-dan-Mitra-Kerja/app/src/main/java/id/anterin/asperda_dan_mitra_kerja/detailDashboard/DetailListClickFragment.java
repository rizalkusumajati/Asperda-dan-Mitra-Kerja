package id.anterin.asperda_dan_mitra_kerja.detailDashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.anterin.asperda_dan_mitra_kerja.R;
import id.anterin.asperda_dan_mitra_kerja.adapter.PagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListClickFragment extends Fragment {
    private ViewPager viewPager;

    public DetailListClickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_detail_list_click, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        setViewPager();
        return view;
    }

    public void setViewPager(){
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),getActivity());

        adapter.addFrag(new TabDetailFragment());
        adapter.addFrag(new TabProdukFragment());
        adapter.addFrag(new TabLokasiFragment());

        viewPager.setAdapter(adapter);
    }

}
