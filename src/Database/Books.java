/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author milea
 */
@Entity
@Table(name = "books")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByJudul", query = "SELECT b FROM Books b WHERE b.judul = :judul"),
    @NamedQuery(name = "Books.findBySubJudul", query = "SELECT b FROM Books b WHERE b.subJudul = :subJudul"),
    @NamedQuery(name = "Books.findByPengarang", query = "SELECT b FROM Books b WHERE b.pengarang = :pengarang"),
    @NamedQuery(name = "Books.findByPenerbit", query = "SELECT b FROM Books b WHERE b.penerbit = :penerbit"),
    @NamedQuery(name = "Books.findByTahunTerbit", query = "SELECT b FROM Books b WHERE b.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Books.findByJumlahHalaman", query = "SELECT b FROM Books b WHERE b.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "Books.findByBanyaknya", query = "SELECT b FROM Books b WHERE b.banyaknya = :banyaknya"),
    @NamedQuery(name = "Books.findByCreatedAt", query = "SELECT b FROM Books b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Books.findByUpdatedAt", query = "SELECT b FROM Books b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Books.findByLikeIsbn", query = "SELECT b FROM Books b WHERE b.isbn LIKE :parameter"),
    @NamedQuery(name = "Books.findByLikeJudul", query = "SELECT b FROM Books b WHERE UPPER(b.judul) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Books.findByLikePengarang", query = "SELECT b FROM Books b WHERE UPPER(b.pengarang) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Books.findByLikePenerbit", query = "SELECT b FROM Books b WHERE UPPER(b.penerbit) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Books.findByLikeTahunTerbit", query = "SELECT b FROM Books b WHERE b.tahunTerbit LIKE :parameter"),
    @NamedQuery(name = "Books.findByLikeKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE c.nama = :parameter"),
    @NamedQuery(name = "Books.findAllWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE c.nama = :kategori"),
    @NamedQuery(name = "Books.findByLikeIsbnWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE b.isbn LIKE :parameter AND c.nama = :kategori"),
    @NamedQuery(name = "Books.findByLikeJudulWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE UPPER(b.judul) LIKE UPPER(:parameter) AND c.nama = :kategori"),
    @NamedQuery(name = "Books.findByLikePengarangWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE UPPER(b.pengarang) LIKE UPPER(:parameter) AND c.nama = :kategori"),
    @NamedQuery(name = "Books.findByLikePenerbitWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE UPPER(b.penerbit) LIKE UPPER(:parameter) AND c.nama = :kategori"),
    @NamedQuery(name = "Books.findByLikeTahunTerbitWithKategori", query = "SELECT b FROM Books b JOIN b.categoriesList c WHERE b.tahunTerbit LIKE :parameter AND c.nama = :kategori"),
    @NamedQuery(name = "Books.findByUserId", query = "SELECT b FROM Books b JOIN b.studentId s WHERE b.userId.userId = :userId")})
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "judul")
    private String judul;
    @Column(name = "sub_judul")
    private String subJudul;
    @Column(name = "pengarang")
    private String pengarang;
    @Column(name = "penerbit")
    private String penerbit;
    @Column(name = "tahun_terbit")
    private String tahunTerbit;
    @Column(name = "jumlah_halaman")
    private Integer jumlahHalaman;
    @Column(name = "banyaknya")
    private Integer banyaknya;
    @Lob
    @Column(name = "foto_sampul")
    private byte[] fotoSampul;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "isbn")
    private String isbn;
    @JoinTable(name = "books_categories", joinColumns = {
        @JoinColumn(name = "book_id", referencedColumnName = "book_id")}, inverseJoinColumns = {
        @JoinColumn(name = "kategori_id", referencedColumnName = "kategori_id")})
    @ManyToMany
    private List<Categories> categoriesList;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne
    private Students studentId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "booksId")
    private List<Borrows> borrowsList;

    public Books() {
    }

    public Books(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSubJudul() {
        return subJudul;
    }

    public void setSubJudul(String subJudul) {
        this.subJudul = subJudul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
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

    public Integer getBanyaknya() {
        return banyaknya;
    }

    public void setBanyaknya(Integer banyaknya) {
        this.banyaknya = banyaknya;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
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

    public List<Borrows> getBorrowsList() {
        return borrowsList;
    }

    public void setBorrowsList(List<Borrows> borrowsList) {
        this.borrowsList = borrowsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Books[ bookId=" + bookId + " ]";
    }
    
}
