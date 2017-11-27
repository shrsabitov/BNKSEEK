package model;

import GetAndImportDBFtoMySQL.IDBFMarkUp;

import javax.persistence.*;

@Entity
@Table(name = "tnp")
public class entTnpEntity implements IDBFMarkUp {
    private Integer id;
    private String tnp;
    private String fullname;
    private String shortname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TNP")
    public String getTnp() {
        return tnp;
    }

    public void setTnp(String tnp) {
        this.tnp = tnp;
    }

    @Basic
    @Column(name = "FULLNAME")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "SHORTNAME")
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }


    public void setupAllTNPfields(String tnp, String fullname, String shortname) {

        setTnp(tnp);
        setFullname(fullname);
        setShortname(shortname);

    }


}
