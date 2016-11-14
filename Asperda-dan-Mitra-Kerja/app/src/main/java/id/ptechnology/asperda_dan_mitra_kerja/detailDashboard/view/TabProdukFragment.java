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

    public TabProdukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_produk, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        setListView();
        return view;
    }

    public void setListView(){

        listDetail = new ArrayList<HashMap<String, String>>();

        adapter = new ListProdukAdapter(getActivity(), listDetail);

        adapter.clear();
        int i=0;
        for (String perusahaan:namaPerusahaan){

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(Constant.KEY_NAMA, perusahaan);

            map.put(Constant.KEY_ALAMAT, alamatPerusahaan[i]);
            map.put(Constant.KEY_RATING,ratingPerusahaan[i]);

            adapter.addItem(map);

            i++;
        }

        listView.setAdapter(adapter);

    }

}
