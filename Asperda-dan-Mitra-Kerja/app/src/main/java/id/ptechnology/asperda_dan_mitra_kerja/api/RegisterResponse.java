package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/11/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("stat")
    @Expose
    private String stat;
    @SerializedName("new_id_member")
    @Expose
    private Integer newIdMember;
    @SerializedName("new_id_user")
    @Expose
    private Integer newIdUser;

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
     * The newIdMember
     */
    public Integer getNewIdMember() {
        return newIdMember;
    }

    /**
     *
     * @param newIdMember
     * The new_id_member
     */
    public void setNewIdMember(Integer newIdMember) {
        this.newIdMember = newIdMember;
    }

    /**
     *
     * @return
     * The newIdUser
     */
    public Integer getNewIdUser() {
        return newIdUser;
    }

    /**
     *
     * @param newIdUser
     * The new_id_user
     */
    public void setNewIdUser(Integer newIdUser) {
        this.newIdUser = newIdUser;
    }

}

