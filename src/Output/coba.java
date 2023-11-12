/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output;

import Database.Users;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
        Date time = new Date();

        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
        try {
            String password = BCrypt.hashpw("2016", BCrypt.gensalt(12));

            entityManager.getTransaction().begin();
            Users user = new Users();
            user.setFullname("Mochamad Roiyan");
            user.setUsername("khaylila");
            user.setPassword(password);
            user.setRoles("admin");

            user.setCreatedAt(time);
            user.setUpdatedAt(time);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("berhasil");
        } catch (Exception e) {
            System.out.println("gagal tambah data" + e.getMessage());
        }
        entityManager.close();
    }
}
