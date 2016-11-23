package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/23/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BentukCompanyResponse {

    @SerializedName("bentuk_company")
    @Expose
    private String bentukCompany;

    /**
     *
     * @return
     * The bentukCompany
     */
    public String getBentukCompany() {
        return bentukCompany;
    }

    /**
     *
     * @param bentukCompany
     * The bentuk_company
     */
    public void setBentukCompany(String bentukCompany) {
        this.bentukCompany = bentukCompany;
    }

}