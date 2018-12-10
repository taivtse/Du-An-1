/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.thongke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import poly.app.core.data.daoimpl.ProcedureDaoImpl;
import poly.app.ui.utils.TableRendererUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import poly.app.core.helper.DialogHelper;
import poly.app.ui.custom.ClosableTabbedPane;
import poly.app.ui.dialogs.chart.BarChart;
import poly.app.ui.dialogs.chart.PieCharts;

/**
 *
 * @author vothanhtai
 */
public class FrameTKTongDoanhThu extends javax.swing.JFrame implements ClosableTabbedPane.ClosableTabbedPaneMethod{

    ProcedureDaoImpl sp_tk = new ProcedureDaoImpl();
    List<Object[]> listDoanhThu = new ArrayList<>();

    /**
     * b
     * Creates new form FrameQLNhanVien
     */
    public FrameTKTongDoanhThu() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Thống kê tổng doanh thu");
        dcTheoNgay.setDate(new Date());
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        reRenderUI();
        loadNamToComboBox();
    }

    private void reRenderUI() {
        TableRendererUtil tblRenderer = new TableRendererUtil(tblThongKe);
        tblRenderer.setCellEditable(false);
        tblRenderer.changeHeaderStyle();
        
        tblRenderer.setColumnAlignment(0, TableRendererUtil.CELL_ALIGN_CENTER);
        tblRenderer.setColumnAlignment(1, TableRendererUtil.CELL_ALIGN_RIGHT);
        tblRenderer.setColumnAlignment(2, TableRendererUtil.CELL_ALIGN_RIGHT);
        tblRenderer.setColumnAlignment(3, TableRendererUtil.CELL_ALIGN_RIGHT);        
    }

    private void loadThongKeTheoThang() {
        listDoanhThu = sp_tk.execute("sp_TongDoanhThuTheoThang", cboTheoThang.getSelectedIndex() + 1, Integer.parseInt(cboNamTheoThang.getSelectedItem().toString()));
    }

    private void loadThongKeTheoNam() {
        listDoanhThu = sp_tk.execute("sp_TongDoanhThuTheoNam", Integer.parseInt(cboTheoNam.getSelectedItem().toString()));
        System.out.println(cboTheoNam.getSelectedItem().toString());
    }

    private void loadThongKeTheoNgay() {
        listDoanhThu = sp_tk.execute("sp_TongDoanhThuTheoNgay", dcTheoNgay.getDate());
    }
    
    public void tongDoanhThu() {
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###");
        Long tong = Long.valueOf(0);
        for (int i = 0; i<tblThongKe.getRowCount(); i++) {
            tong += Integer.parseInt(tblThongKe.getValueAt(i, 3).toString().replace(",",""));
        }
        lblTong.setText("Tổng doanh thu: " + decimalFormat.format(tong)+" VND");
    }

    public void loadDataToTable() {
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###");
        for (Object[] fill : listDoanhThu) {
            Object[] record = new Object[]{
                formatter.format(fill[0]), decimalFormat.format(fill[1]), decimalFormat.format(fill[2]), decimalFormat.format(fill[3])
            };
            modelTable.addRow(record);
        }
        
        tongDoanhThu();
    }

    public void loadDataToTableTheoNam() {
        DefaultTableModel modelTable = (DefaultTableModel) tblThongKe.getModel();
        modelTable.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        for (Object[] fill : listDoanhThu) {

            Object[] record = new Object[]{
                fill[0] + "-" + cboTheoNam.getSelectedItem(), decimalFormat.format(fill[1]), decimalFormat.format(fill[2]), decimalFormat.format(fill[3])
            };
            modelTable.addRow(record);

        }
    }

    private void checkRadioButton() {
        cboTheoThang.setEnabled(rdoTheoThang.isSelected());
        cboNamTheoThang.setEnabled(rdoTheoThang.isSelected());
        dcTheoNgay.setEnabled(rdoTheoNgay.isSelected());
        cboTheoNam.setEnabled(rdoTheoNam.isSelected());
    }

    private void loadNamToComboBox() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 2015; i <= calendar.get(Calendar.YEAR); i++) {
            cboTheoNam.addItem(i + "");
            cboNamTheoThang.addItem(i + "");
        }
    }

    public void xemBieuDo(String title, String tg) {
        List<String> xData = new ArrayList<String>();
        List<Integer> yData = new ArrayList<>();

        for (int i = 0; i < tblThongKe.getRowCount(); i++) {
            String str = tblThongKe.getValueAt(i, 0).toString();
            String xValue = str.substring(0, str.indexOf("-"));
            Integer yValue = Integer.parseInt(tblThongKe.getValueAt(i, 3).toString().replace(",", ""));

            xData.add(xValue);
            yData.add(yValue);
        }
        new BarChart(xData, yData, "Thống Kê Tổng Doanh Thu Của " + title, this, tg, "Doanh Thu", "Doanh Thu").displayChart();
    }

    private void outFileExcell() throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Tổng Danh thu");
        int rowNum = 0;
        Cell cell;
        Row row;
        row = sheet.createRow(rowNum);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        for (int i = 0; i < tblThongKe.getColumnCount(); i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(tblThongKe.getColumnName(i));
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < tblThongKe.getRowCount(); i++) {

            row = sheet.createRow(i + 1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(tblThongKe.getValueAt(i, 0).toString());
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue((Double.parseDouble(tblThongKe.getValueAt(i, 1).toString().replace(",", ""))));
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue((Double.parseDouble(tblThongKe.getValueAt(i, 2).toString().replace(",", ""))));
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue((Double.parseDouble(tblThongKe.getValueAt(i, 3).toString().replace(",", ""))));
            
            for (int j = 0; j < 4; j++) {
                sheet.autoSizeColumn(i);
            }
        }

        JFileChooser fc = new JFileChooser();
        File f = new File("TongDoanhThu");
        fc.setSelectedFile(f);
        int option = fc.showSaveDialog(FrameTKTongDoanhThu.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            //ghi file excel
            try {
                File file = new File(path + ".xls");
                FileOutputStream outFile;
                outFile = new FileOutputStream(file);
                workbook.write(outFile);
                DialogHelper.message(this, "Xuất excel thành công !", DialogHelper.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void XemBieuDoTron(String title) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int tongVe = 0;
        for (int i = 1; i <= 2; i++) {
            String key = tblThongKe.getColumnName(i);
            for (int j = 0; j < tblThongKe.getRowCount(); j++) {
                tongVe += Integer.parseInt(tblThongKe.getValueAt(j, i).toString().replace(",", ""));
            }
            map.put(key, tongVe);
            tongVe = 0;
        }
        new PieCharts().displayChart(map, "Thống Kê Tổng Doanh Thu Của " + title);
    }
    
    public JPanel getMainPanel() {
        synchronizedData();
        return this.pnlMain;
    }
    
    public void synchronizedData(){
        resetSearchForm();
        this.setDefaultSelectedComboBox();
        this.loadThongKeTheoNgay();
        this.loadDataToTable();
    }
    
    private void resetSearchForm() {
        rdoTheoNgay.setSelected(true);
        rdoTheoThang.setSelected(false);
        rdoTheoNam.setSelected(false);
        
        cboTheoThang.setEnabled(false);
        cboNamTheoThang.setEnabled(false);
        cboTheoNam.setEnabled(false);
    }

    
    public void setDefaultSelectedComboBox() {
        Calendar cal = Calendar.getInstance();
        cboTheoThang.setSelectedIndex(cal.get(Calendar.MONTH));
        cboNamTheoThang.getModel().setSelectedItem(cal.get(Calendar.YEAR));
        cboTheoNam.getModel().setSelectedItem(cal.get(Calendar.YEAR));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rdoTheoThang = new javax.swing.JRadioButton();
        rdoTheoNam = new javax.swing.JRadioButton();
        cboTheoThang = new javax.swing.JComboBox<>();
        cboNamTheoThang = new javax.swing.JComboBox<>();
        cboTheoNam = new javax.swing.JComboBox<>();
        rdoTheoNgay = new javax.swing.JRadioButton();
        dcTheoNgay = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnXemBieuDoCot = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnXemBieuDoTron = new javax.swing.JButton();
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

        buttonGroup1.add(rdoTheoThang);
        rdoTheoThang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoThang.setText("Theo tháng");
        rdoTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoThangActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTheoNam);
        rdoTheoNam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoNam.setText("Theo năm");
        rdoTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNamActionPerformed(evt);
            }
        });

        cboTheoThang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboTheoThang.setEnabled(false);
        cboTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoThangItemStateChanged(evt);
            }
        });
        cboTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoThangActionPerformed(evt);
            }
        });

        cboNamTheoThang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboNamTheoThang.setEnabled(false);
        cboNamTheoThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamTheoThangItemStateChanged(evt);
            }
        });

        cboTheoNam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboTheoNam.setEnabled(false);
        cboTheoNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoNamItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdoTheoNgay);
        rdoTheoNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTheoNgay.setSelected(true);
        rdoTheoNgay.setText("Theo ngày");
        rdoTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTheoNgayActionPerformed(evt);
            }
        });

        dcTheoNgay.setDateFormatString("dd-MM-yyyy");
        dcTheoNgay.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcTheoNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcTheoNgayPropertyChange(evt);
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
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTheoNgay)
                            .addComponent(rdoTheoThang)
                            .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoTheoNam))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcTheoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoTheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoTheoThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rdoTheoNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        btnXemBieuDoCot.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnXemBieuDoCot.setText("Xem biểu đồ cột");
        btnXemBieuDoCot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXemBieuDoCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBieuDoCotActionPerformed(evt);
            }
        });

        btnXuatFile.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnXuatFile.setText("Xuất file excel");
        btnXuatFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnXemBieuDoTron.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnXemBieuDoTron.setText("Xem biểu đồ tròn");
        btnXemBieuDoTron.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXemBieuDoTron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBieuDoTronActionPerformed(evt);
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
                .addComponent(btnXemBieuDoCot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemBieuDoTron)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTong)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemBieuDoCot, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemBieuDoTron, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setOpaque(false);

        tblThongKe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu đồ ăn", "Doanh thu vé bán", "Tổng doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
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
        loadDataToTable();
        this.setDefaultSelectedComboBox();
    }//GEN-LAST:event_formWindowOpened

    private void cboTheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTheoThangActionPerformed

    private void cboTheoNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoNamItemStateChanged
        if (rdoTheoNam.isSelected()) {
            this.loadThongKeTheoNam();
            this.loadDataToTableTheoNam();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTheoNamItemStateChanged

    private void rdoTheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoThangActionPerformed
        this.checkRadioButton();
        if (rdoTheoThang.isSelected()) {
            this.loadThongKeTheoThang();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoThangActionPerformed

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

    private void rdoTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNamActionPerformed
        this.checkRadioButton();
        if (rdoTheoNam.isSelected()) {
            this.loadThongKeTheoNam();
            this.loadDataToTableTheoNam();
        }
    }//GEN-LAST:event_rdoTheoNamActionPerformed

    private void btnXemBieuDoCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBieuDoCotActionPerformed
        if (tblThongKe.getRowCount() > 0) {
            if (rdoTheoNgay.isSelected()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                this.xemBieuDo("Ngày " + formatter.format(dcTheoNgay.getDate()), "Ngày");
            } else if (rdoTheoThang.isSelected()) {
                this.xemBieuDo(cboTheoThang.getSelectedItem().toString() + "-" + cboNamTheoThang.getSelectedItem().toString(), "Ngày");
            } else {
                this.xemBieuDo("Năm " + cboTheoNam.getSelectedItem().toString(), "Tháng");
            }
        } else {
            DialogHelper.message(this, "Không có dữ liệu thống kê !", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemBieuDoCotActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        if (tblThongKe.getRowCount() <= 0) {
            DialogHelper.message(this, "Không có dữ liệu xuất file", DialogHelper.ERROR_MESSAGE);
        } else {
            try {
                outFileExcell();

            } catch (IOException ex) {
                Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void rdoTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTheoNgayActionPerformed
        this.checkRadioButton();
        if (rdoTheoNgay.isSelected()) {
            this.loadThongKeTheoNgay();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_rdoTheoNgayActionPerformed

    private void dcTheoNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcTheoNgayPropertyChange
        if (rdoTheoNgay.isSelected()) {
            this.loadThongKeTheoNgay();
            this.loadDataToTable();
        }
    }//GEN-LAST:event_dcTheoNgayPropertyChange

    private void btnXemBieuDoTronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBieuDoTronActionPerformed
        if (tblThongKe.getRowCount() > 0) {
            if (rdoTheoNgay.isSelected()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                this.XemBieuDoTron((formatter.format(dcTheoNgay.getDate())));
            } else if (rdoTheoThang.isSelected()) {
                this.XemBieuDoTron(cboTheoThang.getSelectedItem().toString() + "-" + cboNamTheoThang.getSelectedItem().toString());
            } else {
                this.XemBieuDoTron(cboTheoNam.getSelectedItem().toString());
            }
        } else {
            DialogHelper.message(this, "Không có dữ liệu thống kê !", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemBieuDoTronActionPerformed

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
            java.util.logging.Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTKTongDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrameTKTongDoanhThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCollapse;
    private javax.swing.JButton btnXemBieuDoCot;
    private javax.swing.JButton btnXemBieuDoTron;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNamTheoThang;
    private javax.swing.JComboBox<String> cboTheoNam;
    private javax.swing.JComboBox<String> cboTheoThang;
    private com.toedter.calendar.JDateChooser dcTheoNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
