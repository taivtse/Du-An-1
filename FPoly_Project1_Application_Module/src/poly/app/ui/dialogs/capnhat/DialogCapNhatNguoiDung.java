/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.capnhat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import poly.app.core.daoimpl.NguoiDungDaoImpl;
import poly.app.core.daoimpl.VaiTroDaoImpl;
import poly.app.core.entities.NguoiDung;
import poly.app.core.entities.VaiTro;
import poly.app.core.helper.APIHelper;
import poly.app.core.helper.DialogHelper;
import poly.app.core.utils.ImageUtil;
import poly.app.ui.utils.ValidationUtil;

/**
 *
 * @author vothanhtai
 */
public class DialogCapNhatNguoiDung extends javax.swing.JDialog {

    NguoiDung nguoiDung;

    /**
     * Creates new form DialogThemNhanVien
     */
    public DialogCapNhatNguoiDung(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

    }

    public DialogCapNhatNguoiDung(java.awt.Frame parent, boolean modal, NguoiDung nguoiDung) {
        this(parent, modal);

        this.nguoiDung = nguoiDung;
    }

    private void loadVaiTroToCombobox() {
        VaiTroDaoImpl vaiTroDaoImpl = new VaiTroDaoImpl();
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cboVaiTro.getModel();
        for (VaiTro vaiTro : vaiTroDaoImpl.getAll()) {
            dcbm.addElement(vaiTro);
        }
    }

    private void setModelToInput() {

        txtHoTen.setText(nguoiDung.getHoTen());
        txtDiaChi.setText(nguoiDung.getDiaChi());
        txtCMND.setText(nguoiDung.getSoCmnd());
        dcNgayVaoLam.setDate(nguoiDung.getNgayVaoLam());
        txtSoDienThoai.setText(nguoiDung.getSoDienThoai());
        rdoNu.setSelected(!nguoiDung.isGioiTinhNam());
        txtEmail.setText(nguoiDung.getEmail());
        cboVaiTro.getModel().setSelectedItem(nguoiDung.getVaiTro());
        cboTrangThai.setSelectedIndex(nguoiDung.getDangLam() ? 0 : 1);
    }

    private NguoiDung getModelFromInput() {
        nguoiDung.setHoTen(txtHoTen.getText());
        nguoiDung.setDiaChi(txtDiaChi.getText());
        nguoiDung.setSoCmnd(txtCMND.getText());
        nguoiDung.setNgayVaoLam(dcNgayVaoLam.getDate());
        nguoiDung.setSoDienThoai(txtSoDienThoai.getText());
        nguoiDung.setGioiTinh(rdoNam.isSelected());
        nguoiDung.setEmail(txtEmail.getText());
        nguoiDung.setVaiTro((VaiTro) cboVaiTro.getModel().getSelectedItem());
        nguoiDung.setDangLam(cboTrangThai.getSelectedIndex() == 0 ? true : false);

        return nguoiDung;
    }

    private boolean updateModelToDatabase() {
        try {
            new NguoiDungDaoImpl().update(getModelFromInput());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean checkInput() {
        if (ValidationUtil.isEmpty(txtHoTen.getText())) {
            DialogHelper.message(this, "Không được bỏ trống họ và tên", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (ValidationUtil.isLenghtEnought(txtHoTen.getText(), 50)) {
            DialogHelper.message(this, "Họ tên không được quá 50 ký tự", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (ValidationUtil.isEmpty(txtCMND.getText())) {
            DialogHelper.message(this, "Không được bỏ bỏ trống CMND", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (!ValidationUtil.isLenghtEqual(txtCMND.getText(), 9) && !ValidationUtil.isLenghtEqual(txtCMND.getText(), 12)) {
            DialogHelper.message(this, "CMND chỉ có 9 hoặc 12 số ", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (ValidationUtil.isEmpty(txtSoDienThoai.getText())) {
            DialogHelper.message(this, "Không được bỏ bỏ trống số điện thoại", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (!ValidationUtil.isLenghtEqual(txtSoDienThoai.getText(), 10)) {
            DialogHelper.message(this, "Số điện thoại phải chỉ bằng 10", DialogHelper.ERROR_MESSAGE);
            return false;

        } else if (!txtSoDienThoai.getText().startsWith("0")) {
            DialogHelper.message(this, "Số điện thoại phải bắt đầu bằng 0", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (ValidationUtil.isEmpty(txtEmail.getText())) {
            DialogHelper.message(this, "Không được bỏ bỏ trống Email", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (!ValidationUtil.isValidEmail(txtEmail.getText())) {
            DialogHelper.message(this, "Định dạng Email sai", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (ValidationUtil.isEmpty(txtDiaChi.getText())) {
            DialogHelper.message(this, "Không được bỏ bỏ trống Địa chỉ", DialogHelper.ERROR_MESSAGE);
            return false;
        } else if (dcNgayVaoLam.getDate() == null) {
            DialogHelper.message(this, "Không được bỏ trống ngày vào làm", DialogHelper.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }

    }
    
    private void loadImage(){
        try {
            String url = APIHelper.IMAGE_URL + nguoiDung.getHinhAnh();
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // optional default is GET
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = con.getInputStream();
                ImageIcon icon = ImageUtil.resizeImage(ImageIO.read(is), lblImage.getWidth(), lblImage.getHeight());
                lblImage.setIcon(icon);
            }
        } catch (Exception ex) {
            Logger.getLogger(DialogCapNhatPhim.class.getName()).log(Level.SEVERE, null, ex);
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
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        cboVaiTro = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dcNgayVaoLam = new com.toedter.calendar.JDateChooser();
        txtEmail = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setText("Giới tính");

        txtCMND.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCMNDKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("CMND");

        jPanel3.setOpaque(false);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/employees.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(52, 83, 104));
        jLabel13.setText("Cập nhật thông tin người dùng");

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        jLabel14.setText("Vui lòng nhập đầy đủ thông tin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14))
        );

        txtSoDienThoai.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyTyped(evt);
            }
        });

        cboVaiTro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");

        dcNgayVaoLam.setDateFormatString("dd-MM-yyyy");
        dcNgayVaoLam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcNgayVaoLam.setOpaque(false);

        txtEmail.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage.setOpaque(true);
        lblImage.setPreferredSize(new java.awt.Dimension(136, 136));
        jPanel4.add(lblImage, new java.awt.GridBagConstraints());

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái");

        cboTrangThai.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm ", "Đã nghỉ" }));

        txtDiaChi.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel10.setText("Vai trò");

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        jLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel1.setText("Họ tên");

        jLabel16.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel16.setText("Ngày vào làm");

        txtHoTen.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoTenKeyTyped(evt);
            }
        });

        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnLuu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.setPreferredSize(new java.awt.Dimension(75, 33));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel5.add(btnLuu, gridBagConstraints);

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
        jPanel5.add(btnHuy, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNam)
                                .addGap(30, 30, 30)
                                .addComponent(rdoNu)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadVaiTroToCombobox();
        setModelToInput();
        loadImage();
    }//GEN-LAST:event_formWindowOpened

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (checkInput()) {
            if (updateModelToDatabase()) {
                DialogHelper.message(this, "Cập nhật dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                DialogHelper.message(this, "Cập nhật dữ liệu dữ liệu thất bại!", DialogHelper.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtCMNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyTyped
        if (String.valueOf(evt.getKeyChar()).matches("\\D") || txtCMND.getText().length() == 12) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCMNDKeyTyped

    private void txtSoDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyTyped
        if (String.valueOf(evt.getKeyChar()).matches("\\D") || txtSoDienThoai.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoDienThoaiKeyTyped

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void txtHoTenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyTyped
        if (txtHoTen.getText().length()==50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHoTenKeyTyped

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
            java.util.logging.Logger.getLogger(DialogCapNhatNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCapNhatNguoiDung dialog = new DialogCapNhatNguoiDung(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLuu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private com.toedter.calendar.JDateChooser dcNgayVaoLam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
