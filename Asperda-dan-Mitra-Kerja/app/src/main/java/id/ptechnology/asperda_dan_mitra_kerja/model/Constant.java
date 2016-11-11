package id.ptechnology.asperda_dan_mitra_kerja.model;

/**
 * Created by macmini2 on 11/9/16.
 */

public class Constant {
    public static final String KEY_NAMA="nama";
    public static final String KEY_ALAMAT="alamat";
    public static final String KEY_RATING="rating";
    public static final String PATH_MEMBER_ID="getMember?id_member=5";

    public static boolean isLogin;

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setLogin(boolean login) {
        isLogin = login;
    }
}
