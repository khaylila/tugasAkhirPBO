/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Database.Books;
import Database.Pengarang;
import Database.Tesis;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author milea
 */
public class PanelBukuNew extends javax.swing.JPanel {

    int id;

    /**
     * Creates new form panelBuku
     */
    public PanelBukuNew() {
        initComponents();
        tampil();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAdd = new javax.swing.JPanel();
        btnInsert = new javax.swing.JButton();
        inputISBN = new javax.swing.JTextField();
        inputJudul = new javax.swing.JTextField();
        inputSubJudul = new javax.swing.JTextField();
        inputPengarang = new javax.swing.JTextField();
        inputJumlahHalaman = new javax.swing.JTextField();
        inputPenerbit = new javax.swing.JTextField();
        inputTahunTerbit = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        inputPencarian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        inputTipeSearch = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(618, 383));
        setLayout(new java.awt.BorderLayout());

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));
        panelAdd.setPreferredSize(new java.awt.Dimension(618, 383));

        btnInsert.setBackground(new java.awt.Color(250, 204, 21));
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setText("Tambah");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        inputISBN.setText("Nomor ISBN");

        inputJudul.setText("Judul Buku");

        inputSubJudul.setText("Sub Judul (Optional)");
        inputSubJudul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSubJudulActionPerformed(evt);
            }
        });

        inputPengarang.setText("Pengarang (Pisahkan dengan koma)");

        inputJumlahHalaman.setText("Jumlah Halaman");

        inputPenerbit.setText("Penerbit");

        inputTahunTerbit.setText("Tahun Terbit");

        btnUpdate.setBackground(new java.awt.Color(250, 204, 21));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(250, 204, 21));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        inputPencarian.setText("Cari Sesuatu");
        inputPencarian.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 204, 21), 1, true));
        inputPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPencarianActionPerformed(evt);
            }
        });
        inputPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputPencarianKeyTyped(evt);
            }
        });

        tabelBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "ISBN", "Judul", "Pengarang", "Penerbit", "Thn Terbit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelBuku.setShowGrid(false);
        tabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBuku);

        btnPrint.setBackground(new java.awt.Color(250, 204, 21));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        inputTipeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Judul", "ISBN", "Penerbit", "Pengarang", "Tahun Terbit" }));

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)
                        .addGap(42, 42, 42)
                        .addComponent(inputTipeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputPencarian))
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(inputISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputSubJudul)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(inputPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputJumlahHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(inputPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(inputSubJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputJumlahHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(inputPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint)
                    .addComponent(inputTipeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(panelAdd, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tampil() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Books> querySelectAll = entityManager.createQuery("SELECT DISTINCT b FROM Books b JOIN FETCH b.pengarangCollection", Books.class);
        List<Books> results = querySelectAll.getResultList();

        DefaultTableModel model = (DefaultTableModel) tabelBuku.getModel();
        model.setRowCount(0);
        for (Books data : results) {
            String pengarangLengkap = "";
            int i = 1;
            for (Pengarang pgr : data.getPengarangCollection()) {
                pengarangLengkap += pgr.getName();
                if (data.getPengarangCollection().size() > i) {
                    pengarangLengkap += ", ";
                }

                i++;
            }
            Object[] baris = new Object[6];
            baris[0] = data.getBookId();
            baris[1] = data.getIsbn();
            baris[2] = data.getJudul();
            baris[3] = pengarangLengkap;
            baris[4] = data.getPenerbit();
            baris[5] = data.getTahunTerbit();
            model.addRow(baris);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    private void resetField() {
        inputISBN.setText("Nomor ISBN");
        inputJudul.setText("Judul Buku");
        inputSubJudul.setText("Sub Judul (Optional)");
        inputPengarang.setText("Pengarang (Pisahkan dengan koma)");
        inputJumlahHalaman.setText("Jumlah Halaman");
        inputPenerbit.setText("Penerbit");
        inputTahunTerbit.setText("Tahun Terbit");

    }

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        String isbn = inputISBN.getText();
        String judul = inputJudul.getText();
        String subJudul = inputSubJudul.getText();
        String pengarang = inputPengarang.getText();
        int jumlahHalaman = Integer.parseInt(inputJumlahHalaman.getText());
        String penerbit = inputPenerbit.getText();
        int tahunTerbit = Integer.parseInt(inputTahunTerbit.getText());
//        Date time = new Date();

        if (!isbn.isEmpty() && !judul.isEmpty() && !penerbit.isEmpty() && !pengarang.isEmpty() && tahunTerbit > 0 && jumlahHalaman > 0) {
            EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
            try {
                entityManager.getTransaction().begin();
                Books daftarBuku = new Books();
                daftarBuku.setIsbn(isbn);
                daftarBuku.setJudul(judul);
                if (!subJudul.equals("Sub Judul (Optional)")) {
                    daftarBuku.setSubJudul(subJudul);
                }
                daftarBuku.setJumlahHalaman(jumlahHalaman);
                daftarBuku.setPenerbit(penerbit);
                daftarBuku.setTahunTerbit(tahunTerbit);
//                daftarBuku.setCreatedAt(time);
//                daftarBuku.setUpdatedAt(time);
                entityManager.persist(daftarBuku);

                TypedQuery<Books> query = entityManager.createNamedQuery("Books.findLastById", Books.class);
                Books bookLastId = query.setMaxResults(1).getSingleResult();
                String[] pengarangs = pengarang.split(",");
                for (String pgr : pengarangs) {
                    Pengarang tbPengarang = new Pengarang();
                    tbPengarang.setBookId(bookLastId);
                    tbPengarang.setName(pgr.trim());
                    entityManager.persist(tbPengarang);
                }
                entityManager.getTransaction().commit();

                this.peringatan("Berhasil menambahkan data!");
            } catch (Exception e) {
                this.peringatan("Tambah data gagal. Pesan: " + e.getMessage());
            }
            entityManager.close();
        } else {
            this.peringatan("Input Data Buku Gagal");
        }
        this.tampil();
        this.resetField();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (this.id != 0) {
            String isbn = inputISBN.getText();
            String judul = inputJudul.getText();
            String subJudul = inputSubJudul.getText();
            String pengarang = inputPengarang.getText();
            int jumlahHalaman = Integer.parseInt(inputJumlahHalaman.getText());
            String penerbit = inputPenerbit.getText();
            int tahunTerbit = Integer.parseInt(inputTahunTerbit.getText());
//            Date time = new Date();
            if (!isbn.isEmpty() && !judul.isEmpty() && !penerbit.isEmpty() && !pengarang.isEmpty() && tahunTerbit > 0 && jumlahHalaman > 0) {
                EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    TypedQuery<Books> query = entityManager.createNamedQuery("Books.findByBookId", Books.class);
                    query.setParameter("bookId", this.id);
                    Books book = query.setMaxResults(1).getSingleResult();

                    book.setIsbn(isbn);
                    book.setJudul(judul);
                    if (!subJudul.equals("Sub Judul (Optional)")) {
                        book.setSubJudul(subJudul);
                    }
                    book.setJumlahHalaman(jumlahHalaman);
                    book.setPenerbit(penerbit);
                    book.setTahunTerbit(tahunTerbit);
//                    daftarBuku.setCreatedAt(time);
//                    daftarBuku.setUpdatedAt(time);
                    entityManager.merge(book);

                    TypedQuery<Pengarang> queryGetPengarang = entityManager.createNamedQuery("Pengarang.findNamaByBookId", Pengarang.class);
                    queryGetPengarang.setParameter("bookId", book);
                    List<Pengarang> pengarangss = queryGetPengarang.getResultList();
                    for (Pengarang peng : pengarangss) {
                        entityManager.remove(peng);
                    }

                    String[] pengarangs = pengarang.split(",");
                    for (String pgr : pengarangs) {
                        Pengarang tbPengarang = new Pengarang();
                        tbPengarang.setBookId(book);
                        tbPengarang.setName(pgr.trim());
                        entityManager.persist(tbPengarang);
                    }
                    entityManager.getTransaction().commit();
                    this.peringatan("Berhasil mengubah data!");
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    this.peringatan("Gagal mengubah data. Pesan peringantan: " + e.getMessage());
                }
                entityManager.close();
            } else {
                this.peringatan("Update Data Mahasiswa Gagal");
            }
            this.tampil();
            this.resetField();
        } else {
            this.peringatan("Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (this.id > 0) {
            EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
            try {
                entityManager.getTransaction().begin();
                TypedQuery<Books> query = entityManager.createNamedQuery("Books.findByBookId", Books.class);
                query.setParameter("bookId", this.id);
                Books daftarBuku = query.setMaxResults(1).getSingleResult();
                entityManager.remove(daftarBuku);
                entityManager.getTransaction().commit();
                this.peringatan("Berhasil menghapus data!");
            } catch (Exception e) {
                this.peringatan("Gagal menghapus data. Pesan: " + e.getMessage());
            }
            entityManager.close();
        } else {
            this.peringatan("Pilih data terlebih dahulu!");
        }
        this.resetField();
        this.tampil();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        int selectedIndex = inputTipeSearch.getSelectedIndex();
        String queryTypeSearch = "Books.findByJudulLike";
        String column = "judul";
        String searchBy = "Judul";
        if (selectedIndex == 1) {
            queryTypeSearch = "Books.findByISBNLike";
            column = "isbn";
            searchBy = "ISBN";
        } else if (selectedIndex == 2) {
            queryTypeSearch = "Books.findByPenerbitLike";
            column = "penerbit";
            searchBy = "Penerbit";
        } else if (selectedIndex == 4) {
            queryTypeSearch = "Books.findByTahunTerbit";
            column = "tahunTerbit";
            searchBy = "Tahun Terbit";
        }
        String kataKunci = inputPencarian.getText();
        if (kataKunci.equalsIgnoreCase("Cari Sesuatu")) {
            kataKunci = "";
        }

        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Books> query;
        if (selectedIndex == 3) {
            query = entityManager.createQuery("SELECT b FROM Books b JOIN b.pengarangCollection p WHERE UPPER(p.name) LIKE UPPER(:namaPengarang)", Books.class);
            query.setParameter("namaPengarang", "%" + kataKunci + "%");
            searchBy = "Pengarang";
        } else {
            query = entityManager.createNamedQuery(queryTypeSearch, Books.class);
            if (selectedIndex == 4) {
                query.setParameter(column, Integer.valueOf(kataKunci));
            } else {
                query.setParameter(column, "%" + kataKunci + "%");
            }
        }
        List<Books> results = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        List<HashMap<String, Object>> newResults = new ArrayList<>();
        for (Books buku : results) {
            System.out.println(buku.toString());
            HashMap<String, Object> hasilBuku = new HashMap<>();
            hasilBuku.put("judul", buku.getJudul());
            
            String pengarang = "";
            int i = 1;
            for (Pengarang pgr : buku.getPengarangCollection()) {
                pengarang += pgr.getName();
                if (buku.getPengarangCollection().size() > i) {
                    pengarang += ", ";
                }

                i++;
            }
            
            hasilBuku.put("pengarang", pengarang);
            hasilBuku.put("penerbit", buku.getPenerbit());
            hasilBuku.put("tahunTerbit", String.valueOf(buku.getTahunTerbit()));
            hasilBuku.put("jumlahHalaman", String.valueOf(buku.getJumlahHalaman()));
            newResults.add(hasilBuku);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("querySearch", kataKunci);
        parameters.put("searchBy", searchBy);

        try {
            String jrxmlFile = new String("src/Report/reportBuku.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JRBeanCollectionDataSource(newResults));
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(PanelSkripsi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PanelSkripsi.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void inputSubJudulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSubJudulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSubJudulActionPerformed

    private void tabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBukuMouseClicked
        // TODO add your handling code here:
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        this.id = Integer.parseInt(target.getModel().getValueAt(row, 0).toString());

        EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Books> query = entityManager.createNamedQuery("Books.findByBookId", Books.class);
        query.setParameter("bookId", this.id);
        Books daftarBuku = query.setMaxResults(1).getSingleResult();

        TypedQuery<Pengarang> queryGetPengarang = entityManager.createNamedQuery("Pengarang.findNamaByBookId", Pengarang.class);
        queryGetPengarang.setParameter("bookId", daftarBuku);
        List<Pengarang> pengarangs = queryGetPengarang.getResultList();
        String pengarangLengkap = "";

        int i = 1;
        for (Pengarang pengarang : pengarangs) {
            pengarangLengkap += pengarang.getName();
            if (pengarangs.size() > i) {
                pengarangLengkap += ", ";
            }

            i++;
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        inputISBN.setText(daftarBuku.getIsbn());
        inputJudul.setText(daftarBuku.getJudul());
        inputSubJudul.setText(daftarBuku.getSubJudul());
        inputPengarang.setText(pengarangLengkap);
        inputJumlahHalaman.setText(String.valueOf(daftarBuku.getJumlahHalaman()));
        inputPenerbit.setText(daftarBuku.getPenerbit());
        inputTahunTerbit.setText(String.valueOf(daftarBuku.getTahunTerbit()));
    }//GEN-LAST:event_tabelBukuMouseClicked

    private void inputPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPencarianKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            int selectedIndex = inputTipeSearch.getSelectedIndex();
            String queryTypeSearch = "Books.findByJudulLike";
            String column = "judul";
            if (selectedIndex == 1) {
                queryTypeSearch = "Books.findByISBNLike";
                column = "isbn";
            } else if (selectedIndex == 2) {
                queryTypeSearch = "Books.findByPenerbitLike";
                column = "penerbit";
            } else if (selectedIndex == 3) {

            } else if (selectedIndex == 4) {
                queryTypeSearch = "Books.findByTahunTerbit";
                column = "tahunTerbit";
            }
            String kataKunci = inputPencarian.getText();

            EntityManager entityManager = Persistence.createEntityManagerFactory("tugasAkhirPBOPU").createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<Books> query;
            if (selectedIndex == 3) {
                query = entityManager.createQuery("SELECT b FROM Books b JOIN b.pengarangCollection p WHERE UPPER(p.name) LIKE UPPER(:namaPengarang)", Books.class);
                query.setParameter("namaPengarang", "%" + kataKunci + "%");
            } else {
                query = entityManager.createNamedQuery(queryTypeSearch, Books.class);
                if (selectedIndex == 4) {
                    query.setParameter(column, Integer.valueOf(kataKunci));
                } else {
                    query.setParameter(column, "%" + kataKunci + "%");
                }
            }
            List<Books> results = query.getResultList();

            DefaultTableModel model = (DefaultTableModel) tabelBuku.getModel();
            model.setRowCount(0);
            for (Books data : results) {
                String pengarangLengkap = "";
                int i = 1;
                for (Pengarang pgr : data.getPengarangCollection()) {
                    pengarangLengkap += pgr.getName();
                    if (data.getPengarangCollection().size() > i) {
                        pengarangLengkap += ", ";
                    }

                    i++;
                }
                Object[] baris = new Object[6];
                baris[0] = data.getBookId();
                baris[1] = data.getIsbn();
                baris[2] = data.getJudul();
                baris[3] = pengarangLengkap;
                baris[4] = data.getPenerbit();
                baris[5] = data.getTahunTerbit();
                model.addRow(baris);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }//GEN-LAST:event_inputPencarianKeyTyped

    private void inputPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPencarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPencarianActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField inputISBN;
    private javax.swing.JTextField inputJudul;
    private javax.swing.JTextField inputJumlahHalaman;
    private javax.swing.JTextField inputPencarian;
    private javax.swing.JTextField inputPenerbit;
    private javax.swing.JTextField inputPengarang;
    private javax.swing.JTextField inputSubJudul;
    private javax.swing.JTextField inputTahunTerbit;
    private javax.swing.JComboBox<String> inputTipeSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JTable tabelBuku;
    // End of variables declaration//GEN-END:variables
}
