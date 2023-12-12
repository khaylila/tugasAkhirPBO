/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Database.Borrows;
import Output.Submenu.Peminjaman.FormPeminjaman;
import Output.Submenu.Peminjaman.FormPeminjamanEdit;
import Output.Submenu.Peminjaman.FormPeminjamanSkripsi;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
public class Peminjaman extends javax.swing.JPanel {

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
    int userId;
    ArrayList<Borrows> listPeminjam = new ArrayList<>();

    /**
     * Creates new form Peminjaman
     */
    public Peminjaman() {
        initComponents();
    }

    public Peminjaman(int userId) {
        this.userId = userId;
        initComponents();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        filterMonth.setSelectedIndex((localDate.getMonthValue() - 1));
        filterYear.removeAllItems();
        for (int i = year; i >= 2023; i--) {
            filterYear.addItem(String.valueOf(year));
        }
        loadTable();
    }

    public void loadTable() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Borrows> resultQueryListBorrows = this.queryBorrow();
        listPeminjam.clear();
        DefaultTableModel model = (DefaultTableModel) tabelPinjam.getModel();
        model.setRowCount(0);
        int i = 1;
        for (Borrows data : resultQueryListBorrows) {
            Object[] baris = new Object[6];
            baris[0] = i++;
            listPeminjam.add(data);
            baris[1] = simpleDateFormat.format(data.getTanggalPinjam());
            baris[2] = simpleDateFormat.format(data.getRencanaKembali());
            baris[3] = (data.getTanggalKembali() != null ? simpleDateFormat.format(data.getTanggalKembali()) : "");
            if (data.getBooksId() != null) {
                baris[4] = data.getBooksId().getJudul();
            } else {
                baris[4] = data.getTesisId().getJudul();
            }
            baris[5] = data.getStudentId().getFullname();
            model.addRow(baris);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPinjam = new javax.swing.JTable();
        btnTambahPeminjam = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        inputSearch = new javax.swing.JTextField();
        searchBy = new javax.swing.JComboBox<>();
        filterYear = new javax.swing.JComboBox<>();
        filterMonth = new javax.swing.JComboBox<>();
        btnTambahPeminjamSkripsi = new javax.swing.JButton();
        filterPrintType = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1240, 625));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/logo-30x30.png"))); // NOI18N
        jLabel1.setText("Peminjaman Buku");

        tabelPinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Tanggal Pinjam", "Tanggal Rencana Kembali", "Tanggal Kembali", "Judul Buku/Skripsi", "Nama Mahasiswa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        tabelPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPinjamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPinjam);
        if (tabelPinjam.getColumnModel().getColumnCount() > 0) {
            tabelPinjam.getColumnModel().getColumn(0).setResizable(false);
            tabelPinjam.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabelPinjam.getColumnModel().getColumn(1).setResizable(false);
            tabelPinjam.getColumnModel().getColumn(2).setResizable(false);
            tabelPinjam.getColumnModel().getColumn(3).setResizable(false);
            tabelPinjam.getColumnModel().getColumn(4).setResizable(false);
            tabelPinjam.getColumnModel().getColumn(5).setResizable(false);
        }

        btnTambahPeminjam.setBackground(new java.awt.Color(255, 204, 0));
        btnTambahPeminjam.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahPeminjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/add-30x30.png"))); // NOI18N
        btnTambahPeminjam.setText("Tambah Buku");
        btnTambahPeminjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPeminjamActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(255, 204, 0));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/document-30x30.png"))); // NOI18N
        btnPrint.setText("Cetak");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        inputSearch.setEditable(false);
        inputSearch.setText("Search");
        inputSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputSearchFocusLost(evt);
            }
        });
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputSearchKeyTyped(evt);
            }
        });

        searchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Judul Buku", "Judul Skripsi", "Mahasiswa", "Angkatan" }));
        searchBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchByItemStateChanged(evt);
            }
        });

        filterYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1" }));

        filterMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        filterMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterMonthActionPerformed(evt);
            }
        });

        btnTambahPeminjamSkripsi.setBackground(new java.awt.Color(255, 204, 0));
        btnTambahPeminjamSkripsi.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahPeminjamSkripsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/add-30x30.png"))); // NOI18N
        btnTambahPeminjamSkripsi.setText("Tambah Skripsi");
        btnTambahPeminjamSkripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPeminjamSkripsiActionPerformed(evt);
            }
        });

        filterPrintType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Buku Terbanyak Dipinjam", "Skripsi Terbanyak Dipinjam" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnTambahPeminjam)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnTambahPeminjamSkripsi)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(filterPrintType, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPrint)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filterMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(filterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahPeminjam)
                        .addComponent(btnTambahPeminjamSkripsi)
                        .addComponent(filterPrintType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrint)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahPeminjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPeminjamActionPerformed
        // TODO add your handling code here:
        new FormPeminjaman(this, this.userId).setVisible(true);
    }//GEN-LAST:event_btnTambahPeminjamActionPerformed

    private void tabelPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPinjamMouseClicked
        // TODO add your handling code here:
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        int indexId = Integer.parseInt(target.getModel().getValueAt(row, 0).toString());
        new FormPeminjamanEdit(this, listPeminjam.get((indexId - 1)).getPinjamId()).setVisible(true);
    }//GEN-LAST:event_tabelPinjamMouseClicked

    private void filterMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterMonthActionPerformed

    private List<Borrows> queryBorrow() {
        String search = inputSearch.getText().trim();
        int filter = searchBy.getSelectedIndex();
        if (search.equalsIgnoreCase("search")) {
            search = "";
        }

        String query = "";
        if (filter == 0) {
            query = "Borrows.findAllWithMonthYear";
        } else if (filter == 1) {
            query = "Borrows.findByLikeBooksJudulWithMonthYear";
        } else if (filter == 2) {
            query = "Borrows.findByLikeThesisJudulWithMonthYear";
        } else if (filter == 3) {
            query = "Borrows.findByLikeMahasiswaFullnameWithMonthYear";
        } else if (filter == 4) {
            query = "Borrows.findByLikeMahasiswaAngkatanWithMonthYear";
        }

        TypedQuery<Borrows> queryListBorrows = null;
        queryListBorrows = entityManager.createNamedQuery(query, Borrows.class);

        if (filter != 0) {
            queryListBorrows.setParameter("parameter", "%" + search + "%");
        }
        queryListBorrows.setParameter("month", ((filterMonth.getSelectedIndex() + 1)));
        queryListBorrows.setParameter("year", Integer.valueOf(filterYear.getSelectedItem().toString()));
        return queryListBorrows.getResultList();
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        if (filterPrintType.getSelectedIndex() == 0) {
            List<Borrows> resultQueryListBorrows = this.queryBorrow();
            resultQueryListBorrows.get(0).getStudentId();

            Long timeStampNow = new Date().getTime() / 1000;

            Map<String, Object> parameters = new HashMap<>();
            int filter = searchBy.getSelectedIndex();
            String desc = "Berikut adalah laporan pada bulan " + filterMonth.getSelectedItem().toString() + " " + filterYear.getSelectedItem().toString();
            if (filter != 0) {
                if (!inputSearch.getText().equalsIgnoreCase("search") || !inputSearch.getText().isEmpty()) {
                    desc += " berdasarkan pencarian '" + inputSearch.getText() + "' pada " + searchBy.getSelectedItem();
                }
            }

            parameters.put("desc", desc);
            parameters.put("ts", timeStampNow);
            parameters.put("simpleDateFormat", new SimpleDateFormat("dd/MM/yyyy"));
            if (resultQueryListBorrows.size() > 0) {
                try {
                    String jrxmlFile = new String("src/Report/peminjaman.jrxml");
                    JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JRBeanCollectionDataSource(resultQueryListBorrows));
                    JasperViewer.viewReport(jp, false);
                } catch (JRException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                this.peringatan("Data tidak ditemukan!");
            }
        } else if (filterPrintType.getSelectedIndex() == 1) {
            Query resultSet = entityManager.createNativeQuery("SELECT books.judul, borrows.books_id, COUNT(borrows.books_id) AS jumlah_peminjaman FROM borrows JOIN books ON books.book_id = borrows.books_id WHERE EXTRACT(MONTH FROM borrows.created_at) = ? AND EXTRACT(YEAR FROM borrows.created_at) = ? GROUP BY borrows.books_id, books.judul ORDER BY jumlah_peminjaman DESC LIMIT 10;");
            resultSet.setParameter(1, filterMonth.getSelectedIndex() + 1);
            resultSet.setParameter(2, Integer.valueOf(filterYear.getSelectedItem().toString()));
            List<Object[]> resultList = resultSet.getResultList();
            if (resultList.size() > 0) {
                List<Map> rst = new ArrayList<>();
                for (Object[] result : resultList) {
                    Map<String, Object> fields = new HashMap<>();
                    fields.put("judul", result[0]);
                    fields.put("banyak_peminjaman", result[2]);
                    rst.add(fields);
                }

                Map<String, Object> parameters = new HashMap<>();
                String desc = "Berikut adalah laporan 10 buku dengan peminjaman terbanyak pada bulan '" + filterMonth.getSelectedItem().toString() + "' tahun '" + filterYear.getSelectedItem().toString() + "'.";
                parameters.put("desc", desc);

                try {
                    String jrxmlFile = new String("src/Report/reportTerbanyak.jrxml");
                    JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JRBeanCollectionDataSource(rst));
                    JasperViewer.viewReport(jp, false);
                } catch (JRException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                this.peringatan("Data tidak ditemukan!");
            }
        } else if (filterPrintType.getSelectedIndex() == 2) {
            Query resultSet = entityManager.createNativeQuery("SELECT thesis.judul, borrows.tesis_id, COUNT(borrows.tesis_id) AS jumlah_peminjaman FROM borrows JOIN thesis ON borrows.tesis_id = thesis.tesis_id  WHERE EXTRACT(MONTH FROM borrows.created_at) = ? AND EXTRACT(YEAR FROM borrows.created_at) = ? GROUP BY borrows.tesis_id, thesis.judul ORDER BY jumlah_peminjaman DESC LIMIT 10;");
            resultSet.setParameter(1, filterMonth.getSelectedIndex() + 1);
            resultSet.setParameter(2, Integer.valueOf(filterYear.getSelectedItem().toString()));
            List<Object[]> resultList = resultSet.getResultList();
            if (resultList.size() > 0) {
                List<Map> rst = new ArrayList<>();
                for (Object[] result : resultList) {
                    Map<String, Object> fields = new HashMap<>();
                    fields.put("judul", result[0]);
                    fields.put("banyak_peminjaman", result[2]);
                    rst.add(fields);
                }

                Map<String, Object> parameters = new HashMap<>();
                String desc = "Berikut adalah laporan 10 skripsi dengan peminjaman terbanyak hingga saat ini.";
                parameters.put("desc", desc);

                try {
                    String jrxmlFile = new String("src/Report/reportTerbanyak.jrxml");
                    JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JRBeanCollectionDataSource(rst));
                    JasperViewer.viewReport(jp, false);
                } catch (JRException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                this.peringatan("Data tidak ditemukan!");
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    private void inputSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            loadTable();
        }
    }//GEN-LAST:event_inputSearchKeyTyped

    private void btnTambahPeminjamSkripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPeminjamSkripsiActionPerformed
        // TODO add your handling code here:
        new FormPeminjamanSkripsi(this, this.userId).setVisible(true);
    }//GEN-LAST:event_btnTambahPeminjamSkripsiActionPerformed

    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusGained
        // TODO add your handling code here:
        if (searchBy.getSelectedIndex() != 0) {
            if (inputSearch.getText().equalsIgnoreCase("search")) {
                inputSearch.setText("");
            }
        }
    }//GEN-LAST:event_inputSearchFocusGained

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
        // TODO add your handling code here:
        if (searchBy.getSelectedIndex() != 0) {
            if (inputSearch.getText().equals("")) {
                inputSearch.setText("Search");
            }
        }
    }//GEN-LAST:event_inputSearchFocusLost

    private void searchByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchByItemStateChanged
        // TODO add your handling code here:
        int filter = searchBy.getSelectedIndex();
        if (filter == 0) {
            inputSearch.setEditable(false);
        } else {
            inputSearch.setEditable(true);
        }
        loadTable();

    }//GEN-LAST:event_searchByItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnTambahPeminjam;
    private javax.swing.JButton btnTambahPeminjamSkripsi;
    private javax.swing.JComboBox<String> filterMonth;
    private javax.swing.JComboBox<String> filterPrintType;
    private javax.swing.JComboBox<String> filterYear;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> searchBy;
    private javax.swing.JTable tabelPinjam;
    // End of variables declaration//GEN-END:variables
}
