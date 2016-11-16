package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/14/16.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CompanyResponse {

    @SerializedName("id_member")
    @Expose
    private String idMember;
    @SerializedName("nama_company")
    @Expose
    private String namaCompany;
    @SerializedName("ket_company")
    @Expose
    private String ketCompany;
    @SerializedName("bentuk_company")
    @Expose
    private String bentukCompany;
    @SerializedName("alamat_company")
    @Expose
    private String alamatCompany;
    @SerializedName("kotacompany")
    @Expose
    private String kotacompany;
    @SerializedName("kodepos_company")
    @Expose
    private String kodeposCompany;
    @SerializedName("tlp1_company")
    @Expose
    private String tlp1Company;
    @SerializedName("tlp2_company")
    @Expose
    private String tlp2Company;
    @SerializedName("email_company")
    @Expose
    private String emailCompany;
    @SerializedName("website_company")
    @Expose
    private String websiteCompany;
    @SerializedName("pic_company")
    @Expose
    private String picCompany;
    @SerializedName("latitude_company")
    @Expose
    private String latitudeCompany;
    @SerializedName("longitude_company")
    @Expose
    private String longitudeCompany;

    private double jarak;

    public double getJarak() {
        return jarak;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
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

    /**
     *
     * @return
     * The namaCompany
     */
    public String getNamaCompany() {
        return namaCompany;
    }

    /**
     *
     * @param namaCompany
     * The nama_company
     */
    public void setNamaCompany(String namaCompany) {
        this.namaCompany = namaCompany;
    }

    /**
     *
     * @return
     * The ketCompany
     */
    public String getKetCompany() {
        return ketCompany;
    }

    /**
     *
     * @param ketCompany
     * The ket_company
     */
    public void setKetCompany(String ketCompany) {
        this.ketCompany = ketCompany;
    }

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

    /**
     *
     * @return
     * The alamatCompany
     */
    public String getAlamatCompany() {
        return alamatCompany;
    }

    /**
     *
     * @param alamatCompany
     * The alamat_company
     */
    public void setAlamatCompany(String alamatCompany) {
        this.alamatCompany = alamatCompany;
    }

    /**
     *
     * @return
     * The kotacompany
     */
    public String getKotacompany() {
        return kotacompany;
    }

    /**
     *
     * @param kotacompany
     * The kotacompany
     */
    public void setKotacompany(String kotacompany) {
        this.kotacompany = kotacompany;
    }

    /**
     *
     * @return
     * The kodeposCompany
     */
    public String getKodeposCompany() {
        return kodeposCompany;
    }

    /**
     *
     * @param kodeposCompany
     * The kodepos_company
     */
    public void setKodeposCompany(String kodeposCompany) {
        this.kodeposCompany = kodeposCompany;
    }

    /**
     *
     * @return
     * The tlp1Company
     */
    public String getTlp1Company() {
        return tlp1Company;
    }

    /**
     *
     * @param tlp1Company
     * The tlp1_company
     */
    public void setTlp1Company(String tlp1Company) {
        this.tlp1Company = tlp1Company;
    }

    /**
     *
     * @return
     * The tlp2Company
     */
    public String getTlp2Company() {
        return tlp2Company;
    }

    /**
     *
     * @param tlp2Company
     * The tlp2_company
     */
    public void setTlp2Company(String tlp2Company) {
        this.tlp2Company = tlp2Company;
    }

    /**
     *
     * @return
     * The emailCompany
     */
    public String getEmailCompany() {
        return emailCompany;
    }

    /**
     *
     * @param emailCompany
     * The email_company
     */
    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    /**
     *
     * @return
     * The websiteCompany
     */
    public String getWebsiteCompany() {
        return websiteCompany;
    }

    /**
     *
     * @param websiteCompany
     * The website_company
     */
    public void setWebsiteCompany(String websiteCompany) {
        this.websiteCompany = websiteCompany;
    }

    /**
     *
     * @return
     * The picCompany
     */
    public String getPicCompany() {
        return picCompany;
    }

    /**
     *
     * @param picCompany
     * The pic_company
     */
    public void setPicCompany(String picCompany) {
        this.picCompany = picCompany;
    }

    /**
     *
     * @return
     * The latitudeCompany
     */
    public String getLatitudeCompany() {
        return latitudeCompany;
    }

    /**
     *
     * @param latitudeCompany
     * The latitude_company
     */
    public void setLatitudeCompany(String latitudeCompany) {
        this.latitudeCompany = latitudeCompany;
    }

    /**
     *
     * @return
     * The longitudeCompany
     */
    public String getLongitudeCompany() {
        return longitudeCompany;
    }

    /**
     *
     * @param longitudeCompany
     * The longitude_company
     */
    public void setLongitudeCompany(String longitudeCompany) {
        this.longitudeCompany = longitudeCompany;
    }

}




