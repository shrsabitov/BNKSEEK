package model;

import GetAndImportDBFtoMySQL.IDBFMarkUp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pzn")
public class entPznEntity implements IDBFMarkUp {
    private Integer id;
    private String pzn;
    private String imy;
    private String name;
    private Date cbDate;
    private Date ceDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PZN")
    public String getPzn() {
        return pzn;
    }

    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    @Basic
    @Column(name = "IMY")
    public String getImy() {
        return imy;
    }

    public void setImy(String imy) {
        this.imy = imy;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CB_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCbDate() {
        return cbDate;
    }

    public void setCbDate(Date cbDate) {
        this.cbDate = cbDate;
    }

    @Basic
    @Column(name = "CE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCeDate() {
        return ceDate;
    }

    public void setCeDate(Date ceDate) {
        this.ceDate = ceDate;
    }

    public void setupAllPZNfields(String pzn, String imy, String name, Date cbDate, Date ceDate) {
        setPzn(pzn);
        setImy(imy);
        setName(name);
        setCbDate(cbDate);
        setCeDate(ceDate);
    }


}
