package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListProdukAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.RecyclerProduk;
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
    private RecyclerView recyclerView;
    private RecyclerProduk recyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<HashMap<String, String>> dataset;

    public TabProdukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_produk, container, false);

        presenter=new TabProdukFragmentPresenterImp();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);



        recyclerView.setLayoutManager(mLayoutManager);



        mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);


         presenter.setListView(dataset,recyclerAdapter,recyclerView,getActivity());



        return view;
    }




}
