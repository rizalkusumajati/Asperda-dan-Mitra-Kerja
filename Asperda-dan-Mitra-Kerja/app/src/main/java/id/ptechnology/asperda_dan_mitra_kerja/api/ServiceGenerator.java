package id.ptechnology.asperda_dan_mitra_kerja.api;

import android.util.Base64;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macmini2 on 11/11/16.
 */

public class ServiceGenerator {
    private AsperdaClient serviceClass;
    private String username="admin";
    private String password="1234";

    public static final String API_BASE_URL = "http://www.cekkendaraan.com/asperda_app/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public ServiceGenerator(){
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = builder.client(client).build();
        this.serviceClass=retrofit.create(AsperdaClient.class);
    }


    public void getMember(Callback <List<MemberResponse>> callback){
        Call<List<MemberResponse>> call=serviceClass.getMember();
        call.enqueue(callback);

    }

    public void loginApi(String username,String password,Callback<LoginResponse>callback){
        Call<LoginResponse> call=serviceClass.getLoginResponse(username,password);
        call.enqueue(callback);
    }

    public void registerApi(String email,String password,String nama_member,String alamat_member,String tmplahir_member,
                            String tgllahir_member,String tlp_member,String handphone_member,String kota_member,String nama_company,
                            String pic_member, Callback<RegisterResponse>callback){

        Call<RegisterResponse>call=serviceClass.getRegisterResponse(email,password,nama_member,alamat_member,tmplahir_member,
                                                                    tgllahir_member,tlp_member,handphone_member,kota_member,nama_company,
                                                                    pic_member);
        call.enqueue(callback);

    }

    public void getMemberId(String path, Callback<List<MemberResponse>> callback){
        Call<List<MemberResponse>>call=serviceClass.getMemberId(path);
        call.enqueue(callback);
    }

    public void getCompany(Callback <List<CompanyResponse>> callback){
        Call<List<CompanyResponse>> call=serviceClass.getCompany();
        call.enqueue(callback);
    }

    public void getMitra(Callback <List<CompanyResponse>> callback){
        Call<List<CompanyResponse>> call=serviceClass.getMitra();
        call.enqueue(callback);
    }

    public void getCompanyByMember(String id,Callback <List<CompanyResponse>> callback){
        Call<List<CompanyResponse>> call=serviceClass.getCompanyByMember(id);
        call.enqueue(callback);
    }

}
