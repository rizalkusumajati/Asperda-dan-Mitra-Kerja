package id.ptechnology.asperda_dan_mitra_kerja.model;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;

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

    public static final String KEY_ID_COMPANY="id_company";
    public static List<CompanyResponse> companyByMember;

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
