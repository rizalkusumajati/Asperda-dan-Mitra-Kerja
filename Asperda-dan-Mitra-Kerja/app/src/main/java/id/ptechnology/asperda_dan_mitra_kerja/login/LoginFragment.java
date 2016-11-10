package id.ptechnology.asperda_dan_mitra_kerja.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.RegistrasiFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private Button registrasi;
    private RegistrasiFragment registrasiFragment;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);

        registrasi=(Button)view.findViewById(R.id.register);
        registrasiFragment=new RegistrasiFragment();
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistrasi();
            }
        });

        return view;
    }

    public void gotoRegistrasi(){
        getActivity().getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, registrasiFragment)
                .addToBackStack(null)
                .commit();
    }

}
