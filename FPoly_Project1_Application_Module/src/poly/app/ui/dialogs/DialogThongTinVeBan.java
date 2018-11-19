/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.GiaVeDaoImpl;
import poly.app.core.daoimpl.VeBanDaoImpl;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.GiaVe;
import poly.app.core.entities.LoaiGhe;
import poly.app.core.entities.SuatChieu;
import poly.app.core.entities.VeBan;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class DialogThongTinVeBan extends javax.swing.JDialog {

    SuatChieu suatChieu;
    Map<String, GheNgoi> selectedGheNgoiMap = new HashMap<>();
    List<GiaVe> giaveList;

    /**
     * Creates new form DialogThongTinVeBan
     */
    public DialogThongTinVeBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public DialogThongTinVeBan(java.awt.Frame parent, boolean modal, SuatChieu suatChieu, Map<String, GheNgoi> selectedGheNgoiMap) {
        this(parent, modal);

        this.suatChieu = suatChieu;
        this.selectedGheNgoiMap = selectedGheNgoiMap;

        giaveList = new GiaVeDaoImpl().getAll();

        reRenderUI();
        setDataToInput();
        setDataToTable();
        
        tinhTongTien();
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblThongTin);
        tblRenderer.changeHeaderStyle();
        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColoumnWidthByPersent(1, 5);

        JComboBox comboBox = new JComboBox();
        for (Component component : comboBox.getComponents()) {
            if (component instanceof AbstractButton) {
                if (component.isVisible()) {
                    component.setVisible(false);
                }
            }
        }

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tblThongTin.getRowCount(); i++) {
                    GiaVe giaVe = (GiaVe) tblThongTin.getValueAt(i, 3);
                    LoaiGhe loaiGhe = (LoaiGhe) tblThongTin.getValueAt(i, 2);
                    tblThongTin.setValueAt(new DecimalFormat("#,###,###")
                            .format(loaiGhe.getPhuThu() + giaVe.getDonGia()), i, 4);
                }

                tinhTongTien();
            }
        });
        DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
        for (GiaVe giaVe : giaveList) {
            defaultComboBoxModel.addElement(giaVe);
        }

        tblThongTin.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer
                = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click để chọn đối tượng");
        tblThongTin.getColumnModel().getColumn(3).setCellRenderer(renderer);
    }

    private void setDataToInput() {
        txtMaNhanVien.setText(ShareHelper.USER.getId());
        txtNgayBanVe.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        txtTenPhim.setText(suatChieu.getPhim().getTen());
        txtGioChieu.setText(new SimpleDateFormat("HH:mm:ss").format(suatChieu.getGioBatDau()));
        txtMaSuatChieu.setText(suatChieu.getId());
        txtDinhDang.setText(suatChieu.getDinhDangPhim().getId());
        txtPhongChieu.setText("Phòng " + suatChieu.getPhongChieu().getId());
    }

    private void setDataToTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblThongTin.getModel();

        int i = 1;
        for (Map.Entry<String, GheNgoi> entry : selectedGheNgoiMap.entrySet()) {
            String viTriGhe = entry.getKey();
            GheNgoi gheNgoi = entry.getValue();
            
            defaultTableModel.addRow(new Object[]{
                i++,
                viTriGhe,
                gheNgoi.getLoaiGhe(),
                giaveList.get(0),
                new DecimalFormat("#,###,###").format(gheNgoi.getLoaiGhe().getPhuThu() + giaveList.get(0).getDonGia())
            });
        }
    }

    private void tinhTongTien() {
        int total = 0;

        for (int i = 0; i < tblThongTin.getRowCount(); i++) {
            total += Integer.parseInt(tblThongTin.getValueAt(i, 4).toString().replace(",", ""));
        }

        total += suatChieu.getDinhDangPhim().getPhuThu();
        
        txtTongTien.setText(new DecimalFormat("#,###,###").format(total));
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtNgayBanVe = new javax.swing.JTextField();
        txtTenPhim = new javax.swing.JTextField();
        txtGioChieu = new javax.swing.JTextField();
        txtMaSuatChieu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDinhDang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhongChieu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnInVe = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/invoice.png"))); // NOI18N
        jPanel2.add(jLabel2);

        jPanel3.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 83, 104));
        jLabel1.setText("Thông tin vé bán");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        jLabel3.setText("Vui lòng kiểm tra trước khi in");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Ngày bán vé");

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("Tên phim");

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("Giờ chiếu");

        txtMaNhanVien.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtMaNhanVien.setEnabled(false);

        txtNgayBanVe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNgayBanVe.setEnabled(false);

        txtTenPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTenPhim.setEnabled(false);

        txtGioChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtGioChieu.setEnabled(false);

        txtMaSuatChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtMaSuatChieu.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("Mã suất chiếu");

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setText("Định dạng");

        txtDinhDang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtDinhDang.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel10.setText("Phòng  chiếu");

        txtPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtPhongChieu.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTongTien.setEnabled(false);

        tblThongTin.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Vị trí ghế", "Loại ghế", "Đối tượng", "Giá vé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblThongTinKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongTin);

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("Thông tin ghế ngồi");

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnInVe.setText("In vé");
        btnInVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInVeActionPerformed(evt);
            }
        });
        jPanel4.add(btnInVe, new java.awt.GridBagConstraints());

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel4.add(btnHuy, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGioChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNgayBanVe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMaSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBanVe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInVeActionPerformed
        for (int i = 0; i < tblThongTin.getRowCount(); i++) {
            GheNgoi gheNgoi = selectedGheNgoiMap.get(tblThongTin.getValueAt(i, 1));
            GiaVe giaVe = (GiaVe) tblThongTin.getValueAt(i, 3);
            int tongTien = Integer.parseInt(txtTongTien.getText().replace(",", ""));
            
            VeBan veBan = new VeBan("", gheNgoi, giaVe, suatChieu, new Date(), tongTien);
            veBan.setNguoiDung(ShareHelper.USER);
            try {
                new VeBanDaoImpl().insert(veBan);
                DialogHelper.message(null, "Thêm vé bán thành công", DialogHelper.INFORMATION_MESSAGE);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.message(null, "Đã xảy ra lỗi trong quá trình thêm vé bán!", DialogHelper.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnInVeActionPerformed

    private void tblThongTinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblThongTinKeyPressed
        evt.consume();
    }//GEN-LAST:event_tblThongTinKeyPressed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

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
            java.util.logging.Logger.getLogger(DialogThongTinVeBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogThongTinVeBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogThongTinVeBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogThongTinVeBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogThongTinVeBan dialog = new DialogThongTinVeBan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInVe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtDinhDang;
    private javax.swing.JTextField txtGioChieu;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaSuatChieu;
    private javax.swing.JTextField txtNgayBanVe;
    private javax.swing.JTextField txtPhongChieu;
    private javax.swing.JTextField txtTenPhim;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
