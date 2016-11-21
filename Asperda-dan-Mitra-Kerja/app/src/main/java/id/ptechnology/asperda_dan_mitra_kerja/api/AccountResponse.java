package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/21/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AccountResponse {

    @SerializedName("stat")
    @Expose
    private String stat;

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

}
