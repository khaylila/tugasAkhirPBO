/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Output.Tesis;

import Database.Borrows;
import Database.Thesis;
import Output.MainFrame;
import Output.Submenu.Dashboard;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author milea
 */
public class ViewTesis extends MainFrame {

    int userId;
    protected Thesis tesis;
    boolean backToMainMenu = true;

    /**
     * Creates new form ViewBuku
     */
    public ViewTesis() {
        initComponents();
    }

    public ViewTesis(int userId, int tesisId, String from) {
        if (from.equalsIgnoreCase("dashboard")) {
            this.backToMainMenu = false;
        }

        this.userId = userId;

        initComponents();

        TypedQuery<Thesis> getTesisById = entityManager.createNamedQuery("Thesis.findByTesisId", Thesis.class);
        getTesisById.setParameter("tesisId", tesisId);
        Thesis result = getTesisById.getSingleResult();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            labelTesisId.setText("ID : " + String.format("%010d", result.getTesisId()));
            inputJudulTesis.setText(result.getJudul());
            inputJumlahHalaman.setText(String.valueOf(result.getJumlahHalaman()));
            inputTahunTerbit.setText(result.getTahunTerbit());
            labelCreatedAt.setText("Tgl. Ditambahkan: " + dateFormat.format(result.getCreatedAt()));
            labelUpdatedAt.setText("Terakhir Diubah: " + dateFormat.format(result.getUpdatedAt()));

//          begin pengarang
            inputPengarang.setText(result.getStudentId().getFullname());
//          end pengarang

//          begin daftar peminjam
            TypedQuery<Borrows> queryByIdTesis = entityManager.createNamedQuery("Borrows.findByTesisId", Borrows.class);
            queryByIdTesis.setParameter("tesisId", result.getTesisId());
            DefaultTableModel model = (DefaultTableModel) tabelPeminjam.getModel();
            model.setRowCount(0);
            List<Borrows> results = queryByIdTesis.getResultList();
            if (!results.isEmpty()) {
                for (Borrows peminjam : results) {
                    Object[] baris = new Object[3];
                    baris[0] = peminjam.getStudentId().getFullname();
                    baris[1] = new SimpleDateFormat("dd/MM/yyyy").format(peminjam.getTanggalPinjam());
                    baris[2] = (peminjam.getTanggalKembali() == null ? "Blm kembali" : "Dikembalikan");
                    model.addRow(baris);
                }
            }
//          end daftar peminjam

//          begin image
            if (result.getFotoSampul() != null) {
                labelImage.setIcon(this.getImageFromDatabase(result.getFotoSampul(), 250));
            }
//          end image

            inputBanyaknya.setText(result.getBanyaknya() + " / " + results.size() + " / " + (result.getBanyaknya() - results.size()));

            this.tesis = result;
        } catch (NoResultException e) {
            this.peringatan("Data tidak ditemukan!");
            this.dispose();
        }

    }

    public ViewTesis(int userId, int tesisId) {
        this.userId = userId;

        initComponents();

        TypedQuery<Thesis> getTesisById = entityManager.createNamedQuery("Thesis.findByTesisId", Thesis.class);
        getTesisById.setParameter("tesisId", tesisId);
        Thesis result = getTesisById.getSingleResult();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            labelTesisId.setText("ID : " + String.format("%010d", result.getTesisId()));
            inputJudulTesis.setText(result.getJudul());
            inputJumlahHalaman.setText(String.valueOf(result.getJumlahHalaman()));
            inputTahunTerbit.setText(result.getTahunTerbit());
            labelCreatedAt.setText("Tgl. Ditambahkan: " + dateFormat.format(result.getCreatedAt()));
            labelUpdatedAt.setText("Terakhir Diubah: " + dateFormat.format(result.getUpdatedAt()));

//          begin pengarang
            inputPengarang.setText(result.getStudentId().getFullname());
//          end pengarang

//          begin daftar peminjam
            TypedQuery<Borrows> queryByIdTesis = entityManager.createNamedQuery("Borrows.findByTesisId", Borrows.class);
            queryByIdTesis.setParameter("tesisId", result.getTesisId());
            DefaultTableModel model = (DefaultTableModel) tabelPeminjam.getModel();
            model.setRowCount(0);
            List<Borrows> results = queryByIdTesis.getResultList();
            if (!results.isEmpty()) {
                for (Borrows peminjam : results) {
                    Object[] baris = new Object[3];
                    baris[0] = peminjam.getStudentId().getFullname();
                    baris[1] = new SimpleDateFormat("dd/MM/yyyy").format(peminjam.getTanggalPinjam());
                    baris[2] = (peminjam.getTanggalKembali() == null ? "Blm kembali" : "Dikembalikan");
                    model.addRow(baris);
                }
            }
//          end daftar peminjam

//          begin image
            if (result.getFotoSampul() != null) {
                labelImage.setIcon(this.getImageFromDatabase(result.getFotoSampul(), 250));
            }
//          end image

            inputBanyaknya.setText(result.getBanyaknya() + " / " + results.size() + " / " + (result.getBanyaknya() - results.size()));

            this.tesis = result;
        } catch (NoResultException e) {
            this.peringatan("Data tidak ditemukan!");
            this.dispose();
        }

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
        labelTesisId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputJudulTesis = new javax.swing.JTextField();
        inputPengarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputTahunTerbit = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        inputJumlahHalaman = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        inputBanyaknya = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelImage = new javax.swing.JLabel();
        labelCreatedAt = new javax.swing.JLabel();
        labelUpdatedAt = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPeminjam = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Skripsi");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/logo-50x50.png"))); // NOI18N
        jLabel1.setText("Detail Skripsi");

        labelTesisId.setFont(new java.awt.Font("Poppins Light", 0, 15)); // NOI18N
        labelTesisId.setText("ID : xxxxxxxxxx");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Judul");

        inputJudulTesis.setEditable(false);

        inputPengarang.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setText("Pengarang");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Tahun Terbit");

        inputTahunTerbit.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Jumlah Halaman");

        inputJumlahHalaman.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Banyaknya / Dipinjam / Tersedia");

        inputBanyaknya.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("Riwayat Peminjaman");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Cover");

        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/logo-250x250.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        labelCreatedAt.setText("Tgl. Ditambahkan: xx/xx/xxxx xx:xx:xx");

        labelUpdatedAt.setText("Terakhir Diubah: xx/xx/xxxx xx:xx:xx");

        btnEdit.setBackground(new java.awt.Color(255, 204, 0));
        btnEdit.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/pencil-30x30.png"))); // NOI18N
        btnEdit.setText("Ubah data");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
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

        tabelPeminjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Peminjam", "Tgl Pinjam", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelPeminjam);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(labelTesisId)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(inputTahunTerbit, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(inputPengarang)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inputBanyaknya)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(57, 57, 57)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inputJumlahHalaman)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(147, 147, 147)))))
                    .addComponent(inputJudulTesis)
                    .addComponent(jLabel10)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCreatedAt)
                    .addComponent(labelUpdatedAt)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(labelTesisId)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(inputJudulTesis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(inputJumlahHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, 0)
                                        .addComponent(inputTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, 0)
                                        .addComponent(inputBanyaknya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelCreatedAt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUpdatedAt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (backToMainMenu) {
            new Dashboard(userId, "Thesis").setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int result = this.dialog(new String[]{"Hapus data skripsi", "Yakin ingin menghapus skripsi?"});
        if (result == JOptionPane.YES_OPTION) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(this.tesis);
                entityManager.getTransaction().commit();
                this.peringatan("Berhasil menghapus data skripsi!");
                this.dispose();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                this.peringatan("Gagal menghapus data skripsi!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        this.backToMainMenu = false;
        this.dispose();
        new FormEditTesis(this.userId, this.tesis.getTesisId()).setVisible(true);
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
            java.util.logging.Logger.getLogger(ViewTesis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTesis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTesis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTesis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTesis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JTextField inputBanyaknya;
    private javax.swing.JTextField inputJudulTesis;
    private javax.swing.JTextField inputJumlahHalaman;
    private javax.swing.JTextField inputPengarang;
    private javax.swing.JTextField inputTahunTerbit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCreatedAt;
    private javax.swing.JLabel labelImage;
    private javax.swing.JLabel labelTesisId;
    private javax.swing.JLabel labelUpdatedAt;
    private javax.swing.JTable tabelPeminjam;
    // End of variables declaration//GEN-END:variables
}
