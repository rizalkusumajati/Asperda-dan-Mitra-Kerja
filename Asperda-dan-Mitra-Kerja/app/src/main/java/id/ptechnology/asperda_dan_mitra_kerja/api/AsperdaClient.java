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

    @GET("api/amember/getMember")
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



}
