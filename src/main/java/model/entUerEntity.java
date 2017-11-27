package model;

import GetAndImportDBFtoMySQL.IDBFMarkUp;

import javax.persistence.*;

@Entity
@Table(name = "uer")
public class entUerEntity implements IDBFMarkUp {
    private Integer id;
    private String uer;
    private String uername;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uer")
    public String getUer() {
        return uer;
    }

    public void setUer(String uer) {
        this.uer = uer;
    }

    @Basic
    @Column(name = "uername")
    public String getUername() {
        return uername;
    }

    public void setUername(String uername) {
        this.uername = uername;
    }

    public void setupAllUERfields(String uer, String uername) {
        this.uer = uer;
        this.uername = uername;
    }


}