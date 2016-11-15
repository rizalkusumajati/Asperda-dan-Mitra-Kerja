package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListProdukAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabProdukFragmentPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabProdukFragmentPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabProdukFragment extends Fragment {
    private ListProdukAdapter adapter;
    private ArrayList<HashMap<String, String>> listDetail;
    private ListView listView;
    String[] namaPerusahaan= new String[]{"A","B","C"};
    String[] alamatPerusahaan= new String[]{"D","E","F"};
    String[] ratingPerusahaan= new String[]{"4","3","2"};
    private TabProdukFragmentPresenter presenter;

    public TabProdukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_produk, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        presenter=new TabProdukFragmentPresenterImp();




        presenter.setListView(listDetail,adapter,listView,getActivity());
        return view;
    }



}
