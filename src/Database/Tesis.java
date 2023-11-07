/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author saintek11
 */
@Entity
@Table(name = "tesis")
@NamedQueries({
    @NamedQuery(name = "Tesis.findAll", query = "SELECT t FROM Tesis t"),
    @NamedQuery(name = "Tesis.findByJudul", query = "SELECT t FROM Tesis t WHERE t.judul = :judul"),
    @NamedQuery(name = "Tesis.findByTahunTerbit", query = "SELECT t FROM Tesis t WHERE t.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Tesis.findByJumlahHalaman", query = "SELECT t FROM Tesis t WHERE t.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "Tesis.findByCreatedAt", query = "SELECT t FROM Tesis t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Tesis.findByUpdatedAt", query = "SELECT t FROM Tesis t WHERE t.updatedAt = :updatedAt"),
    @NamedQuery(name = "Tesis.findByTesisId", query = "SELECT t FROM Tesis t WHERE t.tesisId = :tesisId")})
public class Tesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "judul")
    private String judul;
    @Basic(optional = false)
    @Column(name = "tahun_terbit")
    private int tahunTerbit;
    @Basic(optional = false)
    @Column(name = "jumlah_halaman")
    private int jumlahHalaman;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIME)
    private Date updatedAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tesis_id")
    private Integer tesisId;
    @OneToMany(mappedBy = "tesisId")
    private Collection<Log> logCollection;
    @OneToMany(mappedBy = "tesisId")
    private Collection<Pengarang> pengarangCollection;

    public Tesis() {
    }

    public Tesis(Integer tesisId) {
        this.tesisId = tesisId;
    }

    public Tesis(Integer tesisId, String judul, int tahunTerbit, int jumlahHalaman, Date createdAt, Date updatedAt) {
        this.tesisId = tesisId;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.jumlahHalaman = jumlahHalaman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getTesisId() {
        return tesisId;
    }

    public void setTesisId(Integer tesisId) {
        this.tesisId = tesisId;
    }

    public Collection<Log> getLogCollection() {
        return logCollection;
    }

    public void setLogCollection(Collection<Log> logCollection) {
        this.logCollection = logCollection;
    }

    public Collection<Pengarang> getPengarangCollection() {
        return pengarangCollection;
    }

    public void setPengarangCollection(Collection<Pengarang> pengarangCollection) {
        this.pengarangCollection = pengarangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tesisId != null ? tesisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tesis)) {
            return false;
        }
        Tesis other = (Tesis) object;
        if ((this.tesisId == null && other.tesisId != null) || (this.tesisId != null && !this.tesisId.equals(other.tesisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Tesis[ tesisId=" + tesisId + " ]";
    }
    
}
