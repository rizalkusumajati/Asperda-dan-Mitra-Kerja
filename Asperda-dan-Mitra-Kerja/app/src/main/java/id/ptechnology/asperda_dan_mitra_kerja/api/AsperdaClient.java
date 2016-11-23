package id.ptechnology.asperda_dan_mitra_kerja.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface AsperdaClient {
    @GET("api/amember/getMember")
    Call<List<MemberResponse>> getMember();

    @GET("api/amember/getMemberById")
    Call<List<MemberResponse>> getMemberId(@Query("id_member") String id);

    @FormUrlEncoded
    @POST("api/amember/login")
    Call<LoginResponse> getLoginResponse(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/amember/registerMember")
    Call<RegisterResponse> getRegisterResponse(@Field("email") String email, @Field("password") String password,
                                               @Field("nama_member") String nama_member,@Field("alamat_member") String alamat_member,
                                               @Field("tmplahir_member") String tmplahir_member,@Field("tgllahir_member") String tgllahir_member,
                                               @Field("tlp_member") String tlp_member,@Field("handphone_member") String handphone_member,
                                               @Field("kota_member") String kota_member,@Field("nama_company") String nama_company,
                                               @Field("pic_member") String pic);

    @GET("api/amember/getCompanyByType?type=asperda")
    Call<List<CompanyResponse>> getCompany();

    @GET("api/amember/getCompanyByType?type=mitra")
    Call<List<CompanyResponse>> getMitra();

    @GET("api/amember/getCompanyByMember")
    Call<List<CompanyResponse>> getCompanyByMember(@Query("id_member") String id);

    @GET("api/amember/getProductByMember")
    Call<List<ProdukResponse>> getProdukByMember(@Query("id_member") String id);

    @GET("api/amember/getMemberByEmail")
    Call<List<MemberResponse>> getMemberByEmail(@Query("email") String email);

    @FormUrlEncoded
    @POST("api/amember/updateCompany")
    Call<String> updateCompany(@Field("id_user") String id_user, @Field("id_member") String id_member,
                               @Field("nama_company") String nama_company,@Field("ket_company") String ket_company,
                                               @Field("bentuk_company") String bentuk_company,@Field("alamat_company") String alamat_company,
                                               @Field("kota_company") String kota_company,@Field("kodepos_company") String kodepos_company,
                                               @Field("tlp1_company") String tlp1_company,@Field("tlp2_company") String tlp2_company,
                                               @Field("email_company") String email_company,@Field("website_company") String website_company,
                                               @Field("tahunberdiri_company") String tahunberdiri_company,@Field("npwp_company") String npwp_company,
                                               @Field("pkp_company") String pkp_company,@Field("referensi_company") String referensi_company,
                                               @Field("latitude_company") String latitude_company,@Field("longitude_company") String longitude_company,
                                               @Field("listbidang_company") String listbidang_company,@Field("pic_company") String pic_company);

    @GET("api/amember/getKota")
    Call<List<KotaResponse>> getKota();

    @GET("api/amember/getBentukCompany")
    Call<List<BentukCompanyResponse>> getBentukCompany();


}
