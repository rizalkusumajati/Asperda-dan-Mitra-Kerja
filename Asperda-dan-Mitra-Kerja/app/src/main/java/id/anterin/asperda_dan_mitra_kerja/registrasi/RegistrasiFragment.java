package id.anterin.asperda_dan_mitra_kerja.registrasi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.anterin.asperda_dan_mitra_kerja.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrasiFragment extends Fragment {


    public RegistrasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrasi, container, false);
    }

}
