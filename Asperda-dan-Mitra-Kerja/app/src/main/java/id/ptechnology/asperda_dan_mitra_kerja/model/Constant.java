package id.ptechnology.asperda_dan_mitra_kerja.model;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ProdukResponse;

/**
 * Created by macmini2 on 11/9/16.
 */

public class Constant {
    public static final String KEY_NAMA="nama";
    public static final String KEY_ALAMAT="alamat";
    public static final String KEY_RATING="rating";
    public static final String PATH_MEMBER_ID="getMember?id_member=5";
    public static final String KEY_PIC_COMPANY="pic_company";
    public static final String URL_PIC_COMPANY="http://www.cekkendaraan.com/asperda_app/assets/uploads/company/";

    //keperluan tab produk
    public static final String KEY_NAMA_MOBIL="namaMobil";
    public static final String KEY_NAMA_KETERANGAN="namaMobilKeterangan";
    public static final String KEY_LOKASI_MOBIL="lokasiMobil";
    public static final String KEY_SOPIR_MOBIL="sopirMobil";
    public static final String KEY_BBM_MOBIL="bbmMobil";
    public static final String KEY_HARGA_MOBIL="hargaMobil";

    //end keperluan tab produk

    public static String emailGoogle;
    public static String namaGoogle;

    public static boolean emailGoogleFound;




    public static final String KEY_ID_COMPANY="id_company";
    public static List<CompanyResponse> companyByMember;
    public static List<ProdukResponse>produkBymember;

    public static GoogleApiClient mGoogleApiClient;


    public static boolean isEmailGoogleFound() {
        return emailGoogleFound;
    }

    public static void setEmailGoogleFound(boolean emailGoogleFound) {
        Constant.emailGoogleFound = emailGoogleFound;
    }

    public static String getNamaGoogle() {
        return namaGoogle;
    }

    public static void setNamaGoogle(String namaGoogle) {
        Constant.namaGoogle = namaGoogle;
    }

    public static String getEmailGoogle() {
        return emailGoogle;
    }

    public static void setEmailGoogle(String emailGoogle) {
        Constant.emailGoogle = emailGoogle;
    }

    public static List<ProdukResponse> getProdukBymember() {
        return produkBymember;
    }

    public static void setProdukBymember(List<ProdukResponse> produkBymember) {
        Constant.produkBymember = produkBymember;
    }

    public static GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public static void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        Constant.mGoogleApiClient = mGoogleApiClient;
    }

    public static List<CompanyResponse> getCompanyByMember() {
        return companyByMember;
    }

    public static void setCompanyByMember(List<CompanyResponse> companyByMember) {
        Constant.companyByMember = companyByMember;
    }

    public static String idCompany;

    public static String getIdCompany() {
        return idCompany;
    }

    public static void setIdCompany(String idCompany) {
        Constant.idCompany = idCompany;
    }

    public static List <CompanyResponse> companyData;

    public static List<CompanyResponse> getMitraData() {
        return mitraData;
    }

    public static void setMitraData(List<CompanyResponse> mitraData) {
        Constant.mitraData = mitraData;
    }

    public static List <CompanyResponse> mitraData;




    public static List<CompanyResponse> getCompanyData() {
        return companyData;
    }

    public static void setCompanyData(List<CompanyResponse> companyData) {
        Constant.companyData = companyData;
    }

    public static void clearCompanyData(){
        companyData.clear();
    }



    public static boolean isLogin;

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setLogin(boolean login) {
        isLogin = login;
    }
}
