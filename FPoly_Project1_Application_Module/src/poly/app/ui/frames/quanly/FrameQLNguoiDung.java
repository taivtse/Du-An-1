/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.quanly;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import poly.app.ui.utils.TableRendererUtil;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.NguoiDungDaoImpl;
import poly.app.core.entities.NguoiDung;
import poly.app.ui.dialogs.capnhat.DialogCapNhatNguoiDung;
import poly.app.ui.dialogs.them.DialogThemNguoiDung;

/**
 *
 * @author vothanhtai
 */
public class FrameQLNguoiDung extends javax.swing.JFrame {

    Map<String, NguoiDung> nguoiDungMap = new TreeMap<>();

    /**
     * Creates new form FrameQLNguoiDung
     */
    public FrameQLNguoiDung() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Quản lý người dùng");
        reRenderUI();
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblNguoiDung);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();

        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColoumnWidthByPersent(2, 20);
        tblRenderer.setColumnAlignment(1, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(6, TableRendererUtil.CELL_ALIGN_CENTER);
    }
    
    public JPanel getMainPanel(){
        formWindowOpened(null);
        return this.pnlMain;
    }

    private Map<String, NguoiDung> search() {

//        new map<> rong chua list moi;
// code bien max , min
        Map<String, NguoiDung> emptyMap = new TreeMap<String, NguoiDung>();
        Date min = dcTuNgay.getDate(), max = dcDenNgay.getDate();
//      ---------------------------------------------------------------
        min = min == null ? dcTuNgay.getMinSelectableDate() : min;
        max = max == null ? dcDenNgay.getMaxSelectableDate() : max;
//        ---------------------------------------------------------------
        LocalDate localDate = min.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        min = java.sql.Date.valueOf(localDate);
//        ---------------------------------------------------------------
        localDate = max.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        max = java.sql.Date.valueOf(localDate);
        for (Entry<String, NguoiDung> entry : nguoiDungMap.entrySet()) {
            if (entry.getValue().getHoTen().toLowerCase().contains(txtSearchTen.getText().toLowerCase())
                    && entry.getValue().getNgayVaoLam().compareTo(min) >= 0
                    && entry.getValue().getNgayVaoLam().compareTo(max) <= 0) {
                emptyMap.put(entry.getValue().getId(), entry.getValue());
            }

        }

        return emptyMap;
    }

    public void loadDataToTable(Map<String, NguoiDung> ndMap) {
        int i = 1;
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNguoiDung.getModel();
        defaultTableModel.setRowCount(0);
        for (Entry<String, NguoiDung> entry : ndMap.entrySet()) {

            NguoiDung nd = entry.getValue();
            defaultTableModel.addRow(
                    new Object[]{
                        i++,
                        nd.getId(),
                        nd.getHoTen(),
                        nd.getSoDienThoai(),
                        nd.getEmail(),
                        new SimpleDateFormat("dd-MM-yyyy").format(nd.getNgayVaoLam()),
                        nd.isGioiTinhNam() ? "Nam" : "Nữ",
                        nd.getVaiTro().getTen(),
                        nd.getDangLam() ? "Đang làm" : "Đã nghỉ"}
            );
        }
        this.tblNguoiDung.setModel(defaultTableModel);
    }

    public void loadAllDataToTable() {
        nguoiDungMap.clear();
        int i = 1;

        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNguoiDung.getModel();
        defaultTableModel.setRowCount(0);

        for (NguoiDung nd : new NguoiDungDaoImpl().getAll()) {
            defaultTableModel.addRow(
                    new Object[]{
                        i++,
                        nd.getId(),
                        nd.getHoTen(),
                        nd.getSoDienThoai(),
                        nd.getEmail(),
                        nd.getNgayVaoLam(),
                        nd.isGioiTinhNam() ? "Nam" : "Nữ",
                        nd.getVaiTro().toString(),
                        nd.getDangLam() ? "Đang làm" : "Đã nghỉ"}
            );

            nguoiDungMap.put(nd.getId(), nd);
        }
        this.tblNguoiDung.setModel(defaultTableModel);
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
        chkTheoTen = new javax.swing.JCheckBox();
        txtSearchTen = new javax.swing.JTextField();
        chkTheoNgayVaoLam = new javax.swing.JCheckBox();
        dcTuNgay = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dcDenNgay = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        btnCollapse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 83, 104));
        jLabel1.setText("Tra cứu người dùng");

        chkTheoTen.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        chkTheoTen.setSelected(true);
        chkTheoTen.setText("Theo tên");
        chkTheoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTheoTenActionPerformed(evt);
            }
        });

        txtSearchTen.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtSearchTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTenKeyReleased(evt);
            }
        });

        chkTheoNgayVaoLam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        chkTheoNgayVaoLam.setText("Theo ngày vào làm");
        chkTheoNgayVaoLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTheoNgayVaoLamActionPerformed(evt);
            }
        });

        dcTuNgay.setDateFormatString("dd-MM-yyyy");
        dcTuNgay.setEnabled(false);
        dcTuNgay.setFocusCycleRoot(true);
        dcTuNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcTuNgayPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Từ ngày");

        dcDenNgay.setDateFormatString("dd-MM-yyyy");
        dcDenNgay.setEnabled(false);
        dcDenNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcDenNgayPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Đến ngày");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jLabel1))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(chkTheoTen)
                                .addComponent(jLabel3)
                                .addComponent(chkTheoNgayVaoLam)
                                .addComponent(txtSearchTen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(chkTheoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchTen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(chkTheoNgayVaoLam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnThem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton1.setText("Xoá");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblNguoiDung.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã người dùng", "Họ tên", "Số điện thoại", "Email", "Ngày vào làm", "Giới tính", "Vai trò", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblNguoiDung.setRowHeight(20);
        tblNguoiDung.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblNguoiDung.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNguoiDung);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
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
        loadAllDataToTable();
    }//GEN-LAST:event_formWindowOpened

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        new DialogThemNguoiDung(this, true).setVisible(true);
//        loadAllDataToTable();
        loadDataToTable(search());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String id = tblNguoiDung.getValueAt(tblNguoiDung.getSelectedRow(), 1).toString();
        new DialogCapNhatNguoiDung(this, true, nguoiDungMap.get(id)).setVisible(true);
        loadAllDataToTable();
        loadDataToTable(search());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        if (evt.getClickCount() >= 2) {
            String id = tblNguoiDung.getValueAt(tblNguoiDung.getSelectedRow(), 1).toString();
            new DialogCapNhatNguoiDung(this, true, nguoiDungMap.get(id)).setVisible(true);
            loadDataToTable(search());
        }


    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void chkTheoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTheoTenActionPerformed
        if (chkTheoTen.isSelected()) {
            txtSearchTen.setEditable(true);
        } else {
            txtSearchTen.setEditable(false);
            txtSearchTen.setText("");
            loadDataToTable(search());
        }
        txtSearchTen.setEditable(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTheoTenActionPerformed

    private void txtSearchTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTenKeyReleased

        loadDataToTable(search());

// TODO add your handling code here:
    }//GEN-LAST:event_txtSearchTenKeyReleased

    private void chkTheoNgayVaoLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTheoNgayVaoLamActionPerformed
        if (chkTheoNgayVaoLam.isSelected()) {
            dcTuNgay.setEnabled(true);
            dcDenNgay.setEnabled(true);
        } else {
            dcTuNgay.setDate(null);
            dcDenNgay.setDate(null);
            dcTuNgay.setEnabled(false);
            dcDenNgay.setEnabled(false);
            loadDataToTable(search());
        }
// TODO add your handling code here:
    }//GEN-LAST:event_chkTheoNgayVaoLamActionPerformed

    private void dcTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcTuNgayPropertyChange
        if (chkTheoNgayVaoLam.isSelected()) {
            loadDataToTable(search());
            Date min = dcTuNgay.getDate();
            Date max = dcDenNgay.getDate();
            if (min != null && max != null) {
                if (max.compareTo(min) < 0) {
                    dcTuNgay.setDate(max);
                }
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_dcTuNgayPropertyChange

    private void dcDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcDenNgayPropertyChange
        if (chkTheoNgayVaoLam.isSelected()) {
            Date min = dcTuNgay.getDate();
            Date max = dcDenNgay.getDate();
            if (min != null && max != null) {
                if (min.compareTo(max) > 0) {
                    dcDenNgay.setDate(min);
                }
            }
            loadDataToTable(search());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_dcDenNgayPropertyChange

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
            java.util.logging.Logger.getLogger(FrameQLNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameQLNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameQLNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameQLNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameQLNguoiDung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox chkTheoNgayVaoLam;
    private javax.swing.JCheckBox chkTheoTen;
    private com.toedter.calendar.JDateChooser dcDenNgay;
    private com.toedter.calendar.JDateChooser dcTuNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtSearchTen;
    // End of variables declaration//GEN-END:variables
}
