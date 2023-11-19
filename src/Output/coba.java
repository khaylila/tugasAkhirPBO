/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output;

import Database.Books;
import Database.Pengarang;
import Database.Tesis;
import Database.Users;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author milea
 */
public class coba {

    public static void main(String[] args) {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println(myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(myDateObj.format(myFormatObj));
//        Date asdf = new Date();
//        asdf
//        System.out.println(String.valueOf(asdf.));
//        Date time = new Date();
//
//        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
//        try {
//            String password = BCrypt.hashpw("2016", BCrypt.gensalt(12));
//
//            entityManager.getTransaction().begin();
//            Users user = new Users();
//            user.setFullname("Mochamad Roiyan");
//            user.setUsername("khaylila");
//            user.setPassword(password);
//            user.setRoles("admin");
//
//            user.setCreatedAt(time);
//            user.setUpdatedAt(time);
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

        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
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
