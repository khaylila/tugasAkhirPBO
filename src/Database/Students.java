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
@Table(name = "students")
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s"),
    @NamedQuery(name = "Students.findByStudentId", query = "SELECT s FROM Students s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Students.findByNim", query = "SELECT s FROM Students s WHERE s.nim = :nim"),
    @NamedQuery(name = "Students.findByFullname", query = "SELECT s FROM Students s WHERE s.fullname = :fullname"),
    @NamedQuery(name = "Students.findByAngkatan", query = "SELECT s FROM Students s WHERE s.angkatan = :parameter"),
    @NamedQuery(name = "Students.findByTelepon", query = "SELECT s FROM Students s WHERE s.telepon = :telepon"),
    @NamedQuery(name = "Students.findByAlamat", query = "SELECT s FROM Students s WHERE s.alamat = :alamat"),
    @NamedQuery(name = "Students.findByCreatedAt", query = "SELECT s FROM Students s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "Students.findByUpdatedAt", query = "SELECT s FROM Students s WHERE s.updatedAt = :updatedAt"),
    @NamedQuery(name = "Students.findByLikeNim", query = "SELECT s FROM Students s WHERE s.nim LIKE :parameter"),
    @NamedQuery(name = "Students.findByLikeFullname", query = "SELECT s FROM Students s WHERE UPPER(s.fullname) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Students.findByLikeTelepon", query = "SELECT s FROM Students s WHERE s.telepon LIKE :parameter"),
    @NamedQuery(name = "Students.findByLikeAlamat", query = "SELECT s FROM Students s WHERE UPPER(s.alamat) LIKE UPPER(:parameter)"),
    @NamedQuery(name = "Students.findByLikeAngkatan", query = "SELECT s FROM Students s WHERE CAST(s.angkatan AS text) LIKE :parameter")})
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "nim")
    private String nim;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "angkatan")
    private Integer angkatan;
    @Column(name = "telepon")
    private String telepon;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "studentId")
    private List<Books> booksList;
    @OneToMany(mappedBy = "studentId")
    private List<Borrows> borrowsList;
    @OneToMany(mappedBy = "studentId")
    private List<Thesis> thesisList;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Students() {
    }

    public Students(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(Integer angkatan) {
        this.angkatan = angkatan;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public List<Borrows> getBorrowsList() {
        return borrowsList;
    }

    public void setBorrowsList(List<Borrows> borrowsList) {
        this.borrowsList = borrowsList;
    }

    public List<Thesis> getThesisList() {
        return thesisList;
    }

    public void setThesisList(List<Thesis> thesisList) {
        this.thesisList = thesisList;
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
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Students[ studentId=" + studentId + " ]";
    }
    
}
