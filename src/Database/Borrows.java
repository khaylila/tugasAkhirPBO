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
@Table(name = "borrows")
@NamedQueries({
    @NamedQuery(name = "Borrows.findAll", query = "SELECT b FROM Borrows b WHERE EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByPinjamId", query = "SELECT b FROM Borrows b WHERE b.pinjamId = :pinjamId"),
    @NamedQuery(name = "Borrows.findByTanggalPinjam", query = "SELECT b FROM Borrows b WHERE b.tanggalPinjam = :tanggalPinjam"),
    @NamedQuery(name = "Borrows.findByRencanaKembali", query = "SELECT b FROM Borrows b WHERE b.rencanaKembali = :rencanaKembali"),
    @NamedQuery(name = "Borrows.findByTanggalKembali", query = "SELECT b FROM Borrows b WHERE b.tanggalKembali = :tanggalKembali"),
    @NamedQuery(name = "Borrows.findByBanyaknya", query = "SELECT b FROM Borrows b WHERE b.banyaknya = :banyaknya"),
    @NamedQuery(name = "Borrows.findByDenda", query = "SELECT b FROM Borrows b WHERE b.denda = :denda"),
    @NamedQuery(name = "Borrows.findByCreatedAt", query = "SELECT b FROM Borrows b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Borrows.findByUpdatedAt", query = "SELECT b FROM Borrows b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "Borrows.findByMonthYear", query = "SELECT b FROM Borrows b WHERE EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByLikeMahasiswaFullnameWithMonthYear", query = "SELECT b FROM Borrows b WHERE LOWER(b.studentId.fullname) LIKE LOWER(:parameter) AND EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByLikeBooksJudulWithMonthYear", query = "SELECT b FROM Borrows b WHERE LOWER(b.booksId.judul) LIKE LOWER(:parameter) AND EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByLikeMahasiswaAngkatanWithMonthYear", query = "SELECT b FROM Borrows b WHERE CAST(b.studentId.angkatan AS text) LIKE :parameter AND EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByKategoriTerbanyak", query = "SELECT b FROM Borrows b WHERE EXTRACT(MONTH FROM b.createdAt) = :month AND EXTRACT(YEAR FROM b.createdAt) = :year"),
    @NamedQuery(name = "Borrows.findByBookId", query = "SELECT b FROM Borrows b WHERE b.booksId.bookId = :bookId"),
    @NamedQuery(name = "Borrows.findByTesisId", query = "SELECT b FROM Borrows b WHERE b.tesisId.tesisId = :tesisId"),
    @NamedQuery(name = "Borrows.findByUserId", query = "SELECT b FROM Borrows b WHERE b.userId.userId = :userId"),
    @NamedQuery(name = "Borrows.findMostBorrowedBooks", query = "SELECT b.booksId.bookId, COUNT(b.booksId.bookId) AS totalBorrows FROM Borrows b JOIN b.booksId bb GROUP BY bb.bookId ORDER BY COUNT(bb.bookId) DESC")})
public class Borrows implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pinjam_id")
    private Integer pinjamId;
    @Column(name = "tanggal_pinjam")
    @Temporal(TemporalType.DATE)
    private Date tanggalPinjam;
    @Column(name = "rencana_kembali")
    @Temporal(TemporalType.DATE)
    private Date rencanaKembali;
    @Column(name = "tanggal_kembali")
    @Temporal(TemporalType.DATE)
    private Date tanggalKembali;
    @Column(name = "banyaknya")
    private Integer banyaknya;
    @Column(name = "denda")
    private Integer denda;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "books_id", referencedColumnName = "book_id")
    @ManyToOne
    private Books booksId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne
    private Students studentId;
    @JoinColumn(name = "tesis_id", referencedColumnName = "tesis_id")
    @ManyToOne
    private Thesis tesisId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Borrows() {
    }

    public Borrows(Integer pinjamId) {
        this.pinjamId = pinjamId;
    }

    public Integer getPinjamId() {
        return pinjamId;
    }

    public void setPinjamId(Integer pinjamId) {
        this.pinjamId = pinjamId;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getRencanaKembali() {
        return rencanaKembali;
    }

    public void setRencanaKembali(Date rencanaKembali) {
        this.rencanaKembali = rencanaKembali;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Integer getBanyaknya() {
        return banyaknya;
    }

    public void setBanyaknya(Integer banyaknya) {
        this.banyaknya = banyaknya;
    }

    public Integer getDenda() {
        return denda;
    }

    public void setDenda(Integer denda) {
        this.denda = denda;
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

    public Books getBooksId() {
        return booksId;
    }

    public void setBooksId(Books booksId) {
        this.booksId = booksId;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public Thesis getTesisId() {
        return tesisId;
    }

    public void setTesisId(Thesis tesisId) {
        this.tesisId = tesisId;
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
        hash += (pinjamId != null ? pinjamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrows)) {
            return false;
        }
        Borrows other = (Borrows) object;
        if ((this.pinjamId == null && other.pinjamId != null) || (this.pinjamId != null && !this.pinjamId.equals(other.pinjamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Borrows[ pinjamId=" + pinjamId + " ]";
    }
    
}
