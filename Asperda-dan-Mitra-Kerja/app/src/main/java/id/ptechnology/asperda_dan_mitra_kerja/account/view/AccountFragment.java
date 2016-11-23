package id.ptechnology.asperda_dan_mitra_kerja.account.view;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.account.presenter.AccountFragmentPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.account.presenter.AccountFragmentPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.account.presenter.AccountFragmentView;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountFragmentView{
    private TextInputEditText etNamaPerusahaan,etAlamat,etKodePos,etNoTelp1,etNoTelp2,etNoFax,etEmail,etWeb,etTahun,etJumlahKendaraan,etAsosiasi,etContact,
            et_contactPhone;
    private Spinner spinnerKota,spinnerPerusahaan;
    private String[] arraySpinner,arrayJenis;
    private Button btnSimpan;
    private AccountFragmentPresenter presenter;
    private ArrayList<String> kota=new ArrayList<>();
    private ArrayList<String> bentukCompany=new ArrayList<>();


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_account, container, false);

        etNamaPerusahaan=(TextInputEditText)view.findViewById(R.id.et_namaPerusahaan);
        etAlamat=(TextInputEditText)view.findViewById(R.id.et_alamat);
        etKodePos=(TextInputEditText)view.findViewById(R.id.et_kodePos);
        etNoTelp1=(TextInputEditText)view.findViewById(R.id.et_noTelp1);
        etNoTelp2=(TextInputEditText)view.findViewById(R.id.et_noTelp2);
        etNoFax=(TextInputEditText)view.findViewById(R.id.et_noFax);
        etEmail=(TextInputEditText)view.findViewById(R.id.et_email);
        etWeb=(TextInputEditText)view.findViewById(R.id.et_web);
        etTahun=(TextInputEditText)view.findViewById(R.id.et_tahun);
        etJumlahKendaraan=(TextInputEditText)view.findViewById(R.id.et_jumlahKendaraan);
        etAsosiasi=(TextInputEditText)view.findViewById(R.id.et_asosiasi);
        etContact=(TextInputEditText)view.findViewById(R.id.et_contact);
        et_contactPhone=(TextInputEditText)view.findViewById(R.id.et_contactPhone);

        spinnerKota=(Spinner)view.findViewById(R.id.spinnerKota);
        spinnerPerusahaan=(Spinner)view.findViewById(R.id.spinnerPerusahaan);

        btnSimpan=(Button)view.findViewById(R.id.btn_simpan);
        Log.i("login",PrefHelper.getString(PrefKey.PREF_LOGIN_ID));

        if (PrefHelper.getBoolean(PrefKey.PREF_LOGIN)){
           // btnSimpan.setVisibility(View.VISIBLE);
            btnSimpan.setEnabled(true);
            btnSimpan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        }
        else {
          //  btnSimpan.setVisibility(View.INVISIBLE);
            btnSimpan.setEnabled(false);
            btnSimpan.setBackgroundColor(getResources().getColor(R.color.grey_default));
            showToast("Silahkan login terlebih dahulu");
        }

        presenter=new AccountFragmentPresenterImp(this);
       // kota.add("Kota");
       // bentukCompany.add("Bentuk Perusahaan");
        if (Constant.getKotaResponses()==null) {
            presenter.getKota(kota,spinnerKota,getActivity());
        }
        else {
            kota.clear();
            presenter.setKota(kota,spinnerKota,getActivity());
        }

        if (Constant.getBentukCompanyResponses()==null) {
            presenter.getBentukCompany(bentukCompany,spinnerPerusahaan,getActivity());
        }
        else {
            bentukCompany.clear();
            presenter.setBentukCompany(bentukCompany,spinnerPerusahaan,getActivity());
        }


//        this.arraySpinner = new String[] {
//                "Kota 1", "Kota 2", "Kota 3", "Kota 4", "Kota 5"
//        };
//
//        this.arrayJenis = new String[] {
//                "CV", "PT"
//        };





        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.validate(etNamaPerusahaan.getText().toString(),etAlamat.getText().toString(),etKodePos.getText().toString(),etNoTelp1.getText().toString(),etEmail.getText().toString(),etTahun.getText().toString(),etContact.getText().toString(),et_contactPhone.getText().toString())){

                    //do action here
                    presenter.updateCompany(PrefHelper.getString(PrefKey.PREF_LOGIN_ID_USER),PrefHelper.getString(PrefKey.PREF_LOGIN_ID),
                            etNamaPerusahaan.getText().toString(),null,spinnerPerusahaan.getSelectedItem().toString(),etAlamat.getText().toString(),
                            spinnerKota.getSelectedItem().toString(),etKodePos.getText().toString(),etNoTelp1.getText().toString(),
                            etNoTelp2.getText().toString(),etEmail.getText().toString(),etWeb.getText().toString(),etTahun.getText().toString(),
                            null,null,null,"0","0",null,null);

                }
            }
        });


        return view;
    }

    @Override
    public void showNamaPerusahaanError() {
        etNamaPerusahaan.setError(getString(R.string.error_field_required));
        etNamaPerusahaan.requestFocus();
    }

    @Override
    public void showAlamatPerusahaanError() {
        etAlamat.setError(getString(R.string.error_field_required));
        etAlamat.requestFocus();
    }

    @Override
    public void showKodePosPerusahaanError() {
        etKodePos.setError(getString(R.string.error_field_required));
        etKodePos.requestFocus();
    }

    @Override
    public void showNomorTelephone1PerusahaanError() {
        etNoTelp1.setError(getString(R.string.error_field_required));
        etNoTelp1.requestFocus();
    }

    @Override
    public void showEmailPerusahaanError() {
        etEmail.setError(getString(R.string.error_field_required));
        etEmail.requestFocus();
    }

    @Override
    public void showTahunPerusahaanError() {
        etTahun.setError(getString(R.string.error_field_required));
        etTahun.requestFocus();
    }

    @Override
    public void showContactPerusahaanError() {
        etContact.setError(getString(R.string.error_field_required));
        etContact.requestFocus();
    }

    @Override
    public void showContactPerusahaan2Error() {
        et_contactPhone.setError(getString(R.string.error_field_required));
        et_contactPhone.requestFocus();
    }

    @Override
    public void showValidationEmailError() {
        etEmail.setError(getString(R.string.error_invalid_email));
        etEmail.requestFocus();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
