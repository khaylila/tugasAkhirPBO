/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Database.Books;
import Database.Categories;
import Database.Thesis;
import Output.Submenu.Buku.ViewBuku;
import Output.Submenu.FormKategori;
import Output.Tesis.ViewTesis;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author milea
 */
public class Dashboard extends javax.swing.JPanel {

    private int userId;
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("TraineTugasAkhirPBOPU").createEntityManager();
    List<Integer> listPinjam = new ArrayList<>();
    List<Books> listBuku = new ArrayList<>();
    List<Thesis> listSkripsi = new ArrayList<>();
    Object obj;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
//        loadSizeBooks();
//        loadSizeSkripsi();
//        loadSizePeminjaman();
//        loadSizePeminjam();
//        loadSizeKategori();
//        loadPeminjaman();
//        loadKategoriBuku();
//        loadBuku();
//        loadSkripsi();
    }

    public Dashboard(Object obj, int userId) {
        this.userId = userId;
        this.obj = obj;
        initComponents();
        loadSizeBooks();
        loadSizeSkripsi();
        loadSizePeminjaman();
        loadSizePeminjam();
        loadSizeKategori();
        loadPeminjaman();
        loadKategoriBuku();
        loadBuku();
        loadSkripsi();
    }

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    private void loadSizeBooks() {
        TypedQuery<Long> queryJumlahBuku = entityManager.createQuery("SELECT COUNT(b.bookId) FROM Books b", Long.class);
        Long result = queryJumlahBuku.getSingleResult();
        labelBanyakBuku.setText(String.format("%04d", result));
    }

    private void loadSizeSkripsi() {
        TypedQuery<Long> queryJumlahSkripsi = entityManager.createQuery("SELECT COUNT(t.tesisId) FROM Thesis t", Long.class);
        Long result = queryJumlahSkripsi.getSingleResult();
        labelBanyakSkripsi.setText(String.format("%04d", result));
    }

    private void loadSizePeminjaman() {
        TypedQuery<Long> queryJumlahPeminjaman = entityManager.createQuery("SELECT COUNT(b.pinjamId) FROM Borrows b", Long.class);
        Long result = queryJumlahPeminjaman.getSingleResult();
        labelPeminjaman.setText(String.format("%04d", result));
    }

    private void loadSizePeminjam() {
        TypedQuery<Long> queryJumlahPeminjam = entityManager.createQuery("SELECT COUNT(s.studentId) FROM Students s", Long.class);
        Long result = queryJumlahPeminjam.getSingleResult();
        labelStudent.setText(String.format("%04d", result));
    }

    private void loadSizeKategori() {
        TypedQuery<Long> queryJumlahKategori = entityManager.createQuery("SELECT COUNT(c.kategoriId) FROM Categories c", Long.class);
        Long result = queryJumlahKategori.getSingleResult();
        labelKategori.setText(String.format("%04d", result));
    }

    private void loadPeminjaman() {
        Query resultSet = entityManager.createNativeQuery("SELECT books.judul, borrows.books_id, borrows.tesis_id, COUNT(borrows.books_id) AS jumlah_peminjaman FROM borrows JOIN books ON books.book_id = borrows.books_id GROUP BY borrows.books_id, books.judul, borrows.tesis_id UNION SELECT thesis.judul, borrows.books_id, borrows.tesis_id, COUNT(borrows.tesis_id) AS jumlah_peminjaman FROM borrows JOIN thesis ON borrows.tesis_id = thesis.tesis_id GROUP BY borrows.tesis_id, thesis.judul, borrows.books_id ORDER BY jumlah_peminjaman DESC LIMIT 20;");
        List<Object[]> resultList = resultSet.getResultList();
        DefaultTableModel model = (DefaultTableModel) tabelPeminjaman.getModel();
        model.setRowCount(0);
        int i = 1;
        for (Object[] result : resultList) {
            if (result[1] != null) {
                listPinjam.add(Integer.valueOf(result[1].toString()));
            } else {
                listPinjam.add(Integer.valueOf(result[2].toString()));
            }

            Object[] baris = new Object[4];
            baris[0] = i++;
            baris[1] = result[0];
            baris[2] = result[1] == null ? "Skripsi" : "Buku";
            baris[3] = result[3];

            model.addRow(baris);
        }

    }

    private void loadKategoriBuku() {
        Query resultSet = entityManager.createNativeQuery("SELECT categories.nama, COUNT(books_categories.kategori_id) AS banyaknya_buku FROM categories JOIN books_categories ON categories.kategori_id = books_categories.kategori_id GROUP BY books_categories.kategori_id, categories.nama;");
        List<Object[]> resultList = resultSet.getResultList();
        DefaultTableModel model = (DefaultTableModel) tabelKategori.getModel();
        model.setRowCount(0);
        for (Object[] result : resultList) {
            Object[] baris = new Object[2];
            baris[0] = result[0];
            baris[1] = result[1];
            model.addRow(baris);
        }
    }

    private void loadBuku() {
        TypedQuery<Books> resultSet = entityManager.createNamedQuery("Books.findAllDesc", Books.class);
        DefaultTableModel model = (DefaultTableModel) tabelBuku.getModel();
        int i = 1;
        model.setRowCount(0);
        for (Books result : resultSet.setMaxResults(20).getResultList()) {
            listBuku.add(result);
            Object[] baris = new Object[5];
            baris[0] = i++;
            baris[1] = result.getIsbn();
            baris[2] = result.getJudul();
            baris[3] = result.getPenerbit();
            baris[4] = new SimpleDateFormat("dd/MM/yyyy").format(result.getCreatedAt());

            model.addRow(baris);
        }
    }

    private void loadSkripsi() {
        TypedQuery<Thesis> resultSet = entityManager.createNamedQuery("Thesis.findAllDesc", Thesis.class);
        DefaultTableModel model = (DefaultTableModel) tabelSkripsi.getModel();
        int i = 1;
        model.setRowCount(0);
        for (Thesis result : resultSet.setMaxResults(20).getResultList()) {
            listSkripsi.add(result);
            Object[] baris = new Object[5];
            baris[0] = i++;
            baris[1] = result.getJudul();
            baris[2] = result.getStudentId().getFullname();
            baris[3] = result.getTahunTerbit();
            baris[4] = new SimpleDateFormat("dd/MM/yyyy").format(result.getCreatedAt());

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

        jPanel1 = new javax.swing.JPanel();
        labelBanyakBuku = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelBanyakSkripsi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelPeminjaman = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPeminjaman = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelSkripsi = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        labelStudent = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelKategori = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1240, 625));
        setMinimumSize(new java.awt.Dimension(1240, 625));
        setPreferredSize(new java.awt.Dimension(1240, 625));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(160, 75));
        jPanel1.setMinimumSize(new java.awt.Dimension(160, 75));
        jPanel1.setPreferredSize(new java.awt.Dimension(160, 75));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        labelBanyakBuku.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        labelBanyakBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/book-50x50-y.png"))); // NOI18N
        labelBanyakBuku.setText("0000");
        labelBanyakBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBanyakBukuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelBanyakBuku)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelBanyakBuku)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(160, 75));
        jPanel3.setMinimumSize(new java.awt.Dimension(160, 75));
        jPanel3.setPreferredSize(new java.awt.Dimension(160, 75));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        labelBanyakSkripsi.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        labelBanyakSkripsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/thesis-50x50.png"))); // NOI18N
        labelBanyakSkripsi.setText("0000");
        labelBanyakSkripsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBanyakSkripsiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelBanyakSkripsi)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelBanyakSkripsi)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(160, 75));
        jPanel4.setMinimumSize(new java.awt.Dimension(160, 75));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        labelPeminjaman.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        labelPeminjaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/peminjaman-50x50.png"))); // NOI18N
        labelPeminjaman.setText("0000");
        labelPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPeminjamanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelPeminjaman)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelPeminjaman)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(890, 69));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setText("Peminjaman terbanyak");

        tabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Judul", "Jenis", "Banyak Peminjaman"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        tabelPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPeminjaman);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Kategori Buku");

        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kategori", "Banyak Buku"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelKategori);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(590, 147));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setText("Buku (Terakhir ditambahkan)");

        tabelBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "ISBN", "Judul", "Penerbit", "Tgl Ditambahkan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBukuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelBuku);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(590, 147));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setText("Skripsi (Terakhir ditambahkan)");

        tabelSkripsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Judul", "Pengarang", "Tahun Terbit", "Tgl Ditambahkan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tabelSkripsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSkripsiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelSkripsi);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMaximumSize(new java.awt.Dimension(160, 75));
        jPanel8.setMinimumSize(new java.awt.Dimension(160, 75));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        labelStudent.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        labelStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/peminjam-50x50.png"))); // NOI18N
        labelStudent.setText("0000");
        labelStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelStudentMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelStudent)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelStudent)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(160, 75));
        jPanel9.setMinimumSize(new java.awt.Dimension(160, 75));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        labelKategori.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        labelKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/kategori-50x50.png"))); // NOI18N
        labelKategori.setText("0000");
        labelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelKategoriMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelKategori)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelKategori)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBukuMouseClicked
        // TODO add your handling code here:
        try {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int index = Integer.parseInt(target.getModel().getValueAt(row, 0).toString());
            new ViewBuku(userId, listBuku.get((index - 1)).getBookId(), "dashboard").setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_tabelBukuMouseClicked

    private void tabelSkripsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSkripsiMouseClicked
        // TODO add your handling code here:
        try {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int index = Integer.parseInt(target.getModel().getValueAt(row, 0).toString());
            new ViewTesis(userId, listSkripsi.get((index - 1)).getTesisId(), "dashboard").setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_tabelSkripsiMouseClicked

    private void tabelPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPeminjamanMouseClicked
        // TODO add your handling code here:
        try {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int index = Integer.parseInt(target.getModel().getValueAt(row, 0).toString());
            String jenis = target.getModel().getValueAt(row, 2).toString();
            if (jenis.equalsIgnoreCase("buku")) {
                new ViewBuku(userId, listPinjam.get((index - 1)), "dashboard").setVisible(true);
            } else {
                new ViewTesis(userId, listPinjam.get((index - 1)), "dashboard").setVisible(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_tabelPeminjamanMouseClicked

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        // TODO add your handling code here:
        try {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            String kategori = target.getModel().getValueAt(row, 0).toString();

            if (this.obj instanceof Output.Submenu.Dashboard) {
                ((Output.Submenu.Dashboard) obj).redirectToBuku(userId, kategori);
            } else {
                this.peringatan("Terjadi kesalahan!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        }
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void labelBanyakBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanyakBukuMouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToBuku(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_labelBanyakBukuMouseClicked

    private void labelBanyakSkripsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanyakSkripsiMouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToSkripsi(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_labelBanyakSkripsiMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToPeminjaman(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToBuku(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToSkripsi(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToPeminjam(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            new FormKategori(userId).setVisible(true);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_jPanel9MouseClicked

    private void labelPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPeminjamanMouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToPeminjaman(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_labelPeminjamanMouseClicked

    private void labelStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelStudentMouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            ((Output.Submenu.Dashboard) obj).redirectToPeminjam(userId);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_labelStudentMouseClicked

    private void labelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKategoriMouseClicked
        // TODO add your handling code here:
        if (this.obj instanceof Output.Submenu.Dashboard) {
            new FormKategori(userId).setVisible(true);
        } else {
            this.peringatan("Terjadi kesalahan!");
        }
    }//GEN-LAST:event_labelKategoriMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelBanyakBuku;
    private javax.swing.JLabel labelBanyakSkripsi;
    private javax.swing.JLabel labelKategori;
    private javax.swing.JLabel labelPeminjaman;
    private javax.swing.JLabel labelStudent;
    private javax.swing.JTable tabelBuku;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JTable tabelPeminjaman;
    private javax.swing.JTable tabelSkripsi;
    // End of variables declaration//GEN-END:variables
}
