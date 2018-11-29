/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.custom;

import java.awt.Color;
import java.text.SimpleDateFormat;
import poly.app.core.entities.SuatChieu;

/**
 *
 * @author vothanhtai
 */
public class PanelSuatChieuItem extends javax.swing.JPanel {
    
    private int index;
    private SuatChieu suatChieu;

    /**
     * Creates new form PanelSuatChieuItem
     */
    public PanelSuatChieuItem() {
        initComponents();
    }
    
    public PanelSuatChieuItem(SuatChieu suatChieu) {
        this();        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        lblGioBatDau.setText(sdf.format(suatChieu.getGioBatDau()));
        lblGioKetThuc.setText(sdf.format(suatChieu.getGioKetThuc()));
        lblTenPhim.setText("<html>" + suatChieu.getPhim().getTen() + "</html>");
    }

    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu suatChieu) {
        this.suatChieu = suatChieu;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setItemSelected(){
        this.setBackground(Color.decode("#8BD628"));
    }
    
    public void setItemUnSelected(){
        this.setBackground(Color.decode("#FFF22F"));
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

        lblGioBatDau = new javax.swing.JLabel();
        lblGioKetThuc = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTenPhim = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 242, 47));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblGioBatDau.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        lblGioBatDau.setForeground(new java.awt.Color(237, 38, 37));
        lblGioBatDau.setText("00:00:00");
        lblGioBatDau.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));

        lblGioKetThuc.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        lblGioKetThuc.setForeground(new java.awt.Color(237, 38, 37));
        lblGioKetThuc.setText("00:00:00");
        lblGioKetThuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        lblTenPhim.setBackground(new java.awt.Color(255, 255, 255));
        lblTenPhim.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        lblTenPhim.setForeground(new java.awt.Color(52, 83, 104));
        lblTenPhim.setText("Film name");
        jPanel1.add(lblTenPhim);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("|");
        jLabel2.setMaximumSize(new java.awt.Dimension(1, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(1, 16));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(1, 5));
        jLabel2.setSize(new java.awt.Dimension(1, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jLabel2, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGioKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGioBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblGioBatDau)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lblGioKetThuc))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblGioBatDau;
    private javax.swing.JLabel lblGioKetThuc;
    private javax.swing.JLabel lblTenPhim;
    // End of variables declaration//GEN-END:variables

}