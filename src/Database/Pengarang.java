/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
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

/**
 *
 * @author saintek11
 */
@Entity
@Table(name = "pengarang")
@NamedQueries({
    @NamedQuery(name = "Pengarang.findAll", query = "SELECT p FROM Pengarang p"),
    @NamedQuery(name = "Pengarang.findByPengarangId", query = "SELECT p FROM Pengarang p WHERE p.pengarangId = :pengarangId"),
    @NamedQuery(name = "Pengarang.findByName", query = "SELECT p FROM Pengarang p WHERE p.name = :name")})
public class Pengarang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pengarang_id")
    private Integer pengarangId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne
    private Books bookId;
    @JoinColumn(name = "tesis_id", referencedColumnName = "tesis_id")
    @ManyToOne
    private Tesis tesisId;

    public Pengarang() {
    }

    public Pengarang(Integer pengarangId) {
        this.pengarangId = pengarangId;
    }

    public Pengarang(Integer pengarangId, String name) {
        this.pengarangId = pengarangId;
        this.name = name;
    }

    public Integer getPengarangId() {
        return pengarangId;
    }

    public void setPengarangId(Integer pengarangId) {
        this.pengarangId = pengarangId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Tesis getTesisId() {
        return tesisId;
    }

    public void setTesisId(Tesis tesisId) {
        this.tesisId = tesisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pengarangId != null ? pengarangId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengarang)) {
            return false;
        }
        Pengarang other = (Pengarang) object;
        if ((this.pengarangId == null && other.pengarangId != null) || (this.pengarangId != null && !this.pengarangId.equals(other.pengarangId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Pengarang[ pengarangId=" + pengarangId + " ]";
    }
    
}
