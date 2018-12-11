/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.quanly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.PhongChieuDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.Phim;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;
import poly.app.core.helper.DateHelper;
import poly.app.ui.custom.ClosableTabbedPane;
import poly.app.ui.dialogs.capnhat.DialogCapNhatSuatChieu;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameQLSuatChieu extends javax.swing.JFrame implements ClosableTabbedPane.ClosableTabbedPaneMethod{

    List<SuatChieu> suatChieuList = new ArrayList<>();
    Map<String, Phim> phimMap = new TreeMap<>();

    /**
     * Creates new form FrameQLNhanVien
     *
     */
    public FrameQLSuatChieu() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Quản lý suất chiếu");
        reRenderUI();
        loadDataToCboPhongChieu();
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblSuatChieu);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();

        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColoumnWidthByPersent(2, 25);
        tblRenderer.setColoumnWidthByPersent(4, 20);

        tblRenderer.setColumnAlignment(1, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(3, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(4, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(5, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(7, TableRendererUtil.CELL_ALIGN_CENTER);

        rdoDaChieu.setEnabled(false);
        rdoDangChieu.setEnabled(false);
        rdoSapChieu.setEnabled(false);
        dcNgayHienThi.setDate(new Date());
    }

    public JPanel getMainPanel() {
        synchronizedData();
        return this.pnlMain;
    }
    
    public void synchronizedData(){
        this.loadAllToTable();
        this.loadTenPhimCombobox();
        loadFilterDataToTable(searchAdvanced());
    }

    private void loadDataToCboPhongChieu() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboPhongChieu.getModel();
        new PhongChieuDaoImpl().getAll().forEach((phongChieu) -> {
            comboBoxModel.addElement(phongChieu);
        });
    }

    public void loadAllToTable() {
        int i = 1;
        suatChieuList.clear();
        phimMap.clear();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSuatChieu.getModel();
        defaultTableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        PhongChieu phongChieu = (PhongChieu) cboPhongChieu.getSelectedItem();
        for (SuatChieu sc : new SuatChieuDaoImpl().getSuatChieuByNgayVaByPhong(dcNgayHienThi.getDate(), phongChieu)) {
            String trangThai = sc.getGioBatDau().compareTo(new Date()) > 0 ? "Sắp chiếu" : "Đã chiếu";
            if (sc.getGioBatDau().compareTo(new Date()) < 0 && sc.getGioKetThuc().compareTo(new Date()) > 0) {
                trangThai = "Đang chiếu";
            }
            defaultTableModel.addRow(
                    new Object[]{
                        i++,
                        sc.getId(),
                        sc.getPhim().getTen(),
                        DateHelper.OUT_DATE_FORMAT.format(sc.getNgayChieu()),
                        sdf.format(sc.getGioBatDau()) + " - " + sdf.format(sc.getGioKetThuc()),
                        sc.getDinhDangPhim().getId(),
                        sc.getPhim().getNgonNgu(),
                        sc.getPhongChieu().getId(),
                        trangThai
                    });

            suatChieuList.add(sc);
            phimMap.put(sc.getPhim().getId(), sc.getPhim());
        }
        this.tblSuatChieu.setModel(defaultTableModel);
    }

    public List<SuatChieu> searchAdvanced() {
        List<SuatChieu> searchResult = new ArrayList<>();
        for (SuatChieu sc : suatChieuList) {
            String trangThai = sc.getGioBatDau().compareTo(new Date()) > 0 ? "Sắp chiếu" : "Đã chiếu";
            if (sc.getGioBatDau().compareTo(new Date()) < 0 && sc.getGioKetThuc().compareTo(new Date()) > 0) {
                trangThai = "Đang chiếu";
            }

            boolean isCanAdd = true;

            if (chkTenPhim.isSelected() && !cboPhim.getSelectedItem().equals(sc.getPhim())) {
                isCanAdd = false;
            }

            if (chkTrangThai.isSelected()) {
                if (!trangThai.equals(rdoDangChieu.getText()) && rdoDangChieu.isSelected()) {
                    isCanAdd = false;
                } else if (!trangThai.equals(rdoDaChieu.getText()) && rdoDaChieu.isSelected()) {
                    isCanAdd = false;
                } else if (!trangThai.equals(rdoSapChieu.getText()) && rdoSapChieu.isSelected()) {
                    isCanAdd = false;
                }
            }

            if (isCanAdd) {
                searchResult.add(sc);
            }
        }

        return searchResult;
    }

    private void loadTenPhimCombobox() {
        DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) cboPhim.getModel();
        defaultComboBoxModel.removeAllElements();
        for (Map.Entry<String, Phim> entry : phimMap.entrySet()) {
            defaultComboBoxModel.addElement(entry.getValue());
        }
    }

    private void loadFilterDataToTable(List<SuatChieu> searchResult) {
        int i = 1;
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSuatChieu.getModel();
        defaultTableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (SuatChieu sc : searchResult) {
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
                        sc.getDinhDangPhim().getId(),
                        sc.getPhim().getNgonNgu(),
                        sc.getPhongChieu().getId(),
                        trangThai
                    });
        }

        this.tblSuatChieu.setModel(defaultTableModel);

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
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTimeline = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuatChieu = new javax.swing.JTable();
        btnCollapse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
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

        dcNgayHienThi.setDateFormatString("dd-MM-yyyy");
        dcNgayHienThi.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
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
        rdoSapChieu.setEnabled(false);
        rdoSapChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSapChieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDangChieu);
        rdoDangChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoDangChieu.setText("Đang chiếu");
        rdoDangChieu.setEnabled(false);
        rdoDangChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangChieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaChieu);
        rdoDaChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoDaChieu.setText("Đã chiếu");
        rdoDaChieu.setEnabled(false);
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

        cboPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhongChieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhongChieuItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Phòng chiếu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayHienThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPhongChieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(rdoDaChieu)
                            .addComponent(rdoDangChieu)
                            .addComponent(rdoSapChieu)
                            .addComponent(chkTrangThai)
                            .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkTenPhim))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcNgayHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
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
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnTimeline.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnTimeline.setText("Xem lịch chiếu");
        btnTimeline.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimelineActionPerformed(evt);
            }
        });

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
                .addGap(15, 15, 15)
                .addComponent(btnTimeline, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblSuatChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblSuatChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã suất chiếu", "Tên phim", "Ngày chiếu", "Giờ chiếu", "Định dạng", "Ngôn ngữ", "Phòng chiếu", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSuatChieu.setRowHeight(22);
        tblSuatChieu.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblSuatChieu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSuatChieu.setShowHorizontalLines(false);
        tblSuatChieu.setShowVerticalLines(false);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
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
        loadAllToTable();
        loadTenPhimCombobox();
    }//GEN-LAST:event_formWindowOpened

    private void tblSuatChieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuatChieuMouseClicked
        if (evt.getClickCount() >= 2) {
            String suatChieuId = tblSuatChieu.getValueAt(tblSuatChieu.getSelectedRow(), 1).toString();
            new DialogCapNhatSuatChieu(this, true, dcNgayHienThi.getDate(), (PhongChieu) cboPhongChieu.getSelectedItem(), suatChieuId).setVisible(true);
        }
    }//GEN-LAST:event_tblSuatChieuMouseClicked

    private void chkTenPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTenPhimActionPerformed
        cboPhim.setEnabled(chkTenPhim.isSelected());
        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_chkTenPhimActionPerformed

    private void dcNgayHienThiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcNgayHienThiPropertyChange
        loadAllToTable();
        loadTenPhimCombobox();
        cboPhim.setEditable(false);
        rdoDaChieu.setEnabled(false);
        rdoDangChieu.setEnabled(false);
        rdoSapChieu.setEnabled(false);
    }//GEN-LAST:event_dcNgayHienThiPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void chkTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTrangThaiActionPerformed
        if (chkTrangThai.isSelected()) {
            rdoDaChieu.setEnabled(true);
            rdoDangChieu.setEnabled(true);
            rdoSapChieu.setEnabled(true);
        } else {
            rdoDaChieu.setEnabled(false);
            rdoDangChieu.setEnabled(false);
            rdoSapChieu.setEnabled(false);
            rdoSapChieu.setSelected(true);
        }

        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_chkTrangThaiActionPerformed

    private void rdoSapChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSapChieuActionPerformed
        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_rdoSapChieuActionPerformed

    private void rdoDaChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaChieuActionPerformed
        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_rdoDaChieuActionPerformed

    private void rdoDangChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangChieuActionPerformed
        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_rdoDangChieuActionPerformed

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        if (cboPhim.getItemCount() > 0) {
            loadFilterDataToTable(searchAdvanced());
        }
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void cboPhongChieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongChieuItemStateChanged
        loadAllToTable();
        loadTenPhimCombobox();
        loadFilterDataToTable(searchAdvanced());
    }//GEN-LAST:event_cboPhongChieuItemStateChanged

    private void btnTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimelineActionPerformed
        new DialogCapNhatSuatChieu(this, true, dcNgayHienThi.getDate(), (PhongChieu) cboPhongChieu.getSelectedItem()).setVisible(true);
    }//GEN-LAST:event_btnTimelineActionPerformed

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
    private javax.swing.JCheckBox chkTenPhim;
    private javax.swing.JCheckBox chkTrangThai;
    private com.toedter.calendar.JDateChooser dcNgayHienThi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
