package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/23/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class KotaResponse {

    @SerializedName("id_kota")
    @Expose
    private String idKota;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    /**
     *
     * @return
     * The idKota
     */
    public String getIdKota() {
        return idKota;
    }

    /**
     *
     * @param idKota
     * The id_kota
     */
    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    /**
     *
     * @return
     * The namaKota
     */
    public String getNamaKota() {
        return namaKota;
    }

    /**
     *
     * @param namaKota
     * The nama_kota
     */
    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

}
