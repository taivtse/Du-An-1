/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.thongke;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import poly.app.core.daoimpl.PhimDaoImpl;
import poly.app.core.data.daoimpl.ProcedureDaoImpl;
import poly.app.core.helper.DialogHelper;
import poly.app.ui.custom.ClosableTabbedPane;
import poly.app.ui.custom.JComboBoxListener;
import poly.app.ui.dialogs.chart.BarChart;
import poly.app.ui.utils.TableRendererUtil;

/**
 *
 * @author vothanhtai
 */
public class FrameTKDoanhThuTheoPhim extends javax.swing.JFrame implements ClosableTabbedPane.ClosableTabbedPaneMethod{

    /**
     * Creates new form FrameQLNhanVien
     */
    Vector vectorPhimSearch = new Vector();
    List<Object[]> listPhim = new ArrayList<>();
    ProcedureDaoImpl sp_tk = new ProcedureDaoImpl();

    public FrameTKDoanhThuTheoPhim() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Thống kê phim");
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        reRenderUI();
        this.loadNamToComboBox();
    }

    private void reRenderUI() {
        for (Component component : cboTheoPhim.getComponents()) {
            if (component instanceof AbstractButton) {
                if (component.isVisible()) {
                    component.setVisible(false);
                }
            }
        }

        loadSearchPhim();

        JTextField text = (JTextField) cboTheoPhim.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.setText("");
        text.addKeyListener(new JComboBoxListener(cboTheoPhim, vectorPhimSearch));

        cboTheoPhim.getEditor().getEditorComponent().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setFocusable(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setFocusable(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //        Render lại giao diện cho table
        TableRendererUtil tblRenderer = new TableRendererUtil(tblThongKe);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();

        tblRenderer.setColoumnWidthByPersent(0, 5);
        tblRenderer.setColoumnWidthByPersent(1, 40);
        
        tblRenderer.setColumnAlignment(2, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(3, TableRendererUtil.CELL_ALIGN_RIGHT);
        tblRenderer.setColumnAlignment(4, TableRendererUtil.CELL_ALIGN_RIGHT);
        

        dcsTheoNgay.setDate(new Date());
    }

    private void loadSearchPhim() {
        vectorPhimSearch = new Vector(new PhimDaoImpl().getAll());
    }

    private void searchByPhim() {
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        for (Object[] objects : listPhim) {
            String filmName = (String) objects[0];
            if (filmName.equals(cboTheoPhim.getSelectedItem().toString())) {
                SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
                DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###");
                Object[] record = new Object[]{
                    1, objects[0], fm.format(objects[1]), objects[2], decimalFormat.format(objects[3])
                };
                modelTable.addRow(record);

                return;
            }
        }
        modelTable.setRowCount(0);
    }

    public JPanel getMainPanel() {
        synchronizedData();
        return this.pnlMain;
    }
    
    public void synchronizedData(){
        reloadData();
        cboTheoPhimItemStateChanged(null);
    }
    
    private void reloadData() {
        if (rdoTheoNgay.isSelected()) {
            rdoTheoNgayActionPerformed(null);
        }else if(rdoTheoThang.isSelected()){
            rdoTheoThangActionPerformed(null);
        }else if (rdoTheoNam.isSelected()) {
            rdoTheoNamActionPerformed(null);
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

        btngr = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rdoTheoThang = new javax.swing.JRadioButton();
        rdoTheoNam = new javax.swing.JRadioButton();
        cboTheoThang = new javax.swing.JComboBox<>();
        cboNamTheoThang = new javax.swing.JComboBox<>();
        cboTheoNam = new javax.swing.JComboBox<>();
        rdoTheoNgay = new javax.swing.JRadioButton();
        dcsTheoNgay = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        cboTheoPhim = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblTong = new javax.swing.JLabel();
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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Theo thời gian"), "Theo thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 13))); // NOI18N

        btngr.add(rdoTheoThang);
        rdoTheoThang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoThang.setText("Theo tháng");
        rdoTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThangActionPerformed(evt);
            }
        });

        btngr.add(rdoTheoNam);
        rdoTheoNam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoNam.setText("Theo năm");
        rdoTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNamActionPerformed(evt);
            }
        });

        cboTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboTheoThang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboTheoThang.setEnabled(false);
        cboTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoThangItemStateChanged(evt);
            }
        });

        cboNamTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018" }));
        cboNamTheoThang.setToolTipText("");
        cboNamTheoThang.setEnabled(false);
        cboNamTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamTheoThangItemStateChanged(evt);
            }
        });

        cboTheoNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018" }));
        cboTheoNam.setEnabled(false);
        cboTheoNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoNamItemStateChanged(evt);
            }
        });

        btngr.add(rdoTheoNgay);
        rdoTheoNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoNgay.setSelected(true);
        rdoTheoNgay.setText("Theo ngày");
        rdoTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgayActionPerformed(evt);
            }
        });

        dcsTheoNgay.setDateFormatString("dd-MM-yyyy");
        dcsTheoNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
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
                    .addComponent(dcsTheoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdoTheoNgay)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboTheoNam, 0, 120, Short.MAX_VALUE)
                            .addComponent(rdoTheoThang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNam, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTheoThang, 0, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoTheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcsTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Theo phim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 13))); // NOI18N

        cboTheoPhim.setEditable(true);
        cboTheoPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboTheoPhim.setFocusable(false);
        cboTheoPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoPhimItemStateChanged(evt);
            }
        });
        cboTheoPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTheoPhimMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTheoPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTheoPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 83, 104));
        jLabel1.setText("Bộ lọc thống kê");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnSua.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnSua.setText("Xem biểu đồ");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton2.setText("Xuất file excel");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblTong.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblTong.setForeground(new java.awt.Color(211, 47, 47));
        lblTong.setText("Tổng doanh thu: 0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTong)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblThongKe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
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
        tblThongKe.setRowHeight(22);
        tblThongKe.setSelectionBackground(new java.awt.Color(96, 116, 129));
        tblThongKe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblThongKe.setShowHorizontalLines(false);
        tblThongKe.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        this.setDefaultSelectedComboBox();
        this.loadThongKeTheoNgay();
        loadDataToTable();
    }//GEN-LAST:event_formWindowOpened

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
            btnSua.setVisible(true);
            this.loadThongKeTheoNgay();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoNgayActionPerformed

    private void dcsTheoNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsTheoNgayPropertyChange
        if (rdoTheoNgay.isSelected()) {
            this.loadThongKeTheoNgay();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_dcsTheoNgayPropertyChange

    private void rdoTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNamActionPerformed
        this.checkRadioButton();
        if (rdoTheoNam.isSelected()) {
            btnSua.setVisible(false);
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
            btnSua.setVisible(true);
            this.loadThongKeTheoThang();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoThangActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.xemBieuDo();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.xuatFileExcel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboTheoPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTheoPhimMouseClicked
        ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setFocusable(true);
    }//GEN-LAST:event_cboTheoPhimMouseClicked

    private void cboTheoPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoPhimItemStateChanged
        if (cboTheoPhim.getSelectedItem() != null && !cboTheoPhim.getSelectedItem().toString().equals("")) {
            searchByPhim();
            tongDoanhThu();
        } else {
            loadDataToTable();
        }
    }//GEN-LAST:event_cboTheoPhimItemStateChanged

    public void setDefaultSelectedComboBox() {
        Calendar cal = Calendar.getInstance();
        cboTheoThang.setSelectedIndex(cal.get(Calendar.MONTH));
        cboNamTheoThang.getModel().setSelectedItem(cal.get(Calendar.YEAR));
        cboTheoNam.getModel().setSelectedItem(cal.get(Calendar.YEAR));
    }
    
    private void loadNamToComboBox() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 2015; i <= calendar.get(Calendar.YEAR); i++) {
            cboTheoNam.addItem(i + "");
            cboNamTheoThang.addItem(i + "");
        }
    }

    private void loadThongKeTheoNgay() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoNgay", dcsTheoNgay.getDate());
        ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setText("");
        cboTheoPhimItemStateChanged(null);
    }

    private void loadThongKeTheoThang() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoThang", cboTheoThang.getSelectedIndex() + 1, Integer.parseInt(cboNamTheoThang.getSelectedItem().toString()));
        ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setText("");
        cboTheoPhimItemStateChanged(null);
    }

    private void loadThongKeTheoNam() {
        listPhim = sp_tk.execute("sp_DoanhThuPhimTheoNam", Integer.parseInt(cboTheoNam.getSelectedItem().toString()));
        ((JTextField) cboTheoPhim.getEditor().getEditorComponent()).setText("");
        cboTheoPhimItemStateChanged(null);
    }
    
    public void tongDoanhThu() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Long tong = Long.valueOf(0);
        for (int i = 0; i<tblThongKe.getRowCount(); i++) {
            tong += Integer.parseInt(tblThongKe.getValueAt(i, 4).toString().replace(",",""));
        }
        lblTong.setText("Tổng doanh thu: " + decimalFormat.format(tong)+" VND");
    }

    public void loadDataToTable() {
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int i = 1;
        for (Object[] fill : listPhim) {
            Object[] record = new Object[]{
                i++, fill[0], fm.format(fill[1]), fill[2], decimalFormat.format(fill[3])
            };
            modelTable.addRow(record);
        }
        
        tongDoanhThu();
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

        for (int i = 0; i < tblThongKe.getRowCount(); i++) {
            String xValue = (String) tblThongKe.getValueAt(i, 1);
            Integer yValue = Integer.parseInt(tblThongKe.getValueAt(i, 4).toString().replace(",", ""));
            xData.add(xValue);
            yData.add(yValue);
        }
        new BarChart(xData, yData, "Thống kê doanh thu theo phim", this, "Tên phim", "Doanh thu", "Tên phim").displayChart();
    }

    public void inThongKe() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void xuatFileExcel() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        int rownum = 0;
        Cell cell;
        Row row;

        //set thuoc tinh cho o cua workbook
        HSSFCellStyle style = createStyleForTitle(wb);
        style.setWrapText(true);
        row = sheet.createRow(rownum);

        //tao tieu de
        for (int i = 0; i < tblThongKe.getColumnCount(); i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(tblThongKe.getColumnName(i));
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        //nhap du lieu
        for (int i = 0; i < tblThongKe.getRowCount(); i++) {
            rownum++;
            row = sheet.createRow(rownum);

            int stt = Integer.parseInt(tblThongKe.getValueAt(i, 0).toString());
            String tenphim = (String) tblThongKe.getValueAt(i, 1);

            String ngaycongchieu = (tblThongKe.getValueAt(i, 2).toString());

            int soluong = Integer.parseInt(tblThongKe.getValueAt(i, 3).toString());
            int doanhthu = Integer.parseInt(tblThongKe.getValueAt(i, 4).toString().replace(",", ""));

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(stt);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(tenphim);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(ngaycongchieu);

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(soluong);

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(doanhthu);

            for (int j = 0; j < 5; j++) {
                sheet.autoSizeColumn(i);
            }

        }

        //chọn thư muc để lưu file excel
        JFileChooser fc = new JFileChooser();
        File f = new File("DoanhThuPhim");
        fc.setSelectedFile(f);
        int option = fc.showSaveDialog(FrameTKDoanhThuTheoPhim.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            //ghi file excel
            try {
                File file = new File(path + ".xls");
                FileOutputStream outFile;
                outFile = new FileOutputStream(file);
                wb.write(outFile);
                DialogHelper.message(this, "Xuất file thành công!", DialogHelper.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                DialogHelper.message(this, "Xuất file thất bại!", DialogHelper.ERROR_MESSAGE);
                Logger.getLogger(FrameTKDoanhThuTheoDoAn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    private javax.swing.ButtonGroup btngr;
    private javax.swing.JComboBox<String> cboNamTheoThang;
    private javax.swing.JComboBox<String> cboTheoNam;
    private javax.swing.JComboBox<String> cboTheoPhim;
    private javax.swing.JComboBox<String> cboTheoThang;
    private com.toedter.calendar.JDateChooser dcsTheoNgay;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTong;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JRadioButton rdoTheoNam;
    private javax.swing.JRadioButton rdoTheoNgay;
    private javax.swing.JRadioButton rdoTheoThang;
    private javax.swing.JTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}
