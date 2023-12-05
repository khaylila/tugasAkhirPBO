/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Output.Users;

import Output.Submenu.Mahasiswa.*;
import Output.Submenu.Buku.*;
import Database.Books;
import Database.Borrows;
import Database.Categories;
import Database.Students;
import Database.Thesis;
import Database.Users;
import Output.MainFrame;
import Output.Submenu.Dashboard;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author milea
 */
public class ViewUsers extends MainFrame {

    protected Users user;
    boolean backToMainMenu = true;
    int userId;

    /**
     * Creates new form ViewBuku
     */
    public ViewUsers() {
        initComponents();
    }

    public ViewUsers(int userId, int viewUserId) {
        System.out.println("hehe");
        this.userId = userId;
        initComponents();

        TypedQuery<Users> getUserById = entityManager.createNamedQuery("Users.findByUserId", Users.class);
        getUserById.setParameter("userId", viewUserId);
        Users result = getUserById.getSingleResult();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            labelAdminId.setText("ID : ADM" + String.format("%010d", result.getUserId()));
            inputFullname.setText(result.getFullname());
            inputUsername.setText(result.getUsername());
            labelCreatedAt.setText("Tgl. Ditambahkan: " + dateFormat.format(result.getCreatedAt()));
            labelUpdatedAt.setText("Terakhir Diubah: " + dateFormat.format(result.getUpdatedAt()));

            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
//          begin daftar peminjaman
            DefaultTableModel model = (DefaultTableModel) tabelPeminjaman.getModel();
            model.setRowCount(0);
            
            TypedQuery<Books> getBooksByUserId = entityManager.createNamedQuery("Books.findByUserId", Books.class);
            getBooksByUserId.setParameter("userId", viewUserId);
            TypedQuery<Borrows> getBorrowsByUserId = entityManager.createNamedQuery("Borrows.findByUserId", Borrows.class);
            getBorrowsByUserId.setParameter("userId", viewUserId);
            TypedQuery<Thesis> getTesisByUserId = entityManager.createNamedQuery("Thesis.findByUserId", Thesis.class);
            getTesisByUserId.setParameter("userId", viewUserId);
            for (Borrows pinjam : getBorrowsByUserId.getResultList()) {
                Object[] baris = new Object[5];
                baris[0] = pinjam.getStudentId().getFullname();
                if (pinjam.getBooksId() == null) {
                    baris[1] = pinjam.getTesisId().getJudul();
                    baris[2] = "Skripsi";
                } else {
                    baris[1] = pinjam.getBooksId().getJudul();
                    baris[2] = "Buku";
                }
                baris[3] = simpleDate.format(pinjam.getTanggalPinjam());
                baris[4] = (pinjam.getTanggalKembali() == null ? "Blm kembali" : "Dikembalikan");
                model.addRow(baris);
            }
//          end daftar peminjam

//          begin pencatatan buku
            DefaultTableModel tbRiwayat = (DefaultTableModel) tabelPencatatanBuku.getModel();
            tbRiwayat.setRowCount(0);
//            System.out.println("list buku => " + result.getBooksList());
            for (Books buku : getBooksByUserId.getResultList()) {
                Object[] baris = new Object[4];
                baris[0] = buku.getStudentId().getFullname();
                baris[1] = buku.getJudul();
                baris[2] = "Buku";
                baris[3] = simpleDate.format(buku.getCreatedAt());
                tbRiwayat.addRow(baris);
            }
//          end sumbang buku

//          begin pencatatan skripsi
            for (Thesis tesis : getTesisByUserId.getResultList()) {
                Object[] baris = new Object[4];
                baris[0] = tesis.getStudentId().getFullname();
                baris[1] = tesis.getJudul();
                baris[2] = "Skripsi";
                baris[3] = simpleDate.format(tesis.getCreatedAt());
                tbRiwayat.addRow(baris);
            }
//          end skripsi

            this.user = result;
        } catch (NoResultException e) {
            this.peringatan("Data tidak ditemukan!");
            this.dispose();
        }

    }

    public void loadUsernameFullname(String username, String fullname, Date updatedAt) {
        inputFullname.setText(fullname);
        inputUsername.setText(username);
        labelUpdatedAt.setText("Terakhir Diubah: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(updatedAt));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelAdminId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputFullname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        labelCreatedAt = new javax.swing.JLabel();
        labelUpdatedAt = new javax.swing.JLabel();
        btnResetPassword = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPencatatanBuku = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelPeminjaman = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Buku");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(708, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(708, 520));
        jPanel1.setPreferredSize(new java.awt.Dimension(708, 520));

        jLabel1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/logo-50x50.png"))); // NOI18N
        jLabel1.setText("Detail Admin");

        labelAdminId.setFont(new java.awt.Font("Poppins Light", 0, 15)); // NOI18N
        labelAdminId.setText("ID : xxxxxxxxxx");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Fullname");

        inputFullname.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Username");

        inputUsername.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("Riwayat pencatatan buku");

        labelCreatedAt.setText("Tgl. Ditambahkan: xx/xx/xxxx xx:xx:xx");

        labelUpdatedAt.setText("Terakhir Diubah: xx/xx/xxxx xx:xx:xx");

        btnResetPassword.setBackground(new java.awt.Color(255, 204, 0));
        btnResetPassword.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        btnResetPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/pencil-30x30.png"))); // NOI18N
        btnResetPassword.setText("Ganti Password");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 204, 0));
        btnDelete.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/trash-30x30.png"))); // NOI18N
        btnDelete.setText("Hapus data");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tabelPencatatanBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pemilik Sebelumnya", "Judul", "Tipe", "Tanggal Pemberian"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelPencatatanBuku);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel12.setText("Riwayat peminjaman buku");

        tabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Judul", "Peminjam", "Tipe", "Tanggal Pinjam", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabelPeminjaman);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        btnEdit.setBackground(new java.awt.Color(255, 204, 0));
        btnEdit.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/pencil-30x30.png"))); // NOI18N
        btnEdit.setText("Ubah data");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(inputFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(inputUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                            .addComponent(labelCreatedAt)
                            .addComponent(labelUpdatedAt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelAdminId)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(labelAdminId)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(inputFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, 0)
                        .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelCreatedAt)
                        .addGap(0, 0, 0)
                        .addComponent(labelUpdatedAt)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (backToMainMenu) {
            new Dashboard(userId, "Users").setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int result = this.dialog(new String[]{"Hapus admin", "Yakin ingin menghapus data admin?"});
        if (result == JOptionPane.YES_OPTION) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(this.user);
                entityManager.getTransaction().commit();
                this.peringatan("Berhasil menghapus data admin!");
                this.dispose();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                this.peringatan("Gagal menghapus admin!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        // TODO add your handling code here:
        int result = this.dialog(new String[]{"Resset password", "Yakin ingin mereset password?"});
        if (result == JOptionPane.YES_OPTION) {
            Users oldUser = this.user;
            String plainPassword = generatePassword();
            String hashPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
            oldUser.setPassword(hashPassword);
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(oldUser);
                entityManager.getTransaction().commit();
                this.dispose();
                this.peringatan("Berhasil merubah password! Password saat ini adalah '" + plainPassword + "'");
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }//GEN-LAST:event_btnResetPasswordActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        this.backToMainMenu = false;
        this.dispose();
        new FormEditMahasiswa(this.userId, this.user.getUserId()).setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JTextField inputFullname;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelAdminId;
    private javax.swing.JLabel labelCreatedAt;
    private javax.swing.JLabel labelUpdatedAt;
    private javax.swing.JTable tabelPeminjaman;
    private javax.swing.JTable tabelPencatatanBuku;
    // End of variables declaration//GEN-END:variables
}
