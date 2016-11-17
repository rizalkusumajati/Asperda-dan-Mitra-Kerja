package id.ptechnology.asperda_dan_mitra_kerja.dashboard.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements DashboardView,LocationListener {
    private ViewPager viewPager;
    private ListView listView,listViewMitra;
    private ListDashboardAdapter adapter, adapterMitra;
    private ArrayList<HashMap<String, String>> listDetail,listDetailMitra;
    private DetailListClickFragment detailListClickFragment;
    private ProgressDialog progressDialog;
    private DashboardPresenter presenter;
    private LocationManager manager;


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
        progressDialog.setMessage("Loading, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        listView=(ListView)view.findViewById(R.id.listViewAnggota);
        listViewMitra=(ListView)view.findViewById(R.id.listViewMitra);

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            manager.requestLocationUpdates(manager.GPS_PROVIDER, 0, 300, this);

        }


        presenter.setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,getActivity(),progressDialog);

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
        Log.i("onChange","Location change in Dashboard Fragment");
        presenter.setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,((MainActivity)getActivity()),progressDialog);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
