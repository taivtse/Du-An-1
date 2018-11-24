/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.quanly;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.Phim;
import poly.app.core.entities.SuatChieu;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameQLSuatChieu extends javax.swing.JFrame {

    Map<String, SuatChieu> suatChieuMap = new HashMap<>();
    Set<Phim> phimSet = new HashSet<>();

    /**
     * Creates new form FrameQLNhanVien
     *
     */
    public FrameQLSuatChieu() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Quản lý suẩt chiếu");
        reRenderUI();
        formWindowOpened(null);
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblSuatChieu);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
        
        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColoumnWidthByPersent(2, 20);
        tblRenderer.setColoumnWidthByPersent(4, 20);
        
        rdoDaChieu.setEnabled(false);
        rdoDangChieu.setEnabled(false);
        rdoSapChieu.setEnabled(false);
        dcNgayHienThi.setDate(new Date());
    }

    public JPanel getMainPanel() {
        formWindowOpened(null);
        return pnlMain;
    }

    public void loadAllToTable() {
        int i = 1;
        suatChieuMap.clear();
        phimSet.clear();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSuatChieu.getModel();
        defaultTableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (SuatChieu sc : new SuatChieuDaoImpl().getSuatChieuTheoNgay(dcNgayHienThi.getDate())) {
            String trangThai = sc.getGioBatDau().compareTo(new Date()) > 0 ? "Sắp chiếu" : "Đã chiếu";
            if (sc.getGioBatDau().compareTo(new Date()) < 0 && sc.getGioKetThuc().compareTo(new Date()) > 0) {
                trangThai = "Đang chiếu";
            }
            defaultTableModel.addRow(
                    new Object[]{
                        i++,
                        sc.getId(),
                        sc.getPhim().getTen(),
                        sc.getNgayChieu(),
                        sdf.format(sc.getGioBatDau()) + " - " + sdf.format(sc.getGioKetThuc()),
                        sc.getDinhDangPhim().getTen(),
                        sc.getPhim().getNgonNgu(),
                        sc.getPhongChieu().getId(),
                        trangThai
                    });
            suatChieuMap.put(sc.getId(), sc);
            phimSet.add(sc.getPhim());
        }
        this.tblSuatChieu.setModel(defaultTableModel);

    }

    public Map<String, SuatChieu> fillterByStatus(Map<String, SuatChieu> inputSearch) {
        Map<String, SuatChieu> searchResult = new HashMap<>();
        for (Entry<String, SuatChieu> entry : inputSearch.entrySet()) {
            SuatChieu sc = entry.getValue();

            String trangThai = sc.getGioBatDau().compareTo(new Date()) > 0 ? "Sắp chiếu" : "Đã chiếu";
            if (sc.getGioBatDau().compareTo(new Date()) < 0 && sc.getGioKetThuc().compareTo(new Date()) > 0) {
                trangThai = "Đang chiếu";
            }

            if ((trangThai.equals(rdoDangChieu.getText()) && rdoDangChieu.isSelected())
                    || (trangThai.equals(rdoDaChieu.getText()) && rdoDaChieu.isSelected())
                    || (trangThai.equals(rdoSapChieu.getText()) && rdoSapChieu.isSelected())) {

                searchResult.put(sc.getId(), sc);

            }
        }

        return searchResult;
    }

    private void loadTenPhimCombobox() {

        DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) cboPhim.getModel();
        defaultComboBoxModel.removeAllElements();
        for (Phim phim : phimSet) {
            defaultComboBoxModel.addElement(phim);
        }

    }

    private void loadFilterDataToTable(Map<String, SuatChieu> searchResult) {
        int i = 1;
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSuatChieu.getModel();
        defaultTableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (Entry<String, SuatChieu> entry : searchResult.entrySet()) {
            SuatChieu sc = entry.getValue();
            String trangThai = sc.getGioBatDau().compareTo(new Date()) > 0 ? "Sắp chiếu" : "Đã chiếu";
            if (sc.getGioBatDau().compareTo(new Date()) < 0 && sc.getGioKetThuc().compareTo(new Date()) > 0) {
                trangThai = "Đang chiếu";
            }

            defaultTableModel.addRow(
                    new Object[]{
                        i++,
                        sc.getId(),
                        sc.getPhim().getTen(),
                        sc.getNgayChieu(),
                        sdf.format(sc.getGioBatDau()) + " - " + sdf.format(sc.getGioKetThuc()),
                        sc.getDinhDangPhim().getTen(),
                        sc.getPhim().getNgonNgu(),
                        sc.getPhongChieu().getId(),
                        trangThai
                    });
        }

        this.tblSuatChieu.setModel(defaultTableModel);

    }

    private Map<String, SuatChieu> filterByName(Map<String, SuatChieu> inputSearch) {
        Map<String, SuatChieu> searchResult = new HashMap<>();
        for (Entry<String, SuatChieu> entry : inputSearch.entrySet()) {
            SuatChieu sc = entry.getValue();
            if (sc.getPhim().getTen().equals(cboPhim.getSelectedItem().toString())) {
                searchResult.put(sc.getId(), sc);
            }
        }

        return searchResult;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkTenPhim = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        dcNgayHienThi = new com.toedter.calendar.JDateChooser();
        chkTrangThai = new javax.swing.JCheckBox();
        rdoSapChieu = new javax.swing.JRadioButton();
        rdoDangChieu = new javax.swing.JRadioButton();
        rdoDaChieu = new javax.swing.JRadioButton();
        cboPhim = new javax.swing.JComboBox<>();
        cboPhongChieu = new javax.swing.JComboBox<>();
        chkPhongChieu = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTimeline = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuatChieu = new javax.swing.JTable();
        btnCollapse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 83, 104));
        jLabel1.setText("Tra cứu suất chiếu");

        chkTenPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        chkTenPhim.setText("Theo tên phim");
        chkTenPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTenPhimActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Ngày hiển thị");

        dcNgayHienThi.setOpaque(false);
        dcNgayHienThi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcNgayHienThiPropertyChange(evt);
            }
        });

        chkTrangThai.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        chkTrangThai.setText("Theo trạng thái");
        chkTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTrangThaiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoSapChieu);
        rdoSapChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoSapChieu.setSelected(true);
        rdoSapChieu.setText("Sắp chiếu");
        rdoSapChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSapChieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDangChieu);
        rdoDangChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoDangChieu.setText("Đang chiếu");
        rdoDangChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangChieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaChieu);
        rdoDaChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoDaChieu.setText("Đã chiếu");
        rdoDaChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaChieuActionPerformed(evt);
            }
        });

        cboPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhim.setEnabled(false);
        cboPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhimItemStateChanged(evt);
            }
        });
        cboPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhimActionPerformed(evt);
            }
        });
        cboPhim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboPhimPropertyChange(evt);
            }
        });

        cboPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhongChieu.setEnabled(false);

        chkPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        chkPhongChieu.setText("Theo phòng chiếu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPhongChieu)
                            .addComponent(chkTenPhim)
                            .addComponent(rdoDaChieu)
                            .addComponent(rdoDangChieu)
                            .addComponent(rdoSapChieu)
                            .addComponent(chkTrangThai))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcNgayHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(chkPhongChieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(chkTenPhim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(chkTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoSapChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoDangChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoDaChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnTimeline.setText("Xem lịch chiếu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimeline)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnTimeline)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblSuatChieu.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tblSuatChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã suất chiếu", "Tên phim", "Ngày chiếu", "Giờ chiếu", "Định dạng", "Ngôn ngữ", "Phòng chiếu", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSuatChieu.setRowHeight(20);
        tblSuatChieu.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblSuatChieu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSuatChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuatChieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuatChieu);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        btnCollapse.setBackground(new java.awt.Color(52, 83, 104));
        btnCollapse.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCollapse.setForeground(new java.awt.Color(255, 255, 255));
        btnCollapse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCollapse.setText("<<");
        btnCollapse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCollapse.setOpaque(true);
        btnCollapse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCollapseMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnCollapse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnCollapse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCollapseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCollapseMouseReleased
        if (btnCollapse.getText().equals("<<")) {
            jPanel2.setVisible(false);
            btnCollapse.setText(">>");
        } else {
            jPanel2.setVisible(true);
            btnCollapse.setText("<<");
        }
    }//GEN-LAST:event_btnCollapseMouseReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        loadAllToTable();
        
        loadAllToTable();
        loadTenPhimCombobox();


    }//GEN-LAST:event_formWindowOpened

    private void tblSuatChieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuatChieuMouseClicked
//        if (evt.getClickCount() >= 2) {
//            String id = tblSuatChieu.getValueAt(tblSuatChieu.getSelectedRow(), 1).toString();
//            new DialogCapNhatSuatPhim(this, true, suatChieuMap.get(id)).setVisible(true);
//            loadDataToTable(search());
//        }
    }//GEN-LAST:event_tblSuatChieuMouseClicked

    private void chkTenPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTenPhimActionPerformed
        cboPhim.setEnabled(chkTenPhim.isSelected());
        if (chkTenPhim.isSelected()) {
            if (chkTrangThai.isSelected()) {
                loadFilterDataToTable(filterByName(fillterByStatus(suatChieuMap)));
            } else {
                loadFilterDataToTable(filterByName(suatChieuMap));
            }
        }else{
            loadFilterDataToTable(fillterByStatus(suatChieuMap));
        }
    }//GEN-LAST:event_chkTenPhimActionPerformed

    private void dcNgayHienThiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcNgayHienThiPropertyChange
        loadAllToTable();
        loadTenPhimCombobox();
        cboPhim.setEditable(false);
        rdoDaChieu.setEnabled(false);
        rdoDangChieu.setEnabled(false);
        rdoSapChieu.setEnabled(false);
        // TODO add your handling code here:

    }//GEN-LAST:event_dcNgayHienThiPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void chkTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTrangThaiActionPerformed
        if (chkTrangThai.isSelected()) {
            rdoDaChieu.setEnabled(true);
            rdoDangChieu.setEnabled(true);
            rdoSapChieu.setEnabled(true);
            if (chkTenPhim.isSelected()) {
                loadFilterDataToTable(fillterByStatus(filterByName(suatChieuMap)));
            } else {
                loadFilterDataToTable(fillterByStatus(suatChieuMap));
            }

        } else {
            rdoDaChieu.setEnabled(false);
            rdoDangChieu.setEnabled(false);
            rdoSapChieu.setEnabled(false);
            loadAllToTable();
        }
    }//GEN-LAST:event_chkTrangThaiActionPerformed

    private void rdoSapChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSapChieuActionPerformed
        if (chkTenPhim.isSelected()) {
            loadFilterDataToTable(fillterByStatus(filterByName(suatChieuMap)));
        } else {
            loadFilterDataToTable(fillterByStatus(suatChieuMap));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSapChieuActionPerformed

    private void rdoDaChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaChieuActionPerformed
        if (chkTenPhim.isSelected()) {
            loadFilterDataToTable(fillterByStatus(filterByName(suatChieuMap)));
        } else {
            loadFilterDataToTable(fillterByStatus(suatChieuMap));
        }
// TODO add your handling code here:
    }//GEN-LAST:event_rdoDaChieuActionPerformed

    private void rdoDangChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangChieuActionPerformed
        if (chkTenPhim.isSelected()) {
            loadFilterDataToTable(fillterByStatus(filterByName(suatChieuMap)));
        } else {
            loadFilterDataToTable(fillterByStatus(suatChieuMap));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDangChieuActionPerformed

    private void cboPhimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboPhimPropertyChange

    }//GEN-LAST:event_cboPhimPropertyChange

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        if (chkTenPhim.isSelected()) {
            if (chkTrangThai.isSelected()) {
                loadFilterDataToTable(filterByName(fillterByStatus(suatChieuMap)));
            }
                else {
                loadFilterDataToTable(filterByName(suatChieuMap));
            }
        }

    }//GEN-LAST:event_cboPhimItemStateChanged

    private void cboPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhimActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameQLSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameQLSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameQLSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameQLSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameQLSuatChieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JButton btnTimeline;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPhim;
    private javax.swing.JComboBox<String> cboPhongChieu;
    private javax.swing.JCheckBox chkPhongChieu;
    private javax.swing.JCheckBox chkTenPhim;
    private javax.swing.JCheckBox chkTrangThai;
    private com.toedter.calendar.JDateChooser dcNgayHienThi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JRadioButton rdoDaChieu;
    private javax.swing.JRadioButton rdoDangChieu;
    private javax.swing.JRadioButton rdoSapChieu;
    private javax.swing.JTable tblSuatChieu;
    // End of variables declaration//GEN-END:variables
}
