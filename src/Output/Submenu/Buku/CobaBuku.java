/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.Submenu.Buku;

import Database.Books;
import Database.Categories;
import Database.Users;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author milea
 */
public class CobaBuku{
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Users> userId = entityManager.createNamedQuery("Users.findByUserId", Users.class);
        userId.setParameter("userId", 1);
        Books buku = new Books();
        buku.setIsbn("9283728192034");
        buku.setJudul("Layu Sebelum Berkembang");
        buku.setSubJudul("");
        buku.setPengarang("MPU Tantular");
        buku.setPenerbit("Gramedia");
        buku.setTahunTerbit("2012");
        buku.setUserId(userId.getSingleResult());
        buku.setBanyaknya(5);
        Date date = new Date();
        buku.setCreatedAt(date);
        buku.setUpdatedAt(date);
        TypedQuery<Categories> categories = entityManager.createNamedQuery("Categories.findAll", Categories.class);
        buku.setCategoriesList(categories.getResultList());
        entityManager.persist(buku);
        entityManager.getTransaction().commit();
    }
}
