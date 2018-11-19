/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import poly.app.core.daoimpl.GheNgoiDaoImpl;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;
import poly.app.core.helper.DialogHelper;
import poly.app.ui.utils.ColorUtil;

/**
 *
 * @author vothanhtai
 */
public class DialogChonGheNgoi extends javax.swing.JDialog {

    SuatChieu suatChieu;
    Map<String, GheNgoi> gheNgoiMap = new HashMap<>();
    Map<String, GheNgoi> selectedGheNgoiMap = new HashMap<>();

    /**
     * Creates new form DialogChonGheNgoi
     */
    public DialogChonGheNgoi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DialogChonGheNgoi(java.awt.Frame parent, boolean modal, SuatChieu suatChieu) {
        this(parent, modal);

        this.suatChieu = suatChieu;
        loadGheNgoiToMap();
        renderSeats();
    }

    private void renderSeats() {
        int gheNgoiButtonWidth = 45;
        int gheNgoiButtonHeight = 35;

        PhongChieu phongChieu = suatChieu.getPhongChieu();
        pnGheNgoi.setLayout(new GridLayout(phongChieu.getSoLuongDay(), phongChieu.getSoLuongCot(), 4, 4));

        List<GheNgoi> gheNgoiDaDatList = new GheNgoiDaoImpl().getGheNgoiDaDatBySuatChieu(suatChieu);

        for (int row = 0; row < phongChieu.getSoLuongDay(); row++) {
            for (int col = 1; col <= phongChieu.getSoLuongCot(); col++) {
//                create a seat shape
                JLabel gheNgoiButton = new JLabel(((char) (65 + row)) + "" + col);
                gheNgoiButton.setMinimumSize(new Dimension(gheNgoiButtonWidth, gheNgoiButtonHeight));
                gheNgoiButton.setPreferredSize(new Dimension(gheNgoiButtonWidth, gheNgoiButtonHeight));
                gheNgoiButton.setMaximumSize(new Dimension(gheNgoiButtonWidth, gheNgoiButtonHeight));
                gheNgoiButton.setSize(new Dimension(gheNgoiButtonWidth, gheNgoiButtonHeight));

                gheNgoiButton.setOpaque(true);
                gheNgoiButton.setHorizontalAlignment(JLabel.CENTER);
                gheNgoiButton.setFont(new Font("Open Sans", Font.PLAIN, 12));

                gheNgoiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//                set background and foreground color for the seat
                if (gheNgoiMap.get(((char) (65 + row)) + "" + col).getLoaiGhe().getId().equals("GT")) {
                    gheNgoiButton.setBackground(ColorUtil.NORMAL_SEAT_COLOR);
                    gheNgoiButton.setForeground(ColorUtil.NORMAL_SEAT_FOREGROUND_COLOR);
                } else {
                    gheNgoiButton.setBackground(ColorUtil.VIP_SEAT_COLOR);
                    gheNgoiButton.setForeground(ColorUtil.VIP_SEAT_FOREGROUND_COLOR);
                }

                boolean isReserved = false;
                for (GheNgoi gheNgoi : gheNgoiDaDatList) {
                    if ((gheNgoi.getViTriDay() + gheNgoi.getViTriCot()).equals(gheNgoiButton.getText())) {
                        isReserved = true;
                        break;
                    }
                }

                if (isReserved) {
                    gheNgoiButton.setBackground(ColorUtil.RESERVED_SEAT_COLOR);
                    gheNgoiButton.setForeground(ColorUtil.RESERVED_SEAT_FOREGROUND_COLOR);
                    
                    gheNgoiButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } else {
//                add click event for selectable seats
                    gheNgoiButton.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (gheNgoiButton.getBackground().equals(ColorUtil.SEAT_COLOR_SELECTED)) {
//                        Revert from selected seat to unselected
                                if (selectedGheNgoiMap.get(gheNgoiButton.getText()).getLoaiGhe().getId().equals("GT")) {
                                    gheNgoiButton.setBackground(ColorUtil.NORMAL_SEAT_COLOR);
                                    gheNgoiButton.setForeground(ColorUtil.NORMAL_SEAT_FOREGROUND_COLOR);
                                } else {
                                    gheNgoiButton.setBackground(ColorUtil.VIP_SEAT_COLOR);
                                    gheNgoiButton.setForeground(ColorUtil.VIP_SEAT_FOREGROUND_COLOR);
                                }
                                selectedGheNgoiMap.remove(gheNgoiButton.getText());
                            } else {
//                        Selected the seat
                                if (selectedGheNgoiMap.size() >= 5) {
                                    DialogHelper.message(null, "Số ghế ngồi trong 1 lần chọn không vượt quá 5 ghế", DialogHelper.ERROR_MESSAGE);
                                } else {
                                    gheNgoiButton.setBackground(ColorUtil.SEAT_COLOR_SELECTED);
                                    gheNgoiButton.setForeground(ColorUtil.SEAT_FOREGROUND_COLOR_SELECTED);
                                    selectedGheNgoiMap.put(gheNgoiButton.getText(), gheNgoiMap.get(gheNgoiButton.getText()));
                                }
                            }
                        }
                    });
                }

                pnGheNgoi.add(gheNgoiButton);
            }
        }

        pack();
        setLocationRelativeTo(null);
    }

    private void loadGheNgoiToMap() {
        for (GheNgoi gheNgoi : new GheNgoiDaoImpl().getGheNgoiByPhongChieu(suatChieu.getPhongChieu())) {
            gheNgoiMap.put(gheNgoi.getViTriDay() + gheNgoi.getViTriCot(), gheNgoi);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnGheNgoi = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnBanVe = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(221, 221, 221));
        jLabel1.setOpaque(true);

        jLabel2.setText("Ghế thường");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Ghế đặc biệt");

        jLabel4.setBackground(new java.awt.Color(49, 152, 252));
        jLabel4.setOpaque(true);

        jLabel5.setText("Ghế đã có người");

        jLabel6.setBackground(new java.awt.Color(165, 43, 36));
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("MÀN HÌNH");
        jLabel7.setOpaque(true);

        pnGheNgoi.setOpaque(false);

        javax.swing.GroupLayout pnGheNgoiLayout = new javax.swing.GroupLayout(pnGheNgoi);
        pnGheNgoi.setLayout(pnGheNgoiLayout);
        pnGheNgoiLayout.setHorizontalGroup(
            pnGheNgoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnGheNgoiLayout.setVerticalGroup(
            pnGheNgoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jLabel8.setText("Ghế đang chọn");

        jLabel9.setBackground(new java.awt.Color(244, 170, 36));
        jLabel9.setOpaque(true);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnBanVe.setText("Bán vé");
        btnBanVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanVeActionPerformed(evt);
            }
        });
        jPanel3.add(btnBanVe, new java.awt.GridBagConstraints());

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel3.add(btnHuy, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnGheNgoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 266, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnGheNgoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

    private void btnBanVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanVeActionPerformed
        if (selectedGheNgoiMap.size() > 0) {
            new DialogThongTinVeBan(null, true, suatChieu, selectedGheNgoiMap).setVisible(true);
        } else {
            DialogHelper.message(this, "Vui lòng chọn ghế!", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBanVeActionPerformed

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
            java.util.logging.Logger.getLogger(DialogChonGheNgoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogChonGheNgoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogChonGheNgoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogChonGheNgoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogChonGheNgoi dialog = new DialogChonGheNgoi(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBanVe;
    private javax.swing.JButton btnHuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnGheNgoi;
    // End of variables declaration//GEN-END:variables
}
