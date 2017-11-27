package model;

import GetAndImportDBFtoMySQL.IDBFMarkUp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bnkseek")
public class entBnkseekEntity implements IDBFMarkUp {
    private Integer id;
    private String real2;
    private Integer pzn;
    private Integer uer;
    private Integer rgn;
    private String ind;
    private Integer tnp;
    private String nnp;
    private String adr;
    private String rkc;
    private String namep;
    private String newnnum;
    private String telef;
    private String regn;
    private String okpo;
    private Date dtIzm;
    private String ksnp;
    private Date dateIn;
    private Date dateCh;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real2")
    public String getReal2() {
        return real2;
    }

    public void setReal2(String real2) {
        this.real2 = real2;
    }

    @Basic
    @Column(name = "pzn")
    public Integer getPzn() {
        return pzn;
    }

    public void setPzn(Integer pzn) {
        this.pzn = pzn;
    }

    @Basic
    @Column(name = "uer")
    public Integer getUer() {
        return uer;
    }

    public void setUer(Integer uer) {
        this.uer = uer;
    }

    @Basic
    @Column(name = "rgn")
    public Integer getRgn() {
        return rgn;
    }

    public void setRgn(Integer rgn) {
        this.rgn = rgn;
    }

    @Basic
    @Column(name = "ind")
    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    @Basic
    @Column(name = "tnp")
    public Integer getTnp() {
        return tnp;
    }

    public void setTnp(Integer tnp) {
        this.tnp = tnp;
    }

    @Basic
    @Column(name = "nnp")
    public String getNnp() {
        return nnp;
    }

    public void setNnp(String nnp) {
        this.nnp = nnp;
    }

    @Basic
    @Column(name = "adr")
    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    @Basic
    @Column(name = "rkc")
    public String getRkc() {
        return rkc;
    }

    public void setRkc(String rkc) {
        this.rkc = rkc;
    }

    @Basic
    @Column(name = "namep")
    public String getNamep() {
        return namep;
    }

    public void setNamep(String namep) {
        this.namep = namep;
    }

    @Basic
    @Column(name = "newnnum")
    public String getNewnnum() {
        return newnnum;
    }

    public void setNewnnum(String newnnum) {
        this.newnnum = newnnum;
    }

    @Basic
    @Column(name = "telef")
    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    @Basic
    @Column(name = "regn")
    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    @Basic
    @Column(name = "okpo")
    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    @Basic
    @Column(name = "dt_izm")
    @Temporal(TemporalType.DATE)
    public Date getDtIzm() {
        return dtIzm;
    }

    public void setDtIzm(Date dtIzm) {
        this.dtIzm = dtIzm;
    }

    @Basic
    @Column(name = "ksnp")
    public String getKsnp() {
        return ksnp;
    }

    public void setKsnp(String ksnp) {
        this.ksnp = ksnp;
    }

    @Basic
    @Column(name = "date_in")
    @Temporal(TemporalType.DATE)
    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    @Basic
    @Column(name = "date_ch")
    @Temporal(TemporalType.DATE)
    public Date getDateCh() {
        return dateCh;
    }

    public void setDateCh(Date dateCh) {
        this.dateCh = dateCh;
    }


}
