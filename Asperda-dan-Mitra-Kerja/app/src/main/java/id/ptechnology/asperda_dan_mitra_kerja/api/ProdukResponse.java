package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/15/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProdukResponse {

    @SerializedName("id_member")
    @Expose
    private String idMember;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("nama_product")
    @Expose
    private String namaProduct;
    @SerializedName("ket_product")
    @Expose
    private String ketProduct;
    @SerializedName("harga_product")
    @Expose
    private String hargaProduct;
    @SerializedName("pic_product")
    @Expose
    private Object picProduct;

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

    /**
     *
     * @return
     * The idProduct
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     *
     * @param idProduct
     * The id_product
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     *
     * @return
     * The namaProduct
     */
    public String getNamaProduct() {
        return namaProduct;
    }

    /**
     *
     * @param namaProduct
     * The nama_product
     */
    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    /**
     *
     * @return
     * The ketProduct
     */
    public String getKetProduct() {
        return ketProduct;
    }

    /**
     *
     * @param ketProduct
     * The ket_product
     */
    public void setKetProduct(String ketProduct) {
        this.ketProduct = ketProduct;
    }

    /**
     *
     * @return
     * The hargaProduct
     */
    public String getHargaProduct() {
        return hargaProduct;
    }

    /**
     *
     * @param hargaProduct
     * The harga_product
     */
    public void setHargaProduct(String hargaProduct) {
        this.hargaProduct = hargaProduct;
    }

    /**
     *
     * @return
     * The picProduct
     */
    public Object getPicProduct() {
        return picProduct;
    }

    /**
     *
     * @param picProduct
     * The pic_product
     */
    public void setPicProduct(Object picProduct) {
        this.picProduct = picProduct;
    }

}


