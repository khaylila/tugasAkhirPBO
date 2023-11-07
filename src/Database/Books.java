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
@Table(name = "books")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByJudul", query = "SELECT b FROM Books b WHERE b.judul = :judul"),
    @NamedQuery(name = "Books.findByTahunTerbit", query = "SELECT b FROM Books b WHERE b.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Books.findByJumlahHalaman", query = "SELECT b FROM Books b WHERE b.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "Books.findByCreatedAt", query = "SELECT b FROM Books b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Books.findByUpdatedAt", query = "SELECT b FROM Books b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Books.findBySubJudul", query = "SELECT b FROM Books b WHERE b.subJudul = :subJudul"),
    @NamedQuery(name = "Books.findByPenerbit", query = "SELECT b FROM Books b WHERE b.penerbit = :penerbit")})
public class Books implements Serializable {

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
    @Column(name = "book_id")
    private Integer bookId;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "sub_judul")
    private String subJudul;
    @Basic(optional = false)
    @Column(name = "penerbit")
    private String penerbit;
    @OneToMany(mappedBy = "bookId")
    private Collection<Log> logCollection;
    @OneToMany(mappedBy = "bookId")
    private Collection<Pengarang> pengarangCollection;

    public Books() {
    }

    public Books(Integer bookId) {
        this.bookId = bookId;
    }

    public Books(Integer bookId, String judul, int tahunTerbit, int jumlahHalaman, Date createdAt, Date updatedAt, String isbn, String penerbit) {
        this.bookId = bookId;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.jumlahHalaman = jumlahHalaman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isbn = isbn;
        this.penerbit = penerbit;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubJudul() {
        return subJudul;
    }

    public void setSubJudul(String subJudul) {
        this.subJudul = subJudul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
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
