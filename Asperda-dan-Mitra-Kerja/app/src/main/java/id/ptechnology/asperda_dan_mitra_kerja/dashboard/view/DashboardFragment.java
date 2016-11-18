package id.ptechnology.asperda_dan_mitra_kerja.dashboard.view;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.AndroidImageAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListDashboardAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.dashboard.presenter.DashboardPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.dashboard.presenter.DashboardPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.dashboard.presenter.DashboardView;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view.DetailListClickFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import me.relex.circleindicator.CircleIndicator;

import static id.ptechnology.asperda_dan_mitra_kerja.model.Constant.mGoogleApiClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements DashboardView,GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        com.google.android.gms.location.LocationListener {
    private ViewPager viewPager;
    private ListView listView,listViewMitra;
    private ListDashboardAdapter adapter, adapterMitra;
    private ArrayList<HashMap<String, String>> listDetail,listDetailMitra;
    private DetailListClickFragment detailListClickFragment;
    private ProgressDialog progressDialog;
    private DashboardPresenter presenter;
    private LocationManager manager;
    private String provider;
    LocationRequest mLocationRequest;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPageAndroid);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);
        indicator.setViewPager(mViewPager);
        detailListClickFragment=new DetailListClickFragment();
        presenter=new DashboardPresenterImp(this);
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching your location, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        listView=(ListView)view.findViewById(R.id.listViewAnggota);
        listViewMitra=(ListView)view.findViewById(R.id.listViewMitra);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        provider = manager.getBestProvider(criteria, true);
        progressDialog.show();
       if (Constant.getMyLokasi()!=null) {
            setListViewData();
       }
       else {
            Log.i("LocChange","Dashboard null lokasi");
//           mGoogleApiClient.connect();
//           Constant.getmGoogleApiClient().connect();
//          progressDialog.show();
       }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Constant.setIdCompany(presenter.getIdCompany(i));
                    Constant.setProdukBymember(null);
                    Constant.setCompanyByMember(null);

                    gotoDetail();
                }
            });

        listViewMitra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Constant.setIdCompany(presenter.getIdMitra(i));
                Constant.setProdukBymember(null);
                Constant.setCompanyByMember(null);
                gotoDetail();
            }
        });


        return view;
    }

    public void setListViewData(){

        presenter.setListView(listDetail, listDetailMitra, adapter, adapterMitra, listView, listViewMitra, getActivity(), progressDialog);
    }




    public void gotoDetail(){

//        Bundle bundle = new Bundle();
//        bundle.putInt(Constant.KEY_ID_COMPANY, i);
//        detailListClickFragment.setArguments(bundle);


        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, detailListClickFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        Constant.setMyLokasi(location);
      //  progressDialog.dismiss();
        Log.i("onChange","Location change in Dashboard Fragment");
        presenter.setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,((MainActivity)getActivity()),progressDialog);
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("GoogleApi","dashboard connect");
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); // Update location every second

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
