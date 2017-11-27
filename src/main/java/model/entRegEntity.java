package model;

import GetAndImportDBFtoMySQL.IDBFMarkUp;

import javax.persistence.*;

@Entity
@Table(name = "reg")
public class entRegEntity implements IDBFMarkUp {
    private Integer id;
    private String rgn;
    private String name;
    private String center;
    private String namet;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RGN")
    public String getRgn() {
        return rgn;
    }

    public void setRgn(String rgn) {
        this.rgn = rgn;
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
    @Column(name = "CENTER")
    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    @Basic
    @Column(name = "NAMET")
    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public void setupAllREGfields(String rgn, String name, String center, String namet) {
        this.rgn = rgn;
        this.name = name;
        this.center = center;
        this.namet = namet;
    }


}
