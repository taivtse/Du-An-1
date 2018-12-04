/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.main;

//import com.apple.eawt.Application;
import java.awt.CardLayout;
import javax.swing.JLabel;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.core.utils.HibernateUtil;
import poly.app.ui.custom.ClosableTabbedPane;
import poly.app.ui.utils.ColorUtil;
import poly.app.ui.dialogs.dangnhap.DialogDangNhap;
import poly.app.ui.dialogs.orther.DialogSplashScreen;
import poly.app.ui.frames.banhang.FrameBanDoAn;
import poly.app.ui.frames.banhang.FrameBanVe;
import poly.app.ui.frames.quanly.FrameQLPhim;
import poly.app.ui.frames.quanly.FrameQLDoAn;
import poly.app.ui.frames.quanly.FrameQLHoaDon;
import poly.app.ui.frames.quanly.FrameQLKhachHang;
import poly.app.ui.frames.quanly.FrameQLNguoiDung;
import poly.app.ui.frames.quanly.FrameQLSuatChieu;
import poly.app.ui.frames.quanly.FrameQLVeBan;

/**
 *
 * @author vothanhtai
 */
public class MainRunningFrame extends javax.swing.JFrame {

    private JLabel[] btnToolBarArr;

    private DialogSplashScreen splashScreen;
    private FrameQLPhim frameQLPhim;
    private FrameQLSuatChieu frameQLSuatChieu;
    private FrameQLDoAn frameQLDoAn;
    private FrameQLNguoiDung frameQLNguoiDung;
    private FrameQLKhachHang frameQLKhachHang;
    private FrameQLHoaDon frameQLHoaDon;
    private FrameQLVeBan frameQLVeBan;

    private FrameBanDoAn frameBanDoAn;
    private FrameBanVe frameBanVe;
    
    private int numberOfThreadLoaded = 0;
    private final int maxnumberOfThreadLoaded = 3;

    /**
     * Creates new form MainFrame
     */
    public MainRunningFrame() {
        changeAppIcon();
        renderMainUIInBackground();
        loadHibernateSession();
        renderChildFrame();
        showSplashScreen();
    }
    
    private void changeAppIcon() {
        setIconImage(ShareHelper.APP_ICON);
//        Application.getApplication().setDockIconImage(ShareHelper.APP_ICON);
    }

    private void reRenderUI() {
        setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        toolBarContainer.add(jToolBar1, "toolbar1");
        toolBarContainer.add(jToolBar2, "toolbar2");
        toolBarContainer.add(jToolBar3, "toolbar3");
        btnToolBarArr = new JLabel[]{btnToolBarDanhMuc, btnToolBarBanHang, btnToolBarThongKe};
    }

    private void renderMainUIInBackground() {
        new Thread(() -> {
            initComponents();
            reRenderUI();
            
            System.out.println("main ui");
            numberOfThreadLoaded++;
            if (numberOfThreadLoaded == maxnumberOfThreadLoaded) {
                showLoginDialog();
            }
        }).start();
    }

    private void loadHibernateSession() {
        new Thread(() -> {
            HibernateUtil.getSessionFactory();
            
            System.out.println("hibernate");
            numberOfThreadLoaded++;
            if (numberOfThreadLoaded == maxnumberOfThreadLoaded) {
                showLoginDialog();
            }
        }).start();
    }

    private void renderChildFrame() {
        new Thread(() -> {
            frameQLPhim = new FrameQLPhim();
            frameQLSuatChieu = new FrameQLSuatChieu();
            frameQLDoAn = new FrameQLDoAn();
            frameQLNguoiDung = new FrameQLNguoiDung();
            frameQLKhachHang = new FrameQLKhachHang();
            frameQLHoaDon = new FrameQLHoaDon();
            frameQLVeBan = new FrameQLVeBan();

            frameBanDoAn = new FrameBanDoAn();
            frameBanVe = new FrameBanVe();
            
            System.out.println("child ui");
            numberOfThreadLoaded++;
            if (numberOfThreadLoaded == maxnumberOfThreadLoaded) {
                showLoginDialog();
            }
        }).start();
    }

    private void setBtnToolBarClickEvent(JLabel btnToolBar) {
        for (JLabel btn : btnToolBarArr) {
            btn.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT);
            btn.setForeground(ColorUtil.TOOLBAR_FOREGROUND_COLOR_DEFAULT);
        }

        btnToolBar.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_SELECTED);
        btnToolBar.setForeground(ColorUtil.TOOLBAR_FOREGROUND_COLOR_SELECTED);

        btnToolBar.repaint();
        btnToolBar.validate();
    }

    private void showSplashScreen() {
        splashScreen = new DialogSplashScreen(this, true);
        splashScreen.setVisible(true);
    }

    private void disposeSplashScreen() {
        splashScreen.dispose();
    }
    
    private void showLoginDialog(){
        disposeSplashScreen();
        new DialogDangNhap(this, true).setVisible(true);
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
        jPanel4 = new javax.swing.JPanel();
        btnToolBarDanhMuc = new javax.swing.JLabel();
        btnToolBarBanHang = new javax.swing.JLabel();
        btnToolBarThongKe = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JLabel();
        lblTenTaiKhoan = new javax.swing.JLabel();
        toolBarContainer = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        itemDanhMucToolBarPhim = new javax.swing.JButton();
        itemDanhMucToolBarSuatChieu = new javax.swing.JButton();
        itemDanhMucToolBarThucAn = new javax.swing.JButton();
        itemDanhMucToolBarNhanVien = new javax.swing.JButton();
        itemDanhMucToolBarKhachHang = new javax.swing.JButton();
        itemDanhMucToolBarHoaDon = new javax.swing.JButton();
        itemDanhMucToolBarVe = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        itemBanHangToolBarThucAn = new javax.swing.JButton();
        itemBanHangToolBarVe = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jPanel3 = new javax.swing.JPanel();
        tbpMainContent = new ClosableTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(52, 83, 104));

        btnToolBarDanhMuc.setBackground(new java.awt.Color(255, 255, 255));
        btnToolBarDanhMuc.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        btnToolBarDanhMuc.setForeground(new java.awt.Color(52, 83, 104));
        btnToolBarDanhMuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnToolBarDanhMuc.setText("Danh mục");
        btnToolBarDanhMuc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnToolBarDanhMuc.setOpaque(true);
        btnToolBarDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnToolBarDanhMucMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnToolBarDanhMucMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnToolBarDanhMucMouseEntered(evt);
            }
        });

        btnToolBarBanHang.setBackground(new java.awt.Color(52, 83, 104));
        btnToolBarBanHang.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        btnToolBarBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnToolBarBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnToolBarBanHang.setText("Bán hàng");
        btnToolBarBanHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnToolBarBanHang.setOpaque(true);
        btnToolBarBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnToolBarBanHangMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnToolBarBanHangMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnToolBarBanHangMouseEntered(evt);
            }
        });

        btnToolBarThongKe.setBackground(new java.awt.Color(52, 83, 104));
        btnToolBarThongKe.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        btnToolBarThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnToolBarThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnToolBarThongKe.setText("Thống kê");
        btnToolBarThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnToolBarThongKe.setOpaque(true);
        btnToolBarThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnToolBarThongKeMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnToolBarThongKeMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnToolBarThongKeMouseEntered(evt);
            }
        });

        btnDangXuat.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 15));
        btnDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseReleased(evt);
            }
        });

        lblTenTaiKhoan.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/user.png"))); // NOI18N
        lblTenTaiKhoan.setText("name");
        lblTenTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnToolBarDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnToolBarBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnToolBarThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTenTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDangXuat))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnToolBarDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
            .addComponent(btnToolBarBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnToolBarThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        toolBarContainer.setBackground(new java.awt.Color(255, 255, 255));
        toolBarContainer.setLayout(new java.awt.CardLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        itemDanhMucToolBarPhim.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarPhim.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/film-reel.png"))); // NOI18N
        itemDanhMucToolBarPhim.setText("Phim");
        itemDanhMucToolBarPhim.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarPhim.setFocusable(false);
        itemDanhMucToolBarPhim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarPhim.setIconTextGap(7);
        itemDanhMucToolBarPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarPhimActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarPhim);

        itemDanhMucToolBarSuatChieu.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarSuatChieu.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarSuatChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/clapperboard.png"))); // NOI18N
        itemDanhMucToolBarSuatChieu.setText("Suất chiếu");
        itemDanhMucToolBarSuatChieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarSuatChieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarSuatChieu.setFocusable(false);
        itemDanhMucToolBarSuatChieu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarSuatChieu.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarSuatChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarSuatChieuActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarSuatChieu);

        itemDanhMucToolBarThucAn.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarThucAn.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarThucAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/popcorn.png"))); // NOI18N
        itemDanhMucToolBarThucAn.setText("Đồ ăn");
        itemDanhMucToolBarThucAn.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarThucAn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarThucAn.setFocusable(false);
        itemDanhMucToolBarThucAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarThucAn.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarThucAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarThucAnActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarThucAn);

        itemDanhMucToolBarNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarNhanVien.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/employee.png"))); // NOI18N
        itemDanhMucToolBarNhanVien.setText("Người dùng");
        itemDanhMucToolBarNhanVien.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarNhanVien.setFocusable(false);
        itemDanhMucToolBarNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarNhanVien.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarNhanVienActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarNhanVien);

        itemDanhMucToolBarKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarKhachHang.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/customer.png"))); // NOI18N
        itemDanhMucToolBarKhachHang.setText("Khách hàng");
        itemDanhMucToolBarKhachHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarKhachHang.setFocusable(false);
        itemDanhMucToolBarKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarKhachHang.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarKhachHangActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarKhachHang);

        itemDanhMucToolBarHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarHoaDon.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/payment.png"))); // NOI18N
        itemDanhMucToolBarHoaDon.setText("Hoá đơn");
        itemDanhMucToolBarHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarHoaDon.setFocusable(false);
        itemDanhMucToolBarHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarHoaDon.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarHoaDonActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarHoaDon);

        itemDanhMucToolBarVe.setBackground(new java.awt.Color(255, 255, 255));
        itemDanhMucToolBarVe.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemDanhMucToolBarVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/tickets.png"))); // NOI18N
        itemDanhMucToolBarVe.setText("Vé");
        itemDanhMucToolBarVe.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemDanhMucToolBarVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemDanhMucToolBarVe.setFocusable(false);
        itemDanhMucToolBarVe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemDanhMucToolBarVe.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemDanhMucToolBarVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDanhMucToolBarVeActionPerformed(evt);
            }
        });
        jToolBar1.add(itemDanhMucToolBarVe);

        toolBarContainer.add(jToolBar1, "card2");

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setOpaque(false);

        itemBanHangToolBarThucAn.setBackground(new java.awt.Color(255, 255, 255));
        itemBanHangToolBarThucAn.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemBanHangToolBarThucAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/popcorn.png"))); // NOI18N
        itemBanHangToolBarThucAn.setText("Đồ ăn");
        itemBanHangToolBarThucAn.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemBanHangToolBarThucAn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemBanHangToolBarThucAn.setFocusable(false);
        itemBanHangToolBarThucAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemBanHangToolBarThucAn.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemBanHangToolBarThucAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBanHangToolBarThucAnActionPerformed(evt);
            }
        });
        jToolBar2.add(itemBanHangToolBarThucAn);

        itemBanHangToolBarVe.setBackground(new java.awt.Color(255, 255, 255));
        itemBanHangToolBarVe.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        itemBanHangToolBarVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/tickets.png"))); // NOI18N
        itemBanHangToolBarVe.setText("Vé xem phim");
        itemBanHangToolBarVe.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        itemBanHangToolBarVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemBanHangToolBarVe.setFocusable(false);
        itemBanHangToolBarVe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        itemBanHangToolBarVe.setMargin(new java.awt.Insets(5, 10, 5, 10));
        itemBanHangToolBarVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBanHangToolBarVeActionPerformed(evt);
            }
        });
        jToolBar2.add(itemBanHangToolBarVe);

        toolBarContainer.add(jToolBar2, "card3");

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);
        jToolBar3.setOpaque(false);
        toolBarContainer.add(jToolBar3, "card4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolBarContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolBarContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(131, 137, 150));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));

        tbpMainContent.setBackground(new java.awt.Color(255, 255, 255));
        tbpMainContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tbpMainContent.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jPanel3.add(tbpMainContent);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/app-logo.png"))); // NOI18N
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnToolBarDanhMucMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarDanhMucMouseEntered
        if (btnToolBarDanhMuc.getBackground().equals(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT)) {
            btnToolBarDanhMuc.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_ENTERED);
        }
    }//GEN-LAST:event_btnToolBarDanhMucMouseEntered

    private void btnToolBarDanhMucMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarDanhMucMouseExited
        if (btnToolBarDanhMuc.getForeground().equals(ColorUtil.TOOLBAR_FOREGROUND_COLOR_DEFAULT)) {
            btnToolBarDanhMuc.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT);
        } else {
            btnToolBarDanhMuc.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_SELECTED);
        }
    }//GEN-LAST:event_btnToolBarDanhMucMouseExited

    private void btnToolBarBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarBanHangMouseEntered
        if (btnToolBarBanHang.getBackground().equals(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT)) {
            btnToolBarBanHang.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_ENTERED);
        }
    }//GEN-LAST:event_btnToolBarBanHangMouseEntered

    private void btnToolBarBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarBanHangMouseExited
        if (btnToolBarBanHang.getForeground().equals(ColorUtil.TOOLBAR_FOREGROUND_COLOR_DEFAULT)) {
            btnToolBarBanHang.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT);
        } else {
            btnToolBarBanHang.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_SELECTED);
        }
    }//GEN-LAST:event_btnToolBarBanHangMouseExited

    private void btnToolBarThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarThongKeMouseEntered
        if (btnToolBarThongKe.getBackground().equals(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT)) {
            btnToolBarThongKe.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_ENTERED);
        }
    }//GEN-LAST:event_btnToolBarThongKeMouseEntered

    private void btnToolBarThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarThongKeMouseExited
        if (btnToolBarThongKe.getForeground().equals(ColorUtil.TOOLBAR_FOREGROUND_COLOR_DEFAULT)) {
            btnToolBarThongKe.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_DEFAULT);
        } else {
            btnToolBarThongKe.setBackground(ColorUtil.TOOLBAR_BACKGROUND_COLOR_SELECTED);
        }
    }//GEN-LAST:event_btnToolBarThongKeMouseExited

    private void btnToolBarDanhMucMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarDanhMucMouseReleased
        setBtnToolBarClickEvent(btnToolBarDanhMuc);
        ((CardLayout) toolBarContainer.getLayout()).show(toolBarContainer, "toolbar1");
    }//GEN-LAST:event_btnToolBarDanhMucMouseReleased

    private void btnToolBarBanHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarBanHangMouseReleased
        setBtnToolBarClickEvent(btnToolBarBanHang);
        ((CardLayout) toolBarContainer.getLayout()).show(toolBarContainer, "toolbar2");
    }//GEN-LAST:event_btnToolBarBanHangMouseReleased

    private void btnToolBarThongKeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToolBarThongKeMouseReleased
        setBtnToolBarClickEvent(btnToolBarThongKe);
        ((CardLayout) toolBarContainer.getLayout()).show(toolBarContainer, "toolbar3");
    }//GEN-LAST:event_btnToolBarThongKeMouseReleased

    private void itemDanhMucToolBarPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarPhimActionPerformed
        tbpMainContent.addTab(frameQLPhim.getTitle(), frameQLPhim.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarPhimActionPerformed

    private void itemDanhMucToolBarSuatChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarSuatChieuActionPerformed
        tbpMainContent.addTab(frameQLSuatChieu.getTitle(), frameQLSuatChieu.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarSuatChieuActionPerformed

    private void itemDanhMucToolBarThucAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarThucAnActionPerformed
        tbpMainContent.addTab(frameQLDoAn.getTitle(), frameQLDoAn.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarThucAnActionPerformed

    private void itemDanhMucToolBarNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarNhanVienActionPerformed
        tbpMainContent.addTab(frameQLNguoiDung.getTitle(), frameQLNguoiDung.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarNhanVienActionPerformed

    private void itemDanhMucToolBarKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarKhachHangActionPerformed
        tbpMainContent.addTab(frameQLKhachHang.getTitle(), frameQLKhachHang.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarKhachHangActionPerformed

    private void itemDanhMucToolBarHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarHoaDonActionPerformed
        tbpMainContent.addTab(frameQLHoaDon.getTitle(), frameQLHoaDon.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarHoaDonActionPerformed

    private void itemDanhMucToolBarVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDanhMucToolBarVeActionPerformed
        tbpMainContent.addTab(frameQLVeBan.getTitle(), frameQLVeBan.getMainPanel());
    }//GEN-LAST:event_itemDanhMucToolBarVeActionPerformed

    private void itemBanHangToolBarThucAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBanHangToolBarThucAnActionPerformed
        tbpMainContent.addTab(frameBanDoAn.getTitle(), frameBanDoAn.getMainPanel());
    }//GEN-LAST:event_itemBanHangToolBarThucAnActionPerformed

    private void itemBanHangToolBarVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBanHangToolBarVeActionPerformed
        tbpMainContent.addTab(frameBanVe.getTitle(), frameBanVe.getMainPanel());
    }//GEN-LAST:event_itemBanHangToolBarVeActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (ShareHelper.USER == null) {
            DialogHelper.message(this, "Hệ thống đã xảy ra lỗi", DialogHelper.ERROR_MESSAGE);
            System.exit(0);
        }else{
            lblTenTaiKhoan.setText(ShareHelper.USER.getHoTen());
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnDangXuatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseReleased
        ShareHelper.USER = null;
        this.setVisible(false);
        this.showLoginDialog();
    }//GEN-LAST:event_btnDangXuatMouseReleased

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
            java.util.logging.Logger.getLogger(MainRunningFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainRunningFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainRunningFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainRunningFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainRunningFrame();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDangXuat;
    private javax.swing.JLabel btnToolBarBanHang;
    private javax.swing.JLabel btnToolBarDanhMuc;
    private javax.swing.JLabel btnToolBarThongKe;
    private javax.swing.JButton itemBanHangToolBarThucAn;
    private javax.swing.JButton itemBanHangToolBarVe;
    private javax.swing.JButton itemDanhMucToolBarHoaDon;
    private javax.swing.JButton itemDanhMucToolBarKhachHang;
    private javax.swing.JButton itemDanhMucToolBarNhanVien;
    private javax.swing.JButton itemDanhMucToolBarPhim;
    private javax.swing.JButton itemDanhMucToolBarSuatChieu;
    private javax.swing.JButton itemDanhMucToolBarThucAn;
    private javax.swing.JButton itemDanhMucToolBarVe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JLabel lblTenTaiKhoan;
    private ClosableTabbedPane tbpMainContent;
    private javax.swing.JPanel toolBarContainer;
    // End of variables declaration//GEN-END:variables

}
