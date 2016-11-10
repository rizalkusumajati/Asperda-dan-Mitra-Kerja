package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.PagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListClickFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout tabDetail,tabProduk,tabLokasi,tabLayout;

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
        tabLayout=(LinearLayout)view.findViewById(R.id.layout_tab);
        tabDetail=(LinearLayout) tabLayout.findViewById(R.id.tab_detail);
        tabProduk=(LinearLayout) tabLayout.findViewById(R.id.tab_produk);
        tabLokasi=(LinearLayout) tabLayout.findViewById(R.id.tab_lokasi);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tabDetail.setBackgroundResource(R.color.colorAccent);
                        tabProduk.setBackgroundResource(R.color.colorPrimaryDark);
                        tabLokasi.setBackgroundResource(R.color.colorPrimaryDark);
                        break;
                    case 1:
                        tabDetail.setBackgroundResource(R.color.colorPrimaryDark);
                        tabProduk.setBackgroundResource(R.color.colorAccent);
                        tabLokasi.setBackgroundResource(R.color.colorPrimaryDark);
                        break;
                    case 2:
                        tabDetail.setBackgroundResource(R.color.colorPrimaryDark);
                        tabProduk.setBackgroundResource(R.color.colorPrimaryDark);
                        tabLokasi.setBackgroundResource(R.color.colorAccent);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupTab();
        return view;
    }

    public void setupTab(){
        tabDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                tabDetail.setBackgroundResource(R.color.colorAccent);
                tabProduk.setBackgroundColor(Color.WHITE);
                tabLokasi.setBackgroundColor(Color.WHITE);
            }
        });

        tabProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                tabDetail.setBackgroundColor(Color.WHITE);
                tabProduk.setBackgroundResource(R.color.colorAccent);
                tabLokasi.setBackgroundColor(Color.WHITE);
            }
        });

        tabLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                tabDetail.setBackgroundColor(Color.WHITE);
                tabProduk.setBackgroundColor(Color.WHITE);
                tabLokasi.setBackgroundResource(R.color.colorAccent);
            }
        });
    }

    public void setViewPager(){
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),getActivity());

        adapter.addFrag(new TabDetailFragment());
        adapter.addFrag(new TabProdukFragment());
        adapter.addFrag(new TabLokasiFragment());

        viewPager.setAdapter(adapter);
    }

}
