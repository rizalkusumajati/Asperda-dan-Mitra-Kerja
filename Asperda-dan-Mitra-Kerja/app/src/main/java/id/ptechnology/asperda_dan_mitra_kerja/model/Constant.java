package id.ptechnology.asperda_dan_mitra_kerja.model;

import android.location.Location;

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
    public static final String KEY_JARAK="jarak";



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

    public static String emailFacebook;
    public static String namaFacebook;
    public static boolean emailFacebookFound;

    public static Location myLokasi;





    public static final String KEY_ID_COMPANY="id_company";
    public static List<CompanyResponse> companyByMember;
    public static List<ProdukResponse>produkBymember;

    public static GoogleApiClient mGoogleApiClient;

    public static Location getMyLokasi() {
        return myLokasi;
    }

    public static void setMyLokasi(Location myLokasi) {
        Constant.myLokasi = myLokasi;
    }

    public static String getEmailFacebook() {
        return emailFacebook;
    }

    public static void setEmailFacebook(String emailFacebook) {
        Constant.emailFacebook = emailFacebook;
    }

    public static String getNamaFacebook() {
        return namaFacebook;
    }

    public static void setNamaFacebook(String namaFacebook) {
        Constant.namaFacebook = namaFacebook;
    }

    public static boolean isEmailFacebookFound() {
        return emailFacebookFound;
    }

    public static void setEmailFacebookFound(boolean emailFacebookFound) {
        Constant.emailFacebookFound = emailFacebookFound;
    }

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
