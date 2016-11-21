package id.ptechnology.asperda_dan_mitra_kerja.api;

/**
 * Created by macmini2 on 11/11/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MemberResponse {

    @SerializedName("id_member")
    @Expose
    private String idMember;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_typemember")
    @Expose
    private Object idTypemember;
    @SerializedName("noanggota_member")
    @Expose
    private Object noanggotaMember;
    @SerializedName("tgldaftar_member")
    @Expose
    private String tgldaftarMember;
    @SerializedName("nama_member")
    @Expose
    private String namaMember;
    @SerializedName("alamat_member")
    @Expose
    private String alamatMember;
    @SerializedName("tmplahir_member")
    @Expose
    private String tmplahirMember;
    @SerializedName("tgllahir_member")
    @Expose
    private String tgllahirMember;
    @SerializedName("tlp1_member")
    @Expose
    private String tlp1Member;
    @SerializedName("tlp2_member")
    @Expose
    private String tlp2Member;
    @SerializedName("email_member")
    @Expose
    private String emailMember;
    @SerializedName("pic_member")
    @Expose
    private Object picMember;
    @SerializedName("status_member")
    @Expose
    private String statusMember;
    @SerializedName("kode_typemember")
    @Expose
    private Object kodeTypemember;
    @SerializedName("nama_typemember")
    @Expose
    private Object namaTypemember;
    @SerializedName("ket_typemember")
    @Expose
    private Object ketTypemember;

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
     * The idTypemember
     */
    public Object getIdTypemember() {
        return idTypemember;
    }

    /**
     *
     * @param idTypemember
     * The id_typemember
     */
    public void setIdTypemember(Object idTypemember) {
        this.idTypemember = idTypemember;
    }

    /**
     *
     * @return
     * The noanggotaMember
     */
    public Object getNoanggotaMember() {
        return noanggotaMember;
    }

    /**
     *
     * @param noanggotaMember
     * The noanggota_member
     */
    public void setNoanggotaMember(Object noanggotaMember) {
        this.noanggotaMember = noanggotaMember;
    }

    /**
     *
     * @return
     * The tgldaftarMember
     */
    public String getTgldaftarMember() {
        return tgldaftarMember;
    }

    /**
     *
     * @param tgldaftarMember
     * The tgldaftar_member
     */
    public void setTgldaftarMember(String tgldaftarMember) {
        this.tgldaftarMember = tgldaftarMember;
    }

    /**
     *
     * @return
     * The namaMember
     */
    public String getNamaMember() {
        return namaMember;
    }

    /**
     *
     * @param namaMember
     * The nama_member
     */
    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    /**
     *
     * @return
     * The alamatMember
     */
    public String getAlamatMember() {
        return alamatMember;
    }

    /**
     *
     * @param alamatMember
     * The alamat_member
     */
    public void setAlamatMember(String alamatMember) {
        this.alamatMember = alamatMember;
    }

    /**
     *
     * @return
     * The tmplahirMember
     */
    public String getTmplahirMember() {
        return tmplahirMember;
    }

    /**
     *
     * @param tmplahirMember
     * The tmplahir_member
     */
    public void setTmplahirMember(String tmplahirMember) {
        this.tmplahirMember = tmplahirMember;
    }

    /**
     *
     * @return
     * The tgllahirMember
     */
    public String getTgllahirMember() {
        return tgllahirMember;
    }

    /**
     *
     * @param tgllahirMember
     * The tgllahir_member
     */
    public void setTgllahirMember(String tgllahirMember) {
        this.tgllahirMember = tgllahirMember;
    }

    /**
     *
     * @return
     * The tlp1Member
     */
    public String getTlp1Member() {
        return tlp1Member;
    }

    /**
     *
     * @param tlp1Member
     * The tlp1_member
     */
    public void setTlp1Member(String tlp1Member) {
        this.tlp1Member = tlp1Member;
    }

    /**
     *
     * @return
     * The tlp2Member
     */
    public String getTlp2Member() {
        return tlp2Member;
    }

    /**
     *
     * @param tlp2Member
     * The tlp2_member
     */
    public void setTlp2Member(String tlp2Member) {
        this.tlp2Member = tlp2Member;
    }

    /**
     *
     * @return
     * The emailMember
     */
    public String getEmailMember() {
        return emailMember;
    }

    /**
     *
     * @param emailMember
     * The email_member
     */
    public void setEmailMember(String emailMember) {
        this.emailMember = emailMember;
    }

    /**
     *
     * @return
     * The picMember
     */
    public Object getPicMember() {
        return picMember;
    }

    /**
     *
     * @param picMember
     * The pic_member
     */
    public void setPicMember(Object picMember) {
        this.picMember = picMember;
    }

    /**
     *
     * @return
     * The statusMember
     */
    public String getStatusMember() {
        return statusMember;
    }

    /**
     *
     * @param statusMember
     * The status_member
     */
    public void setStatusMember(String statusMember) {
        this.statusMember = statusMember;
    }

    /**
     *
     * @return
     * The kodeTypemember
     */
    public Object getKodeTypemember() {
        return kodeTypemember;
    }

    /**
     *
     * @param kodeTypemember
     * The kode_typemember
     */
    public void setKodeTypemember(Object kodeTypemember) {
        this.kodeTypemember = kodeTypemember;
    }

    /**
     *
     * @return
     * The namaTypemember
     */
    public Object getNamaTypemember() {
        return namaTypemember;
    }

    /**
     *
     * @param namaTypemember
     * The nama_typemember
     */
    public void setNamaTypemember(Object namaTypemember) {
        this.namaTypemember = namaTypemember;
    }

    /**
     *
     * @return
     * The ketTypemember
     */
    public Object getKetTypemember() {
        return ketTypemember;
    }

    /**
     *
     * @param ketTypemember
     * The ket_typemember
     */
    public void setKetTypemember(Object ketTypemember) {
        this.ketTypemember = ketTypemember;
    }

}


