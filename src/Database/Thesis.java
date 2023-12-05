/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author milea
 */
@Entity
@Table(name = "thesis")
@NamedQueries({
    @NamedQuery(name = "Thesis.findAll", query = "SELECT t FROM Thesis t"),
    @NamedQuery(name = "Thesis.findByTesisId", query = "SELECT t FROM Thesis t WHERE t.tesisId = :tesisId"),
    @NamedQuery(name = "Thesis.findByJudul", query = "SELECT t FROM Thesis t WHERE t.judul = :judul"),
    @NamedQuery(name = "Thesis.findByTahunTerbit", query = "SELECT t FROM Thesis t WHERE t.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Thesis.findByJumlahHalaman", query = "SELECT t FROM Thesis t WHERE t.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "Thesis.findByCreatedAt", query = "SELECT t FROM Thesis t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Thesis.findByUpdatedAt", query = "SELECT t FROM Thesis t WHERE t.updatedAt = :updatedAt"),
    @NamedQuery(name = "Thesis.findByBanyaknya", query = "SELECT t FROM Thesis t WHERE t.banyaknya = :banyaknya"),
    @NamedQuery(name = "Thesis.findByLikeJudul", query = "SELECT t FROM Thesis t WHERE UPPER(t.judul) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Thesis.findByLikeTahunTerbit", query = "SELECT t FROM Thesis t WHERE t.tahunTerbit LIKE :paramter"),
    @NamedQuery(name = "Thesis.findByLikeStudent", query = "SELECT t FROM Thesis t JOIN t.studentId s WHERE UPPER(s.fullname) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Thesis.findByUserId", query = "SELECT t FROM Thesis t JOIN t.studentId s WHERE t.userId.userId = :userId")})
public class Thesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tesis_id")
    private Integer tesisId;
    @Column(name = "judul")
    private String judul;
    @Column(name = "tahun_terbit")
    private String tahunTerbit;
    @Column(name = "jumlah_halaman")
    private Integer jumlahHalaman;
    @Lob
    @Column(name = "foto_sampul")
    private byte[] fotoSampul;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "banyaknya")
    private Integer banyaknya;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne
    private Students studentId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Thesis() {
    }

    public Thesis(Integer tesisId) {
        this.tesisId = tesisId;
    }

    public Integer getTesisId() {
        return tesisId;
    }

    public void setTesisId(Integer tesisId) {
        this.tesisId = tesisId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public byte[] getFotoSampul() {
        return fotoSampul;
    }

    public void setFotoSampul(byte[] fotoSampul) {
        this.fotoSampul = fotoSampul;
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

    public Integer getBanyaknya() {
        return banyaknya;
    }

    public void setBanyaknya(Integer banyaknya) {
        this.banyaknya = banyaknya;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Thesis)) {
            return false;
        }
        Thesis other = (Thesis) object;
        if ((this.tesisId == null && other.tesisId != null) || (this.tesisId != null && !this.tesisId.equals(other.tesisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Thesis[ tesisId=" + tesisId + " ]";
    }

}
