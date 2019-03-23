/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.banhang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.DoAnChiTietDaoImpl;
import poly.app.core.daoimpl.HoaDonChiTietDaoImpl;
import poly.app.core.daoimpl.HoaDonDaoImpl;
import poly.app.core.daoimpl.LoaiDoAnDaoImpl;
import poly.app.core.entities.DoAnChiTiet;
import poly.app.core.entities.HoaDon;
import poly.app.core.entities.HoaDonChiTiet;
import poly.app.core.entities.LoaiDoAn;
import poly.app.core.entities.NguoiDung;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.ui.custom.ClosableTabbedPane;
import poly.app.ui.report.HoaDonReportParameter;
import poly.app.ui.utils.ReportPrinterUtil;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameBanDoAn extends javax.swing.JFrame implements ClosableTabbedPane.ClosableTabbedPaneMethod {

    /**
     * Creates new form FrameQLNhanVien
     */
    List<DoAnChiTiet> listDoAnChiTiet = new ArrayList<>();
    Map<String, HoaDonChiTiet> mapOrder = new TreeMap<String, HoaDonChiTiet>();
    Map<String, DoAnChiTiet> mapDoAn = new TreeMap<String, DoAnChiTiet>();
    ButtonGroup btngr = new ButtonGroup();
    DecimalFormat decimalFormat = new DecimalFormat("##,###,###");

    public FrameBanDoAn() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Bán đồ ăn");
        reRenderUI();
        this.loadDataToComboBox();
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblDoAn);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
        tblRenderer.setColoumnWidthByPersent(0, 10);
        tblRenderer.setColumnAlignment(0, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(3, TableRendererUtil.CELL_ALIGN_RIGHT);

        tblRenderer = new TableRendererUtil(tblDaChon);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColumnAlignment(1, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(4, TableRendererUtil.CELL_ALIGN_RIGHT);
        tblRenderer.setColumnAlignment(5, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(6, TableRendererUtil.CELL_ALIGN_RIGHT);

        this.loadDataToComboBox();
    }

    public JPanel getMainPanel() {
        synchronizedData();
        return this.pnlMain;
    }

    public void synchronizedData() {
        this.loadDataToTableDoAn();
        cboLoaiDoAnItemStateChanged(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        btnChon = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoAn = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboLoaiDoAn = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnBan = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDaChon = new javax.swing.JTable();
        btnCollapse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 83, 104));
        jLabel1.setText("Bán đồ ăn");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Số lượng");

        spnSoLuong.setValue(1);

        btnChon.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnChon.setText("Chọn");
        btnChon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnChon)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblDoAn.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tblDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã đồ ăn", "Tên đồ ăn", "Kích cỡ", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        tblDoAn.setRowHeight(20);
        tblDoAn.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblDoAn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDoAnMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoAnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDoAn);

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Loại đồ ăn");

        cboLoaiDoAn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboLoaiDoAn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiDoAnItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addContainerGap(371, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboLoaiDoAn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(0, 319, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(cboLoaiDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnBan.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnBan.setText("Bán và in");
        btnBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(211, 47, 47));
        lblTongTien.setText("0 VND");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBan)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblDaChon.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblDaChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã đồ ăn", "Tên đồ ăn", "Kích cỡ", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDaChon.setRowHeight(22);
        tblDaChon.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblDaChon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDaChon.setShowHorizontalLines(false);
        tblDaChon.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblDaChon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        btnCollapse.setBackground(new java.awt.Color(52, 83, 104));
        btnCollapse.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCollapse.setForeground(new java.awt.Color(255, 255, 255));
        btnCollapse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCollapse.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCollapse.setOpaque(true);

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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.loadDataToComboBox();
    }//GEN-LAST:event_formWindowOpened

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        if (tblDaChon.getRowCount() > 0) {
            this.Ban();
            DialogHelper.message(this, "In hoá đơn thành công!", DialogHelper.INFORMATION_MESSAGE);
        } else {
            DialogHelper.message(this, "Chưa chọn đồ ăn!", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        int soluong = Integer.parseInt(spnSoLuong.getValue().toString());
        int index = tblDoAn.getSelectedRow();
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        if (tblDoAn.getSelectedRow() >= 0) {
            String key = (String) tblDoAn.getValueAt(index, 0) + tblDoAn.getValueAt(index, 3).toString().replace(",", "");
            DoAnChiTiet dact = mapDoAn.get(key);

            if (soluong <= 0) {
                DialogHelper.message(this, "Số lượng ít nhất là 1", DialogHelper.ERROR_MESSAGE);
            }
            if (mapOrder.containsKey(key)) {
                int slCu = mapOrder.get(key).getSoLuong();
                hdct.setSoLuong(slCu);
            }
            hdct.setDoAnChiTiet(dact);
            hdct.setHoaDon(null);
            this.orderBySoLuong(key, hdct);
        } else {
            DialogHelper.message(this, "Chưa chọn món ", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void tblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseClicked
        int index = tblDoAn.getSelectedRow();
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        if (evt.getClickCount() >= 2) {

            if (tblDoAn.getSelectedRow() >= 0) {
                String key = (String) tblDoAn.getValueAt(index, 0) + tblDoAn.getValueAt(index, 3).toString().replace(",", "");// key do an
                DoAnChiTiet dact = mapDoAn.get(key);
                if (mapOrder.containsKey(key)) {
                    int slCu = mapOrder.get(key).getSoLuong();
                    hdct.setSoLuong(slCu);
                }
                hdct.setDoAnChiTiet(dact);
                hdct.setHoaDon(null);
                this.orderByClick(key, hdct);
            } else {
                DialogHelper.message(this, "Chưa chọn đồ ăn", DialogHelper.ERROR_MESSAGE);
            }
            tblDoAn.setSelectionMode(0);
        }
    }//GEN-LAST:event_tblDoAnMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (tblDaChon.getSelectedRow() >= 0) {
            String key = (String) tblDaChon.getValueAt(tblDaChon.getSelectedRow(), 1) + tblDaChon.getValueAt(tblDaChon.getSelectedRow(), 4).toString().replace(",", "");
            mapOrder.remove(key);
            this.loadDataToTableHoaDon();
        } else {
            DialogHelper.message(this, "Chưa chọn đồ ăn để xóa!", DialogHelper.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblDoAnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMousePressed

    }//GEN-LAST:event_tblDoAnMousePressed

    private void cboLoaiDoAnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiDoAnItemStateChanged
        this.filterDoAnTheoLoaiDoAn();
    }//GEN-LAST:event_cboLoaiDoAnItemStateChanged

    public void orderBySoLuong(String key, HoaDonChiTiet hdct) {
        hdct.setSoLuong(hdct.getSoLuong() + Integer.parseInt(spnSoLuong.getValue().toString()));
        hdct.setTongTien(hdct.getSoLuong() * hdct.getDoAnChiTiet().getDonGia());
        mapOrder.put(key, hdct);
        this.loadDataToTableHoaDon();
    }

    public void orderByClick(String key, HoaDonChiTiet hdct) {
        hdct.setSoLuong(hdct.getSoLuong() + 1);
        hdct.setTongTien(hdct.getSoLuong() * hdct.getDoAnChiTiet().getDonGia());
        mapOrder.put(key, hdct);
        this.loadDataToTableHoaDon();
    }

    public void loadDataToTableDoAn() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDoAn.getModel();
        modelTable.setRowCount(0);
        List<DoAnChiTiet> listDoAn = new DoAnChiTietDaoImpl().getAllAndOrderBySize();
        for (DoAnChiTiet fill : listDoAn) {
            Object[] record = new Object[]{
                fill.getDoAn().getId(),
                fill.getDoAn().getTen(),
                fill.getKichCoDoAn().getTen(),
                decimalFormat.format(fill.getDonGia())
            };
            modelTable.addRow(record);
            mapDoAn.put(fill.getDoAn().getId() + fill.getDonGia(), fill);
        }
    }

    public void loadDataToTableHoaDon() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDaChon.getModel();
        modelTable.setRowCount(0);
        int i = 1;
        int tongtien = 0;
        for (Map.Entry<String, HoaDonChiTiet> fill : mapOrder.entrySet()) {
            Object[] record = new Object[]{
                i++,
                fill.getValue().getDoAnChiTiet().getDoAn().getId(),
                fill.getValue().getDoAnChiTiet().getDoAn().getTen(),
                fill.getValue().getDoAnChiTiet().getKichCoDoAn().getTen(),
                decimalFormat.format(fill.getValue().getDoAnChiTiet().getDonGia()),
                fill.getValue().getSoLuong(),
                decimalFormat.format(fill.getValue().getTongTien())
            };
            modelTable.addRow(record);
            tongtien += fill.getValue().getTongTien();
        }
        lblTongTien.setText(decimalFormat.format(tongtien) + " VND");
    }

    public void loadDataToComboBox() {
        DefaultComboBoxModel modelCBB = (DefaultComboBoxModel) cboLoaiDoAn.getModel();
        LoaiDoAnDaoImpl ldaDAO = new LoaiDoAnDaoImpl();
        List<LoaiDoAn> listLDA = ldaDAO.getAll();
        modelCBB.removeAllElements();
        modelCBB.addElement(new LoaiDoAn("", "Tất cả"));
        for (LoaiDoAn fill : listLDA) {
            modelCBB.addElement(fill);
        }
    }

    public void filterDoAnTheoLoaiDoAn() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDoAn.getModel();
        modelTable.setRowCount(0);
        LoaiDoAn lda = (LoaiDoAn) cboLoaiDoAn.getModel().getSelectedItem();
        for (Map.Entry<String, DoAnChiTiet> entry : mapDoAn.entrySet()) {
            if (lda != null && lda.getId().equals("")) {
                Object[] record = new Object[]{
                    entry.getValue().getDoAn().getId(),
                    entry.getValue().getDoAn().getTen(),
                    entry.getValue().getKichCoDoAn().getTen(),
                    decimalFormat.format(entry.getValue().getDonGia())
                };
                modelTable.addRow(record);
                continue;
            }
            if (entry.getValue().getDoAn().getLoaiDoAn().getId().equals(lda.getId())) {
                Object[] record = new Object[]{
                    entry.getValue().getDoAn().getId(),
                    entry.getValue().getDoAn().getTen(),
                    entry.getValue().getKichCoDoAn().getTen(),
                    decimalFormat.format(entry.getValue().getDonGia())
                };
                modelTable.addRow(record);
            }
        }
    }

    public void Ban() {
        //get nguoi dung de them vao hoa don
        NguoiDung nd = ShareHelper.USER;

        //tao hoa don moi
        HoaDon newHD = new HoaDon();
        newHD.setId("");
        newHD.setNgayBan(new Date());
        newHD.setNguoiDung(nd);
        HoaDonDaoImpl hdDAO = new HoaDonDaoImpl();
        hdDAO.insert(newHD);
        newHD = hdDAO.getNewestHoaDon();
        //them hoadonchitiet vao hoa don
        HoaDonChiTietDaoImpl hdctDAO = new HoaDonChiTietDaoImpl();
        int tongCong = 0;
        HoaDonReportParameter parameter = new HoaDonReportParameter(newHD.getId(), ShareHelper.USER.getHoTen(), new Date(), tongCong);
        for (Map.Entry<String, HoaDonChiTiet> up : mapOrder.entrySet()) {
            HoaDonChiTiet hoaDonChiTiet = up.getValue();
            hoaDonChiTiet.setHoaDon(newHD);
            hdctDAO.insert(hoaDonChiTiet);

            tongCong += up.getValue().getTongTien();
            parameter.addHoaDonReportBean(hoaDonChiTiet.getDoAnChiTiet().getDoAn().getTen() + " (" + hoaDonChiTiet.getDoAnChiTiet().getKichCoDoAn().getId() + ")",
                    hoaDonChiTiet.getSoLuong(),
                    hoaDonChiTiet.getDoAnChiTiet().getDonGia(),
                    hoaDonChiTiet.getTongTien());
        }
        
        parameter.setTotalPrice(tongCong);
        ReportPrinterUtil.printReport(parameter, ReportPrinterUtil.HOADON_REPORT_URL, false);

        DefaultTableModel modelTable = (DefaultTableModel) tblDaChon.getModel();
        mapOrder.clear();
        modelTable.setRowCount(0);
        lblTongTien.setText("0 VND");
    }

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
            java.util.logging.Logger.getLogger(FrameBanDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBanDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBanDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBanDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameBanDoAn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnChon;
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiDoAn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblDaChon;
    private javax.swing.JTable tblDoAn;
    // End of variables declaration//GEN-END:variables
}
