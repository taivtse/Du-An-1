/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.thongke;

import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.daoimpl.LoaiPhimDaoImpl;
import poly.app.core.data.daoimpl.ProcedureDaoImpl;
import poly.app.core.entities.LoaiPhim;
import poly.app.ui.dialogs.chart.BarChart;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameTKDoanhThuTheoPhim extends javax.swing.JFrame {

    /**
     * Creates new form FrameQLNhanVien
     */
    List<Object[]> listPhim = new ArrayList<>();
    ProcedureDaoImpl sp_tk = new ProcedureDaoImpl();
    
    public FrameTKDoanhThuTheoPhim() {
        initComponents();
        setLocationRelativeTo(null);
        reRenderUI();
    }

    private void reRenderUI() {
        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblThongKe);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
        dcsTheoNgay.setDate(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngr = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cboLoaiPhim = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        rdoTheoThang = new javax.swing.JRadioButton();
        rdoTheoNam = new javax.swing.JRadioButton();
        cboTheoThang = new javax.swing.JComboBox<>();
        cboNamTheoThang = new javax.swing.JComboBox<>();
        cboTheoNam = new javax.swing.JComboBox<>();
        rdoTheoNgay = new javax.swing.JRadioButton();
        dcsTheoNgay = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        btnCollapse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setText("Bộ lọc:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Theo loại phim"));

        cboLoaiPhim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboLoaiPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiPhimItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboLoaiPhim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(cboLoaiPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Theo thời gian"), "Theo thời gian"));

        btngr.add(rdoTheoThang);
        rdoTheoThang.setText("Theo tháng");
        rdoTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThangActionPerformed(evt);
            }
        });

        btngr.add(rdoTheoNam);
        rdoTheoNam.setText("Theo năm");
        rdoTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNamActionPerformed(evt);
            }
        });

        cboTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoThangItemStateChanged(evt);
            }
        });

        cboNamTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamTheoThangItemStateChanged(evt);
            }
        });

        cboTheoNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoNamItemStateChanged(evt);
            }
        });

        btngr.add(rdoTheoNgay);
        rdoTheoNgay.setSelected(true);
        rdoTheoNgay.setText("Theo ngày");
        rdoTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgayActionPerformed(evt);
            }
        });

        dcsTheoNgay.setDateFormatString("dd-MM-yyyy");
        dcsTheoNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsTheoNgayPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboTheoNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoTheoThang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNam, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTheoThang, 0, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNgay)
                            .addComponent(dcsTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoTheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcsTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(rdoTheoThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(rdoTheoNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnThem.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnThem.setText("In thống kê");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnSua.setText("Xem biểu đồ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất file excel");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel5.setOpaque(false);

        tblThongKe.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên phim", "Ngày công chiếu ", "Số lượng vé bán ", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
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

        btnCollapse.setBackground(new java.awt.Color(51, 51, 51));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCollapse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCollapse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.loadNamToComboBox();
        this.loadThongKeTheoNgay();
        loadDataToTable();
    }//GEN-LAST:event_formWindowOpened

    private void cboLoaiPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiPhimItemStateChanged
        this.filterLoaiPhim();
    }//GEN-LAST:event_cboLoaiPhimItemStateChanged

    private void cboTheoThangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoThangItemStateChanged
        if (rdoTheoThang.isSelected()) {
            this.loadThongKeTheoThang();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_cboTheoThangItemStateChanged

    private void cboNamTheoThangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamTheoThangItemStateChanged
        if (rdoTheoThang.isSelected()) {
            this.loadThongKeTheoThang();
            this.loadDataToTable();
        }   
    }//GEN-LAST:event_cboNamTheoThangItemStateChanged

    private void rdoTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgayActionPerformed
        this.checkRadioButton();
        if (rdoTheoNgay.isSelected()) {
            this.loadThongKeTheoNgay();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoNgayActionPerformed

    private void dcsTheoNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgayPropertyChange
        this.loadThongKeTheoNgay();
        this.loadDataToTable();
    }//GEN-LAST:event_dcsTheoNgayPropertyChange

    private void rdoTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNamActionPerformed
        this.checkRadioButton();
        if (rdoTheoNam.isSelected()) {
            this.loadThongKeTheoNam();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoNamActionPerformed

    private void cboTheoNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoNamItemStateChanged
        if (rdoTheoNam.isSelected()) {
            this.loadThongKeTheoNam();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_cboTheoNamItemStateChanged

    private void rdoTheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThangActionPerformed
        this.checkRadioButton();
        if (rdoTheoThang.isSelected()) {
            this.loadThongKeTheoThang();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoThangActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.xemBieuDo();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.inThongKe();
    }//GEN-LAST:event_btnThemActionPerformed
    
    private void loadLoaiDoAnToCombobox() {

        DefaultComboBoxModel modelComboBox = (DefaultComboBoxModel) cboLoaiPhim.getModel();
        LoaiPhimDaoImpl lp = new LoaiPhimDaoImpl();
        List<LoaiPhim> listLP = lp.getAll();
        for (LoaiPhim fill : listLP) {
            modelComboBox.addElement(fill);
        }
    }
    
    private void loadNamToComboBox() {

        Calendar calendar = Calendar.getInstance();
        for (int i = 2015; i <= calendar.get(Calendar.YEAR); i++) {
            cboTheoNam.addItem(i + "");
            cboNamTheoThang.addItem(i + "");
        }
    }
    
    private void filterLoaiPhim() {
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        int i = 1;
        for (Object[] objArr : listPhim) {
            if (cboLoaiPhim.getSelectedIndex() == 0) {
                Object[] record = new Object[]{
                    i++, objArr[0], objArr[1], objArr[3], objArr[4]
                };
                modelTable.addRow(record);
            } else if (objArr[1].toString().equals(cboLoaiPhim.getSelectedItem().toString())) {
                Object[] record = new Object[]{
                    i++, objArr[0], objArr[1], objArr[3], objArr[4]
                };
                modelTable.addRow(record);
            }
        }
    }
    
    private void loadThongKeTheoNgay() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoNgay", dcsTheoNgay.getDate());
    }

    private void loadThongKeTheoThang() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoThang", cboTheoThang.getSelectedIndex()+1, Integer.parseInt(cboNamTheoThang.getSelectedItem().toString()));
    }

    private void loadThongKeTheoNam() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoNam", Integer.parseInt(cboTheoNam.getSelectedItem().toString()));
    }
    
    public void loadDataToTable(){
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        int i = 1;
        for (Object[] fill : listPhim) {
            System.out.println(fill);
            Object[] record = new Object[]{
                i++, fill[0], fill[1], fill[2], fill[3]
            };
            modelTable.addRow(record);
        }
    }
    
    private void checkRadioButton() {
        dcsTheoNgay.setEnabled(rdoTheoNgay.isSelected());
       
        cboTheoThang.setEnabled(rdoTheoThang.isSelected());
        cboNamTheoThang.setEnabled(rdoTheoThang.isSelected());
        
        cboTheoNam.setEnabled(rdoTheoNam.isSelected());

    }
    
    public void xemBieuDo() {
    List<String> xData = new ArrayList<String>();
        List<Integer> yData = new ArrayList<>();
        
        
        for(int i =0; i<tblThongKe.getRowCount();i++){
            String xValue = (String) tblThongKe.getValueAt(i, 1);
            Integer yValue = Integer.parseInt(tblThongKe.getValueAt(i, 4).toString());
            System.out.println(yValue);
            xData.add(xValue);
            yData.add(yValue);
        }
        new BarChart().displayChart(xData, yData, "Thống Kê Doanh Thu Theo Đồ Ăn", this);
    }
    
    public void inThongKe() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

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
            java.util.logging.Logger.getLogger(FrameTKDoanhThuTheoPhim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTKDoanhThuTheoPhim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTKDoanhThuTheoPhim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTKDoanhThuTheoPhim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameTKDoanhThuTheoPhim().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup btngr;
    private javax.swing.JComboBox<String> cboLoaiPhim;
    private javax.swing.JComboBox<String> cboNamTheoThang;
    private javax.swing.JComboBox<String> cboTheoNam;
    private javax.swing.JComboBox<String> cboTheoThang;
    private com.toedter.calendar.JDateChooser dcsTheoNgay;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoTheoNam;
    private javax.swing.JRadioButton rdoTheoNgay;
    private javax.swing.JRadioButton rdoTheoThang;
    private javax.swing.JTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}