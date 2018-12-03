/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.dangnhap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import poly.app.core.daoimpl.NguoiDungDaoImpl;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.core.utils.FileFactoryUtil;
import poly.app.ui.utils.ValidationUtil;

/**
 *
 * @author vothanhtai
 */
public class DatLaiMatKhau extends javax.swing.JDialog {
    /**
     * Creates new form DatLaiMatKhau
     */
    public DatLaiMatKhau(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnThucHien);
    }

    private boolean updateMatKhau(){
        ShareHelper.USER.setMatKhau(txtMatKhauMoi.getText());
        return new NguoiDungDaoImpl().update(ShareHelper.USER);
    }
    
    private boolean validateInput() {
        if (String.valueOf(txtMatKhauMoi.getPassword()).startsWith("$$")) {
            DialogHelper.message(this, "Mật khẩu mới không được bắt đầu bằng ký tự \"$$\"", DialogHelper.ERROR_MESSAGE);
            return false;
        }
        
        if (ValidationUtil.isEmpty(String.valueOf(txtMatKhauMoi.getPassword()))) {
            DialogHelper.message(this, "Mật khẩu mới không được để trống", DialogHelper.ERROR_MESSAGE);
            return false;
        }
        
        if (!ValidationUtil.isLenghtEnought(String.valueOf(txtMatKhauMoi.getPassword()), 3)) {
            DialogHelper.message(this, "Mật khẩu phải từ 3 ký tự trở lên", DialogHelper.ERROR_MESSAGE);
            return false;
        }


        if (ValidationUtil.isEmpty(String.valueOf(txtReMatKhauMoi.getPassword()))) {
            DialogHelper.message(this, "Xác nhận mật khẩu mới không được để trống", DialogHelper.ERROR_MESSAGE);
            return false;
        }
        
        if (!String.valueOf(txtMatKhauMoi.getPassword()).equals(String.valueOf(txtReMatKhauMoi.getPassword()))) {
            DialogHelper.message(this, "Mật khẩu mới không trùng khớp", DialogHelper.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void saveAccountToFile(){
        Map<String, String> account = new HashMap<String, String>();
        account.put("username", ShareHelper.USER.getId());
        account.put("password", String.valueOf(txtMatKhauMoi.getPassword()));
        
        FileFactoryUtil.writeObject(account, new File("accounnt.bin").getAbsolutePath());
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
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnThucHien = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtMatKhauMoi = new javax.swing.JPasswordField();
        txtReMatKhauMoi = new javax.swing.JPasswordField();
        chkLuuTaiKhoan = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(254, 203, 101));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/login-icon.png"))); // NOI18N
        jPanel2.add(jLabel5, new java.awt.GridBagConstraints());

        jPanel3.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 83, 104));
        jLabel2.setText("Đặt lại mật khẩu");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLabel3.setText("Mật khẩu mới");

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLabel4.setText("Nhập lại mật khẩu mới");

        btnThucHien.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnThucHien.setText("Thực hiện");
        btnThucHien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThucHienActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnCancel.setText("Thoát");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtMatKhauMoi.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N

        txtReMatKhauMoi.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N

        chkLuuTaiKhoan.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        chkLuuTaiKhoan.setText("Lưu tài khoản?");
        chkLuuTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLuuTaiKhoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(chkLuuTaiKhoan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReMatKhauMoi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(txtReMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(chkLuuTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucHienActionPerformed
        if (validateInput()) {
            boolean isUpdated = updateMatKhau();
            if (isUpdated) {
                if (chkLuuTaiKhoan.isSelected()) {
                    saveAccountToFile();
                }
                DialogHelper.message(this, "Cập nhật mật khẩu thành công!\nSử dụng mật khẩu mới cho lần đăng nhập sau", DialogHelper.INFORMATION_MESSAGE);
            }else{
                DialogHelper.message(this, "Cập nhật mật khẩu thất bại!\nVui lòng thử lại", DialogHelper.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnThucHienActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void chkLuuTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLuuTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLuuTaiKhoanActionPerformed

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
            java.util.logging.Logger.getLogger(DatLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatLaiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatLaiMatKhau dialog = new DatLaiMatKhau(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnThucHien;
    private javax.swing.JCheckBox chkLuuTaiKhoan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtMatKhauMoi;
    private javax.swing.JPasswordField txtReMatKhauMoi;
    // End of variables declaration//GEN-END:variables
}
