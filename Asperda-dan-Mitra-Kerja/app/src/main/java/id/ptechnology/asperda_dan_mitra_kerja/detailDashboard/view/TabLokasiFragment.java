package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabLokasiFragmentPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabLokasiFragmentPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabLokasiView;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;

import static id.ptechnology.asperda_dan_mitra_kerja.R.id.map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLokasiFragment extends Fragment implements TabLokasiView{
    private SupportMapFragment f;
    private GoogleMap mMap;
    static  LatLng RAGUNAN;
    private TabLokasiFragmentPresenter presenter;

    public TabLokasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_lokasi, container, false);
        //RAGUNAN = new LatLng(-6.3039, 106.8267);
        presenter=new TabLokasiFragmentPresenterImp(this);
        presenter.getCompanyByMember();

        FragmentManager fm = this.getChildFragmentManager();
        f = ((SupportMapFragment) fm.findFragmentById(map));




        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setMap(String latitiude, String longitude, final String namaPerusahaan) {
        RAGUNAN = new LatLng(Double.parseDouble(latitiude), Double.parseDouble(longitude));

        f.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.clear();


                mMap.addMarker(new MarkerOptions()

                        .position(RAGUNAN)
                        .title(namaPerusahaan));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RAGUNAN, 14));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
            }
        });
    }
}
