package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.PagerAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.DetailListClickPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.DetailListClickPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabDetailFragmentView;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListClickFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout tabDetail,tabProduk,tabLokasi,tabLayout;
    private DetailListClickPresenter presenter;

    public DetailListClickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_detail_list_click, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);

        tabLayout=(LinearLayout)view.findViewById(R.id.layout_tab);
        tabDetail=(LinearLayout) tabLayout.findViewById(R.id.tab_detail);
        tabProduk=(LinearLayout) tabLayout.findViewById(R.id.tab_produk);
        tabLokasi=(LinearLayout) tabLayout.findViewById(R.id.tab_lokasi);
//        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt(Constant.KEY_ID_COMPANY);
       // presenter=new DetailListClickPresenterImp();

       // presenter.getCompanyByMember();


//        if (Constant.getCompanyByMember().get(0)==null){
//            Toast.makeText(getActivity(),"NUll member",Toast.LENGTH_SHORT).show();
//        }
        setViewPager();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tabDetail.setBackgroundResource(R.drawable.bg_tab_selected);
                        tabProduk.setBackgroundResource(R.drawable.bg_tab_default);
                        tabLokasi.setBackgroundResource(R.drawable.bg_tab_default);
                        break;
                    case 1:
                        tabDetail.setBackgroundResource(R.drawable.bg_tab_default);
                        tabProduk.setBackgroundResource(R.drawable.bg_tab_selected);
                        tabLokasi.setBackgroundResource(R.drawable.bg_tab_default);
                        break;
                    case 2:
                        tabDetail.setBackgroundResource(R.drawable.bg_tab_default);
                        tabProduk.setBackgroundResource(R.drawable.bg_tab_default);
                        tabLokasi.setBackgroundResource(R.drawable.bg_tab_selected);
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
                tabDetail.setBackgroundResource(R.drawable.bg_tab_selected);
                tabProduk.setBackgroundResource(R.drawable.bg_tab_default);
                tabLokasi.setBackgroundResource(R.drawable.bg_tab_default);
            }
        });

        tabProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                tabDetail.setBackgroundResource(R.drawable.bg_tab_default);
                tabProduk.setBackgroundResource(R.drawable.bg_tab_selected);
                tabLokasi.setBackgroundResource(R.drawable.bg_tab_default);
            }
        });

        tabLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                tabDetail.setBackgroundResource(R.drawable.bg_tab_default);
                tabProduk.setBackgroundResource(R.drawable.bg_tab_default);
                tabLokasi.setBackgroundResource(R.drawable.bg_tab_selected);
            }
        });
    }



    public void setViewPager(){

        if (Constant.getCompanyByMember()==null){
        Toast.makeText(getActivity(),"NUll member",Toast.LENGTH_SHORT).show();
       }
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),getActivity());

        adapter.addFrag(new TabDetailFragment());
        adapter.addFrag(new TabProdukFragment());
        adapter.addFrag(new TabLokasiFragment());

        viewPager.setAdapter(adapter);
    }

}
