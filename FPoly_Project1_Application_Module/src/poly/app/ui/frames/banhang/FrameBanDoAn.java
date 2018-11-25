/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.banhang;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.common.CoreConstant;
import poly.app.core.daoimpl.DoAnChiTietDaoImpl;
import poly.app.core.daoimpl.HoaDonChiTietDaoImpl;
import poly.app.core.daoimpl.HoaDonDaoImpl;
import poly.app.core.daoimpl.NguoiDungDaoImpl;
import poly.app.core.entities.DoAnChiTiet;
import poly.app.core.entities.HoaDon;
import poly.app.core.entities.HoaDonChiTiet;
import poly.app.core.entities.NguoiDung;
import poly.app.core.helper.DateHelper;
import poly.app.core.helper.DialogHelper;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameBanDoAn extends javax.swing.JFrame {

    /**
     * Creates new form FrameQLNhanVien
     */
    List<DoAnChiTiet> listDoAnChiTiet = new ArrayList<>();
    Map<String, HoaDonChiTiet> mapOrder = new HashMap<String, HoaDonChiTiet>();
    Map<String, DoAnChiTiet> mapDoAn = new HashMap<String, DoAnChiTiet>();
    ButtonGroup btngr = new ButtonGroup();

    public FrameBanDoAn() {
        initComponents();
        setLocationRelativeTo(null);
        reRenderUI();
        this.setRadio();
        this.loadDataToTableDoAn();

    }

    private void setRadio() {
        btngr.add(btnTatCa);
        btngr.add(btnDoAnNhanh);
        btngr.add(btnNuocUong);
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblDoAn);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();

        tblRenderer = new TableRendererUtil(tblDaChon);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
    }

    public JPanel getMainPanel() {
        return pnMain;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        btnChon = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoAn = new javax.swing.JTable();
        btnTatCa = new javax.swing.JRadioButton();
        btnDoAnNhanh = new javax.swing.JRadioButton();
        btnNuocUong = new javax.swing.JRadioButton();
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
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnMain.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setText("Loại đồ ăn");

        jLabel4.setText("Số lượng");

        spnSoLuong.setValue(1);

        btnChon.setText("Chọn");
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
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChon)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon))
                .addContainerGap(47, Short.MAX_VALUE))
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
        tblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoAnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblDoAnMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblDoAn);

        btnTatCa.setSelected(true);
        btnTatCa.setText("Tất cả");
        btnTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaActionPerformed(evt);
            }
        });

        btnDoAnNhanh.setText("Đồ ăn nhanh");
        btnDoAnNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoAnNhanhActionPerformed(evt);
            }
        });

        btnNuocUong.setText("Nước uống");
        btnNuocUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuocUongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTatCa)
                                .addGap(38, 38, 38)
                                .addComponent(btnDoAnNhanh)
                                .addGap(38, 38, 38)
                                .addComponent(btnNuocUong)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTatCa)
                    .addComponent(btnDoAnNhanh)
                    .addComponent(btnNuocUong))
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnBan.setText("Bán");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tổng tiền:");

        lblTongTien.setForeground(new java.awt.Color(51, 102, 255));
        lblTongTien.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnBan)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan)
                    .addComponent(btnXoa)
                    .addComponent(jLabel2)
                    .addComponent(lblTongTien))
                .addContainerGap())
        );

        jPanel5.setOpaque(false);

        tblDaChon.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
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
        tblDaChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaChonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDaChon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        javax.swing.GroupLayout pnMainLayout = new javax.swing.GroupLayout(pnMain);
        pnMain.setLayout(pnMainLayout);
        pnMainLayout.setHorizontalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCollapse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMainLayout.setVerticalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCollapse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    }//GEN-LAST:event_formWindowOpened

    private void tblDaChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaChonMouseClicked

    }//GEN-LAST:event_tblDaChonMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void btnDoAnNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoAnNhanhActionPerformed
        this.filterDoAnNhanh();
    }//GEN-LAST:event_btnDoAnNhanhActionPerformed

    private void btnNuocUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuocUongActionPerformed
        this.filterNuocUong();
    }//GEN-LAST:event_btnNuocUongActionPerformed

    private void btnTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaActionPerformed
        this.loadDataToTableDoAn();
    }//GEN-LAST:event_btnTatCaActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        if (tblDaChon.getRowCount() > 0) {
            this.Ban();
            this.inThongKe();
        } else {
            DialogHelper.message(this, "Chưa chọn món !", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        if (tblDoAn.getSelectedRow() > 0) {
            this.order();
            if (DialogHelper.confirm(this, "In hóa đơn")) {
                this.inThongKe();
            }
        } else {
            DialogHelper.message(this, "Chưa chọn món ", DialogHelper.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnChonActionPerformed

    private void tblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseClicked
        if (evt.getClickCount() >= 2) {
            if (tblDoAn.getSelectedRow() >= 0) {
                this.order();
            } else {
                DialogHelper.message(this, "Chưa chọn món ", DialogHelper.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tblDoAnMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String key = (String) tblDaChon.getValueAt(tblDaChon.getSelectedRow(), 1) + tblDaChon.getValueAt(tblDaChon.getSelectedRow(), 4);
        mapOrder.remove(key);
        this.loadDataToTableHoaDon();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblDoAnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoAnMouseEntered

    public void order() {
        int index = tblDoAn.getSelectedRow();
        String key = (String) tblDoAn.getValueAt(index, 0) + tblDoAn.getValueAt(index, 3);
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        DoAnChiTiet dact = mapDoAn.get(key);

        hdct.setSoLuong(Integer.parseInt(spnSoLuong.getValue().toString()));
        hdct.setDoAnChiTiet(dact);
        hdct.setTongTien(hdct.getSoLuong() * hdct.getDoAnChiTiet().getDonGia());
        hdct.setHoaDon(null);
        if (mapOrder.containsKey(key)) {
            int soLuongCu = mapOrder.get(key).getSoLuong();
            int soLuongThem = Integer.parseInt(spnSoLuong.getValue().toString());
            hdct.setSoLuong(soLuongCu + soLuongThem);
            hdct.setTongTien(hdct.getSoLuong() * hdct.getDoAnChiTiet().getDonGia());
            mapOrder.put(key, hdct);
        }
        mapOrder.put(key, hdct);
        this.loadDataToTableHoaDon();
    }

    public void inThongKe() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.24, 0.24);

//                tblThongKe.paint(g2);
//          

                return Printable.PAGE_EXISTS;

            }
        });

        boolean ok = job.printDialog();
        if (ok) {
            try {

                job.print();
            } catch (PrinterException ex) {
            }
        }
    }

    public void loadDataToTableDoAn() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDoAn.getModel();
        modelTable.setRowCount(0);
        List<String> sortExpression = new ArrayList<String>();
        sortExpression.add("doAn");
        sortExpression.add("kichCoDoAn");
        List<DoAnChiTiet> listDoAn = new DoAnChiTietDaoImpl().getByProperties(null, sortExpression, CoreConstant.SORT_ASC, null, null);
        for (DoAnChiTiet fill : listDoAn) {
            Object[] record = new Object[]{
                fill.getDoAn().getId(),
                fill.getDoAn().getTen(),
                fill.getKichCoDoAn().getTen(),
                fill.getDonGia()
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
                fill.getValue().getDoAnChiTiet().getDonGia(),
                fill.getValue().getSoLuong(),
                fill.getValue().getTongTien()
            };
            modelTable.addRow(record);
            tongtien += fill.getValue().getTongTien();
        }
        lblTongTien.setText(String.valueOf(tongtien));
    }

    public void filterDoAnNhanh() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDoAn.getModel();
        modelTable.setRowCount(0);
        for (Map.Entry<String, DoAnChiTiet> entry : mapDoAn.entrySet()) {
            if (entry.getValue().getDoAn().getLoaiDoAn().getId().compareTo("DA") == 0) {
                Object[] record = new Object[]{
                    entry.getValue().getDoAn().getId(),
                    entry.getValue().getDoAn().getTen(),
                    entry.getValue().getKichCoDoAn().getTen(),
                    entry.getValue().getDonGia()
                };
                modelTable.addRow(record);
            }
        }
    }

    public void filterNuocUong() {
        DefaultTableModel modelTable = (DefaultTableModel) tblDoAn.getModel();
        modelTable.setRowCount(0);
        for (Map.Entry<String, DoAnChiTiet> entry : mapDoAn.entrySet()) {
            if (entry.getValue().getDoAn().getLoaiDoAn().getId().compareTo("NU") == 0) {
                Object[] record = new Object[]{
                    entry.getValue().getDoAn().getId(),
                    entry.getValue().getDoAn().getTen(),
                    entry.getValue().getKichCoDoAn().getTen(),
                    entry.getValue().getDonGia()
                };
                modelTable.addRow(record);
            }
        }
    }

    public void Ban() {
        //get nguoi dung de them vao hoa don
        NguoiDung nd = new NguoiDungDaoImpl().getById("NV00001");

        //tao hoa don moi
        HoaDon newHD = new HoaDon();
        newHD.setId("");
        newHD.setNgayBan(DateHelper.now());
        newHD.setNguoiDung(nd);
        HoaDonDaoImpl hdDAO = new HoaDonDaoImpl();
        hdDAO.insert(newHD);
        newHD = hdDAO.getNewestHoaDon();
        //them hoadonchitiet vao hoa don
        HoaDonChiTietDaoImpl hdctDAO = new HoaDonChiTietDaoImpl();
        for (Map.Entry<String, HoaDonChiTiet> up : mapOrder.entrySet()) {
            up.getValue().setHoaDon(newHD);
            hdctDAO.insert(up.getValue());
        }
        DefaultTableModel modelTable = (DefaultTableModel) tblDaChon.getModel();
        modelTable.setRowCount(0);
        DialogHelper.message(this, "Thêm hóa đơn thành công !!!", DialogHelper.INFORMATION_MESSAGE);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new FrameBanDoAn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnChon;
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JRadioButton btnDoAnNhanh;
    private javax.swing.JRadioButton btnNuocUong;
    private javax.swing.JRadioButton btnTatCa;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnMain;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblDaChon;
    private javax.swing.JTable tblDoAn;
    // End of variables declaration//GEN-END:variables
}