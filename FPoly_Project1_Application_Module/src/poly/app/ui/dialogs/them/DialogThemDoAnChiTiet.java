/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.them;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import poly.app.core.daoimpl.DoAnChiTietDaoImpl;
import poly.app.core.daoimpl.KichCoDoAnDaoImpl;
import poly.app.core.entities.DoAn;
import poly.app.core.entities.DoAnChiTiet;
import poly.app.core.entities.KichCoDoAn;
import poly.app.core.helper.DialogHelper;

/**
 *
 * @author vothanhtai
 */
public class DialogThemDoAnChiTiet extends javax.swing.JDialog {

    /**
     * Creates new form DialogThemDoAnChiTiet
     */
    DoAn doAn;

    public DialogThemDoAnChiTiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DialogThemDoAnChiTiet(java.awt.Frame parent, boolean modal, DoAn doan) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.doAn = doan;
        this.setModelToInput();
        this.loadDataToComboBox();
    }

    public void loadDataToComboBox() {
        DefaultComboBoxModel modelComboBox = (DefaultComboBoxModel) cboKichCo.getModel();
        modelComboBox.removeAllElements();
        KichCoDoAnDaoImpl kcda = new KichCoDoAnDaoImpl();
        List<KichCoDoAn> listKCDA = kcda.getAll();
        for (KichCoDoAn fill : listKCDA) {
            modelComboBox.addElement(fill);
        }
    }

    public void setModelToInput() {
        txtTen.setText(doAn.getTen());
        txtTen.setEditable(false);
    }

    public DoAnChiTiet getModelFromInput() {
        DoAnChiTiet model = new DoAnChiTiet();
        model.setKichCoDoAn((KichCoDoAn) cboKichCo.getSelectedItem());
        model.setDoAn(doAn);
        model.setDonGia(Integer.parseInt(ftfDonGia.getValue().toString()));

        model.setDangBan(doAn.isDangBan());

        return model;
    }

    public boolean insertDACT() {
        try {
            DoAnChiTietDaoImpl insertDACT = new DoAnChiTietDaoImpl();
            DoAnChiTiet model = getModelFromInput();
            insertDACT.insert(model);
            doAn.getDoAnChiTiets().add(model);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    private boolean validateInput() {
        if (ftfDonGia.getValue() == null) {
            DialogHelper.message(this, "Nhập giá của đồ ăn !", DialogHelper.ERROR_MESSAGE);
            return false;
        }

        DoAnChiTiet model = getModelFromInput();

        Map<String, DoAnChiTiet> existedDoAnChiTiet = new HashMap<>();
        for (DoAnChiTiet doAnChiTiet : doAn.getDoAnChiTiets()) {
            existedDoAnChiTiet.put(doAnChiTiet.getKichCoDoAn().getId(), doAnChiTiet);
        }

        if (existedDoAnChiTiet.containsKey(model.getKichCoDoAn().getId())) {
            DialogHelper.message(this, "Đã có " + model.getKichCoDoAn().getTen().toUpperCase() + " trong " + doAn.getTen().toUpperCase(), DialogHelper.ERROR_MESSAGE);
            return false;
        }

        if (model.getKichCoDoAn().getId().equals("S")) {
            if (existedDoAnChiTiet.containsKey("M") && model.getDonGia() >= existedDoAnChiTiet.get("M").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ nhỏ không được lớn hơn cỡ vừa ", DialogHelper.ERROR_MESSAGE);
                return false;
            }

            if (existedDoAnChiTiet.containsKey("L") && model.getDonGia() >= existedDoAnChiTiet.get("L").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ nhỏ không được lớn hơn cỡ lớn ", DialogHelper.ERROR_MESSAGE);
                return false;
            }
        }

        if (model.getKichCoDoAn().getId().equals("M")) {
            if (existedDoAnChiTiet.containsKey("S") && model.getDonGia() <= existedDoAnChiTiet.get("S").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ vừa không được nhỏ hơn cỡ nhỏ ", DialogHelper.ERROR_MESSAGE);
                return false;
            }

            if (existedDoAnChiTiet.containsKey("L") && model.getDonGia() >= existedDoAnChiTiet.get("L").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ vừa không được lớn hơn cỡ lớn ", DialogHelper.ERROR_MESSAGE);
                return false;
            }
        }
        if (model.getKichCoDoAn().getId().equals("L")) {
            if (existedDoAnChiTiet.containsKey("S") && model.getDonGia() <= existedDoAnChiTiet.get("S").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ lớn không được nhỏ hơn cỡ nhỏ ", DialogHelper.ERROR_MESSAGE);
                return false;
            }

            if (existedDoAnChiTiet.containsKey("M") && model.getDonGia() <= existedDoAnChiTiet.get("M").getDonGia()) {
                DialogHelper.message(this, "Giá cỡ lớn không được nhỏ hơn cỡ vừa ", DialogHelper.ERROR_MESSAGE);
                return false;
            }
        }

        if (doAn.isDangBan() == false && cboTrangThai.getSelectedIndex() == 0) {
            DialogHelper.message(this, "Đồ ăn này đã ngưng bán !", HEIGHT);
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        cboKichCo = new javax.swing.JComboBox<>();
        ftfDonGia = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel1.setText("Tên đồ ăn");

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Kích cỡ");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Giá bán");

        txtTen.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        cboKichCo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        ftfDonGia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        ftfDonGia.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        ftfDonGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ftfDonGiaKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái");

        cboTrangThai.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang được bán", "Đã ngưng bán" }));

        jPanel2.setOpaque(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/apple.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 83, 104));
        jLabel6.setText("Thêm kích cỡ đồ ăn");

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        jLabel7.setText("Vui lòng nhập đầy đủ thông tin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7))
        );

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnThem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnThem.setText("Lưu");
        btnThem.setPreferredSize(new java.awt.Dimension(75, 33));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel3.add(btnThem, gridBagConstraints);

        btnHuy.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.setPreferredSize(new java.awt.Dimension(75, 33));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel3.add(btnHuy, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKichCo, 0, 230, Short.MAX_VALUE)
                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(ftfDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(cboTrangThai, 0, 230, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (validateInput()) {
            if (this.insertDACT()) {
                DialogHelper.message(this, "Thêm dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);

                this.dispose();
            } else {
                DialogHelper.message(this, "Thêm dữ liệu thành công!", DialogHelper.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void ftfDonGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftfDonGiaKeyTyped
        if (String.valueOf(evt.getKeyChar()).matches("\\D")) {
            evt.consume();
        }
    }//GEN-LAST:event_ftfDonGiaKeyTyped

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
            java.util.logging.Logger.getLogger(DialogThemDoAnChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogThemDoAnChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogThemDoAnChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogThemDoAnChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogThemDoAnChiTiet dialog = new DialogThemDoAnChiTiet(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JFormattedTextField ftfDonGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
