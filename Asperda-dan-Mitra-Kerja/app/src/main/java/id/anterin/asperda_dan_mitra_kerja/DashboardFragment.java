package id.anterin.asperda_dan_mitra_kerja;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.anterin.asperda_dan_mitra_kerja.adapter.AndroidImageAdapter;
import id.anterin.asperda_dan_mitra_kerja.adapter.ListDashboardAdapter;
import id.anterin.asperda_dan_mitra_kerja.detailDashboard.DetailListClickFragment;
import id.anterin.asperda_dan_mitra_kerja.model.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    private ViewPager viewPager;
    private ListView listView,listViewMitra;
    private ListDashboardAdapter adapter, adapterMitra;
    private ArrayList<HashMap<String, String>> listDetail,listDetailMitra;
    private DetailListClickFragment detailListClickFragment;
    String[] namaPerusahaan= new String[]{"A","B","C"};
    String[] alamatPerusahaan= new String[]{"D","E","F"};
    String[] ratingPerusahaan= new String[]{"4","3","2"};
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPageAndroid);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);

        detailListClickFragment=new DetailListClickFragment();

        listView=(ListView)view.findViewById(R.id.listViewAnggota);
        listViewMitra=(ListView)view.findViewById(R.id.listViewMitra);


        setListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoDetail();
            }
        });
        return view;
    }

    public void setListView(){

        listDetail = new ArrayList<HashMap<String, String>>();
        listDetailMitra = new ArrayList<HashMap<String, String>>();
        adapter = new ListDashboardAdapter(getActivity(), listDetail);
        adapterMitra=new ListDashboardAdapter(getActivity(),listDetailMitra);
        adapterMitra.clear();
        adapter.clear();
       int i=0;
            for (String perusahaan:namaPerusahaan){

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(Constant.KEY_NAMA, perusahaan);

                    map.put(Constant.KEY_ALAMAT, alamatPerusahaan[i]);
                    map.put(Constant.KEY_RATING,ratingPerusahaan[i]);

                    adapter.addItem(map);
                adapterMitra.addItem(map);
               i++;
            }

           listView.setAdapter(adapter);
           listViewMitra.setAdapter(adapterMitra);
           Utility.setListViewHeightBasedOnChildren(listView);
           Utility.setListViewHeightBasedOnChildren(listViewMitra);
        }

    public void gotoDetail(){
        getActivity().getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, detailListClickFragment)
                .addToBackStack(null)
                .commit();
    }


}
