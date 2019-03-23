/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.banhang;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.swing.JRViewer;
import poly.app.core.dao.VeBanDao;
import poly.app.core.daoimpl.GiaVeDaoImpl;
import poly.app.core.daoimpl.VeBanDaoImpl;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.GiaVe;
import poly.app.core.entities.LoaiGhe;
import poly.app.core.entities.SuatChieu;
import poly.app.core.entities.VeBan;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.ui.report.VeBanReportParameter;
import poly.app.ui.utils.ReportPrinterUtil;
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
        tblRenderer.setColoumnWidthByPersent(2, 5);
        tblRenderer.setColoumnWidthByPersent(4, 5);
        tblRenderer.setColoumnWidthByPersent(6, 5);

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
                    LoaiGhe loaiGhe = (LoaiGhe) tblThongTin.getValueAt(i, 1);
                    GiaVe giaVe = (GiaVe) tblThongTin.getValueAt(i, 3);

                    int phuThuSuatChieu = Integer.parseInt(tblThongTin.getValueAt(i, 5).toString().replace(",", ""));

                    tblThongTin.setValueAt(new DecimalFormat("#,###,###").format(giaVe.getDonGia()), i, 4);

                    tblThongTin.setValueAt(new DecimalFormat("#,###,###").format(loaiGhe.getPhuThu() + giaVe.getDonGia() + phuThuSuatChieu), i, 6);
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
        txtMaSuatChieu.setText(suatChieu.getId());
        txtNgayChieu.setText(new SimpleDateFormat("dd-MM-yyyy").format(suatChieu.getNgayChieu()));
        txtTenPhim.setText(suatChieu.getPhim().getTen());
        txtGioChieu.setText(new SimpleDateFormat("HH:mm:ss").format(suatChieu.getGioBatDau()) + " - "
                + new SimpleDateFormat("HH:mm:ss").format(suatChieu.getGioKetThuc()));
        txtDinhDang.setText(suatChieu.getDinhDangPhim().getTen());
        txtPhongChieu.setText("Phòng " + suatChieu.getPhongChieu().getId());
    }

    private void setDataToTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblThongTin.getModel();

        DecimalFormat df = new DecimalFormat("#,###,###");

        for (Map.Entry<String, GheNgoi> entry : selectedGheNgoiMap.entrySet()) {
            String viTriGhe = entry.getKey();
            GheNgoi gheNgoi = entry.getValue();

            defaultTableModel.addRow(new Object[]{
                viTriGhe,
                gheNgoi.getLoaiGhe(),
                df.format(gheNgoi.getLoaiGhe().getPhuThu()),
                giaveList.get(0),
                df.format(giaveList.get(0).getDonGia()),
                df.format(suatChieu.getDinhDangPhim().getPhuThu()),
                df.format(gheNgoi.getLoaiGhe().getPhuThu() + giaveList.get(0).getDonGia() + suatChieu.getDinhDangPhim().getPhuThu())
            });
        }
    }

    private void tinhTongTien() {
        int total = 0;

        for (int i = 0; i < tblThongTin.getRowCount(); i++) {
            total += Integer.parseInt(tblThongTin.getValueAt(i, 6).toString().replace(",", ""));
        }

        lblTongTien.setText(new DecimalFormat("##,###,###").format(total) + " VND");
    }

    private void inVeban(List<VeBanReportParameter> listReportParameters) {
        ReportPrinterUtil.printMultiReport(listReportParameters, ReportPrinterUtil.HOADON_REPORT_URL, false);
//       ReportPrinterUtil.showMultiPrintPreview(listReportParameters, ReportPrinterUtil.VEBAN_REPORT_URL);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgayChieu = new javax.swing.JTextField();
        txtTenPhim = new javax.swing.JTextField();
        txtGioChieu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDinhDang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhongChieu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnInVe = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaSuatChieu = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/invoice.png"))); // NOI18N

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
                .addContainerGap(676, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel2))
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Ngày chiếu");

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("Tên phim");

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("Giờ chiếu");

        txtNgayChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNgayChieu.setEnabled(false);

        txtTenPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTenPhim.setEnabled(false);

        txtGioChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtGioChieu.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setText("Định dạng");

        txtDinhDang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtDinhDang.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel10.setText("Phòng  chiếu");

        txtPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtPhongChieu.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Tổng cộng");

        tblThongTin.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Vị trí ghế", "Loại ghế", "Phụ thu", "Đối tượng", "Giá vé", "Phụ thu suất chiếu", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTin.setRowHeight(22);
        tblThongTin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblThongTinKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongTin);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnInVe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnInVe.setText("In vé");
        btnInVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInVe.setPreferredSize(new java.awt.Dimension(76, 33));
        btnInVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInVeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(btnInVe, gridBagConstraints);

        btnHuy.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setPreferredSize(new java.awt.Dimension(75, 33));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(btnHuy, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Open Sans", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(52, 83, 104));
        jLabel8.setText("Thông tin suất chiếu");

        jLabel12.setFont(new java.awt.Font("Open Sans", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(52, 83, 104));
        jLabel12.setText("Thông tin ghế ngồi");

        lblTongTien.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(211, 47, 47));
        lblTongTien.setText("10,000 vnd");

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Mã suất chiếu");

        txtMaSuatChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtMaSuatChieu.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtGioChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTongTien)))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGioChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
        boolean isSuccess = true;
        VeBanDao veBanDao = new VeBanDaoImpl();
        List<VeBanReportParameter> listReportParameters = new ArrayList<>();
        try {
            for (int i = 0; i < tblThongTin.getRowCount(); i++) {
                GheNgoi gheNgoi = selectedGheNgoiMap.get(tblThongTin.getValueAt(i, 0));
                GiaVe giaVe = (GiaVe) tblThongTin.getValueAt(i, 3);
                int tongTien = Integer.parseInt(tblThongTin.getValueAt(i, 6).toString().replace(",", ""));

                VeBan veBan = new VeBan("", gheNgoi, giaVe, suatChieu, new Date(), tongTien);
                veBan.setNguoiDung(ShareHelper.USER);

                veBanDao.insert(veBan);

                VeBanReportParameter reportParameter
                        = new VeBanReportParameter(suatChieu.getPhim().getTen(),
                                tongTien,
                                suatChieu.getDinhDangPhim().getId(),
                                suatChieu.getPhongChieu().getId(),
                                suatChieu.getNgayChieu(),
                                suatChieu.getGioBatDau(),
                                gheNgoi.getViTriDay() + gheNgoi.getViTriCot());
                listReportParameters.add(reportParameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        if (isSuccess) {
            this.inVeban(listReportParameters);
            DialogHelper.message(this, "In vé bán thành công!", DialogHelper.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            DialogHelper.message(this, "Đã xảy ra lỗi trong quá trình thêm vé bán!", DialogHelper.ERROR_MESSAGE);
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtDinhDang;
    private javax.swing.JTextField txtGioChieu;
    private javax.swing.JTextField txtMaSuatChieu;
    private javax.swing.JTextField txtNgayChieu;
    private javax.swing.JTextField txtPhongChieu;
    private javax.swing.JTextField txtTenPhim;
    // End of variables declaration//GEN-END:variables
}
