/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author milea
 */
public class MainFrame extends javax.swing.JFrame {

    protected EntityManager entityManager;

    public MainFrame() {
        this.entityManager = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
    }

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public int dialog(String[] data) {
        return JOptionPane.showConfirmDialog(this, data[1], data[0], JOptionPane.YES_NO_OPTION);
    }

    public ImageIcon getImageFromDatabase(byte[] data, int size) {
        try {
            if (data != null) {
                // Konversi byte array ke BufferedImage
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                BufferedImage bufferedImage = ImageIO.read(bis);

                // Ubah BufferedImage menjadi ImageIcon
                if (bufferedImage != null) {
                    Image image = bufferedImage.getScaledInstance(size, size, Image.SCALE_SMOOTH); // Sesuaikan ukuran gambar
                    return new ImageIcon(image);
                }
            }
        } catch (IOException e) {
            this.peringatan("Gagal load image!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected String generatePassword() {
        String pass = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            pass += String.valueOf(random.nextInt(10));
        }
        return pass;
    }
}
