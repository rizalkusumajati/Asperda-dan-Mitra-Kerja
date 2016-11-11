package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/11/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("stat")
    @Expose
    private String stat;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_member")
    @Expose
    private String idMember;



    /**
     *
     * @return
     * The stat
     */
    public String getStat() {
        return stat;
    }

    /**
     *
     * @param stat
     * The stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    /**
     *
     * @return
     * The idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     * The id_user
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     * The idMember
     */
    public String getIdMember() {
        return idMember;
    }

    /**
     *
     * @param idMember
     * The id_member
     */
    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

}

