package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.DetailListClickPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.DetailListClickPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabDetailFragmentPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabDetailFragmentPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter.TabDetailFragmentView;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDetailFragment extends Fragment implements TabDetailFragmentView {
    private CircleImageView ivLogoPerusahaan;
    private TextView tvNamaPerusahaan,tvAlamatPerusahaan,tvKotaPerusahaan,tvEmailPerusahaan,tvKeteranganPerusahaan;
    private TabDetailFragmentPresenter presenter;
    String logoPerusahaan,namaPerusahaan,  alamatPerusahaan,  kotaPerusahaan, emailPerusahaan,  keteranganPerusahaan;
    private ProgressDialog progressDialog;

    public TabDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_detail, container, false);


        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);




        ivLogoPerusahaan=(CircleImageView) view.findViewById(R.id.iv_logoPerusahaan);
        tvNamaPerusahaan=(TextView)view.findViewById(R.id.tv_namaPerusahaan);
        tvAlamatPerusahaan=(TextView)view.findViewById(R.id.tv_alamatPerusahaan);
        tvKotaPerusahaan=(TextView)view.findViewById(R.id.tv_kotaPerusahaan);
        tvEmailPerusahaan=(TextView)view.findViewById(R.id.tv_emailPerusahaan);
        tvKeteranganPerusahaan=(TextView)view.findViewById(R.id.tv_keteranganPerusahaan);

        presenter=new TabDetailFragmentPresenterImp(this);


        if (Constant.getCompanyByMember()==null)
        presenter.getCompanyByMember(progressDialog);
        else
        setData(Constant.getCompanyByMember().get(0).getPicCompany(),Constant.getCompanyByMember().get(0).getNamaCompany(),Constant.getCompanyByMember().get(0).getAlamatCompany(),Constant.getCompanyByMember().get(0).getKotacompany(),Constant.getCompanyByMember().get(0).getEmailCompany(),Constant.getCompanyByMember().get(0).getKetCompany());






        //Picasso.with(getContext()).load()

        return view;
    }

    @Override
    public void setData(String logoPerusahaan, String namaPerusahaan, String alamatPerusahaan, String kotaPerusahaan, String emailPerusahaan, String keteranganPerusahaan) {
//        this.logoPerusahaan=logoPerusahaan;
//        this.namaPerusahaan=namaPerusahaan;
//        this.alamatPerusahaan=alamatPerusahaan;
//        this.kotaPerusahaan=kotaPerusahaan;
//        this.emailPerusahaan=emailPerusahaan;
//        this.keteranganPerusahaan=keteranganPerusahaan;


        Picasso.with(getActivity()).load(Constant.URL_PIC_COMPANY + logoPerusahaan).into(ivLogoPerusahaan);
        tvNamaPerusahaan.setText(namaPerusahaan);
        tvAlamatPerusahaan.setText(alamatPerusahaan);
        tvKotaPerusahaan.setText(kotaPerusahaan);
        tvEmailPerusahaan.setText(emailPerusahaan);
        tvKeteranganPerusahaan.setText(keteranganPerusahaan);
    }


}
