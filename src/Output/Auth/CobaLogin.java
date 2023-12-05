/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.Auth;

import Database.Books;
import Database.Users;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author milea
 */
public class CobaLogin {

    public static void main(String[] args) {
        
        EntityManager em = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Books> searchBookByCategory = em.createNamedQuery("Books.findByLikeKategori", Books.class);
        searchBookByCategory.setParameter("parameter", "fiksi");
        List<Books> results = searchBookByCategory.getResultList();
        System.out.println(results.size());
        
        em.getTransaction().commit();
        
        
        
//        long time = new Date().getTime();
//        System.out.println("Milisecond " + time);
//        System.out.println("Second " + (time / 1000));
//        System.out.println((1701105580-1700500780)/86400);
//        System.out.println(1701105580-1700500780);
//        Date date= new Date();
////
//        EntityManager entityManager = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
//        try {
//            String password = BCrypt.hashpw("2016", BCrypt.gensalt(12));
//
//            entityManager.getTransaction().begin();
//            Users user = new Users();
//            user.setFullname("Mochamad Roiyan");
//            user.setUsername("khaylila");
//            user.setPassword(password);
//            user.setRoles(1);
//
//            user.setCreatedAt(date);
//            user.setUpdatedAt(date);
//            entityManager.persist(user);
//            entityManager.getTransaction().commit();
//            System.out.println("berhasil");
//        } catch (Exception e) {
//            System.out.println("gagal tambah data" + e.getMessage());
//        }
//        entityManager.close();
//        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
//        TypedQuery<Object[]> query = entityManager.createQuery("SELECT t, p FROM Tesis t JOIN t.pengarangCollection p", Object[].class);
//        for (Object[] objects : query.getResultList()) {
////            System.out.println(String.valueOf(tesis));
//            Tesis tesis = (Tesis) objects[0];
//            Pengarang pengarang = (Pengarang) objects[1];
//
//            System.out.println("Tesis ID: " + tesis.getTesisId());
//            System.out.println("Tesis Judul: " + tesis.getJudul());
//            System.out.println("Pengarang Name: " + pengarang.getName());
//            System.out.println("-----");
//        }
////        System.out.println(String.valueOf(query.getFirstResult()));
//        entityManager.close();
//        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
//        TypedQuery<Tesis> query = entityManager.createQuery("SELECT t FROM Tesis t JOIN FETCH t.pengarangCollection", Tesis.class);
//        for (Tesis tesis : query.getResultList()) {
////            System.out.println(String.valueOf(tesis));
//
//            System.out.println("Tesis ID: " + tesis.getTesisId());
//            System.out.println("Tesis Judul: " + tesis.getJudul());
//            for(Pengarang pgr : tesis.getPengarangCollection()){
//             System.out.println("Pengarang Name: " + pgr.getName());   
//            }
//            System.out.println("-----");
//        }
//        System.out.println(String.valueOf(query.getFirstResult()));
//        entityManager.close();
//        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
//        TypedQuery<Books> query1 = entityManager.createQuery("SELECT DISTINCT b FROM Books b JOIN FETCH b.pengarangCollection", Books.class);
//        for (Books books : query1.getResultList()) {
//            System.out.println("Buku ID: " + books.getBookId());
//            System.out.println("Buku Judul: " + books.getJudul());
//            for(Pengarang pgr : books.getPengarangCollection()){
//                System.out.println("Pengarang Name: " + pgr.getName());   
//            }
//            System.out.println("-----");
//        }
////        System.out.println(String.valueOf(query.getFirstResult()));
//        entityManager.close();
//        entityManager.getTransaction().begin();
//        Date date = new Date();
//        Tesis tesis = new Tesis();
//        tesis.setJudul("Marmut Merah Jambu");
//        tesis.setJumlahHalaman(150);
//        tesis.setTahunTerbit(2021);
//        tesis.setCreatedAt(date);
//        tesis.setUpdatedAt(date);
//        entityManager.persist(tesis);
//        entityManager.getTransaction().commit();
    }
}
