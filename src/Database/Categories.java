/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author milea
 */
@Entity
@Table(name = "categories")
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.findByKategoriId", query = "SELECT c FROM Categories c WHERE c.kategoriId = :kategoriId"),
    @NamedQuery(name = "Categories.findByNama", query = "SELECT c FROM Categories c WHERE c.nama = :nama"),
    @NamedQuery(name = "Categories.findByLikeNama", query = "SELECT c FROM Categories c WHERE UPPER(c.nama) LIKE UPPER(:parameter)")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kategori_id")
    private Integer kategoriId;
    @Column(name = "nama")
    private String nama;
    @ManyToMany(mappedBy = "categoriesList")
    private List<Books> booksList;

    public Categories() {
    }

    public Categories(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kategoriId != null ? kategoriId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.kategoriId == null && other.kategoriId != null) || (this.kategoriId != null && !this.kategoriId.equals(other.kategoriId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Categories[ kategoriId=" + kategoriId + " ]";
    }
    
}
