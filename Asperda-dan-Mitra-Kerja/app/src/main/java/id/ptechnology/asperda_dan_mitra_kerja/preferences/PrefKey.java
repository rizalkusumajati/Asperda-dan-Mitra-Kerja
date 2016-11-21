package id.ptechnology.asperda_dan_mitra_kerja.preferences;

/**
 * Created by macmini2 on 11/11/16.
 */

public enum PrefKey {

    PREF_LOGIN("isLogin"),
    PREF_NAME("welcome"),
    PREF_LOGIN_NAME("member_name"),
    PREF_LOGIN_ID("member_id"),
    PREF_LOGIN_NAMA_PERUSAHAAN("company_nama"),
    PREF_LOGIN_VIA("jenis"),
    PREF_LOGIN_ID_USER("id_user")
    ;


    private final String value;

    private PrefKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



}
