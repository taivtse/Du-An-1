/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.capnhat;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import poly.app.core.daoimpl.DinhDangPhimDaoImpl;
import poly.app.core.daoimpl.PhimDaoImpl;
import poly.app.core.daoimpl.PhongChieuDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.DinhDangPhim;
import poly.app.core.entities.Phim;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;
import poly.app.core.helper.DateHelper;
import poly.app.core.helper.DialogHelper;
import poly.app.ui.custom.PanelSuatChieuItem;
import poly.app.ui.custom.PanelKhoangCachItem;

/**
 *
 * @author vothanhtai
 */
public class DialogCapNhatSuatChieu extends javax.swing.JDialog {

    private GroupLayout layout;
    private GroupLayout.ParallelGroup parallel;
    private GroupLayout.SequentialGroup sequential;
    private boolean isAllComboboxLoaded = false;
    private int updatingIndex = -1;
    private List<PanelSuatChieuItem> suatChieuItemList = new ArrayList<>();

    /**
     * Creates new form DialogCapNhatSuatChieu
     */
    public DialogCapNhatSuatChieu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public DialogCapNhatSuatChieu(java.awt.Frame parent, boolean modal, Date ngayChieu, PhongChieu phongChieu) {
        this(parent, modal);

        dcsNgayChieuFilter.setDate(ngayChieu);
        cboPhongChieuFilter.getModel().setSelectedItem(phongChieu);
        reRenderUI();
        
        isAllComboboxLoaded = false;
        loadDataToCboPhongChieu();
        loadDataToCboPhim();
        loadDataToCboDinhDang();
        isAllComboboxLoaded = true;
        loadTimeLine();
    }

    public DialogCapNhatSuatChieu(java.awt.Frame parent, boolean modal, Date ngayChieu, PhongChieu phongChieu, String suatChieuId) {
        this(parent, modal, ngayChieu, phongChieu);
        for (PanelSuatChieuItem panelSuatChieuItem : suatChieuItemList) {
            if (panelSuatChieuItem.getSuatChieu().getId().equals(suatChieuId)) {
                suatChieuItemClickAction(panelSuatChieuItem);
                break;
            }
        }
    }

    private void reRenderUI() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        spnGioBatDau.setModel(model);
        spnGioBatDau.setEditor(new JSpinner.DateEditor(spnGioBatDau, "HH:mm:ss"));

        SpinnerDateModel model1 = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        spnGioKetThuc.setModel(model1);
        spnGioKetThuc.setEditor(new JSpinner.DateEditor(spnGioKetThuc, "HH:mm:ss"));

        dcsNgayChieu.setDate(dcsNgayChieuFilter.getDate());
    }

    private void loadTimeLine() {
        buildPanelLichChieuLayout();

        List<SuatChieu> suatChieuList = new SuatChieuDaoImpl().getSuatChieuByNgayVaByPhong(
                dcsNgayChieuFilter.getDate(), (PhongChieu) cboPhongChieuFilter.getSelectedItem());
        suatChieuItemList.clear();

        for (int i = 0; i < suatChieuList.size(); i++) {
            SuatChieu suatChieu = suatChieuList.get(i);

            PanelSuatChieuItem suatChieuItem = createSuatChieuItem(suatChieu, i);

//            Add suatChieuItem to pnlLichChieu
            addItemToPanelLichChieu(suatChieuItem);

//            Add suatChieuItem to suatChieuItemList
            suatChieuItemList.add(suatChieuItem);

            if (i < suatChieuList.size() - 1) {
                PanelKhoangCachItem khoangCachItem = createKhoangCachItem(suatChieuList.get(i), suatChieuList.get(i + 1), i);
                addItemToPanelLichChieu(khoangCachItem);
            }

            if (updatingIndex == -1) {
                suatChieuItemClickAction(suatChieuItem);
            }
        };

        pnlLichChieu.revalidate();
        pnlLichChieu.repaint();
    }

    private void buildPanelLichChieuLayout() {
//        render timeline ui
        pnlLichChieu.removeAll();
        layout = (GroupLayout) pnlLichChieu.getLayout();
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
    }

    private void addItemToPanelLichChieu(Component component) {
        //            Add suatChieuItem to pnlLichChieu
        parallel.addGroup(layout.createSequentialGroup()
                .addComponent(component));
        sequential.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(component));
    }

    private PanelSuatChieuItem createSuatChieuItem(SuatChieu suatChieu, int index) {
        Dimension panelSize = pnlLichChieu.getSize();
        //            render suatChieuItem
        PanelSuatChieuItem suatChieuItem = new PanelSuatChieuItem(suatChieu, index);

        Dimension itemSize = suatChieuItem.getPreferredSize();
        itemSize.width = panelSize.width;
        suatChieuItem.setMaximumSize(itemSize);

//        Add click event
        suatChieuItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                suatChieuItemClickAction(suatChieuItem);
            }
        });

        return suatChieuItem;
    }

    private PanelKhoangCachItem createKhoangCachItem(SuatChieu prevSuatChieu, SuatChieu nextSuatChieu, int prevSuatChieuIndex) {
        Dimension panelSize = pnlLichChieu.getSize();
//            render suatChieuItem
        int khoangCach = DateHelper.minutesDiff(prevSuatChieu.getGioKetThuc(), nextSuatChieu.getGioBatDau());
        PanelKhoangCachItem khoangCachItem = new PanelKhoangCachItem(khoangCach, prevSuatChieuIndex);

        Dimension itemSize = khoangCachItem.getPreferredSize();
        itemSize.width = panelSize.width;
        khoangCachItem.setMaximumSize(itemSize);

        //        Add click event
        khoangCachItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                khoangCachItemClickAction(khoangCachItem);
            }
        });

        return khoangCachItem;
    }

    private void khoangCachItemClickAction(PanelKhoangCachItem khoangCachItem) {
        int newKhoangCach;
        try {
            String inputStr = DialogHelper.prompt(this, "Nhập thời gian chờ");
            if (inputStr == null) {
                return;
            }
            newKhoangCach = Integer.parseInt(inputStr);
            if (newKhoangCach < 0) {
                throw new Exception("Thời gian chờ bé hơn 0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.message(this, "Thời gian chờ không hợp lệ!", DialogHelper.ERROR_MESSAGE);
            return;
        }

        int changeKhoangCach = newKhoangCach - khoangCachItem.getKhoangCach();

        SuatChieu prevSuatChieu = suatChieuItemList.get(khoangCachItem.getPrevSuatChieuIndex()).getSuatChieu();

        if (changeKhoangCach > 0) {
            Date nextDayMidNight = DateHelper.getMidNight(DateHelper.rollDays(dcsNgayChieu.getDate(), 1));

            for (int i = khoangCachItem.getPrevSuatChieuIndex() + 1; i < suatChieuItemList.size(); i++) {
                SuatChieu nextSuatChieu = suatChieuItemList.get(i).getSuatChieu();
                Date newNextGioBatDau = DateHelper.rollTime(nextSuatChieu.getGioBatDau(), Calendar.MINUTE, changeKhoangCach);
                if (newNextGioBatDau.compareTo(nextDayMidNight) >= 0) {
                    String alertStr = "Lỗi: thời gian chờ quá lớn sẽ đẩy các suất chiếu sau sang ngày tiếp theo";
                    DialogHelper.message(this, alertStr, DialogHelper.ERROR_MESSAGE);
                    return;
                }
            }
        }

        try {
            new SuatChieuDaoImpl().updateThoiGianCacSuatChieu(prevSuatChieu, changeKhoangCach);
            DialogHelper.message(this, "Cập nhật dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);

            loadTimeLine();

            suatChieuItemList.get(updatingIndex).setItemSelected();
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.message(this, "Cập nhật dữ liệu thất bại!", DialogHelper.ERROR_MESSAGE);
        }
    }

    private void suatChieuItemClickAction(PanelSuatChieuItem suatChieuItem) {
        setUpdatingState();
        Component[] components = pnlLichChieu.getComponents();
        for (Component component : components) {
            if (component instanceof PanelSuatChieuItem) {
                ((PanelSuatChieuItem) component).setItemUnSelected();
            }
        }
        suatChieuItem.setItemSelected();
        updatingIndex = suatChieuItem.getSuatChieuIndex();

        SuatChieu updatingSuatChieu = suatChieuItem.getSuatChieu();

        cboPhim.getModel().setSelectedItem(updatingSuatChieu.getPhim());
        cboDinhDang.getModel().setSelectedItem(updatingSuatChieu.getDinhDangPhim());
        spnGioBatDau.setValue(updatingSuatChieu.getGioBatDau());
        spnGioKetThuc.setValue(updatingSuatChieu.getGioKetThuc());

        if (updatingSuatChieu.getGioBatDau().compareTo(new Date()) < 0) {
            btnSaveOrUpdate.setEnabled(false);
            btnResetOrDelete.setEnabled(false);
        } else {
            btnSaveOrUpdate.setEnabled(true);
            btnResetOrDelete.setEnabled(true);
        }
    }

    private void loadDataToCboPhongChieu() {
        DefaultComboBoxModel comboBoxModel1 = (DefaultComboBoxModel) cboPhongChieu.getModel();
        comboBoxModel1.removeAllElements();
        DefaultComboBoxModel comboBoxModel2 = (DefaultComboBoxModel) cboPhongChieuFilter.getModel();
        comboBoxModel2.removeAllElements();
        new PhongChieuDaoImpl().getAll().forEach((phongChieu) -> {
            comboBoxModel1.addElement(phongChieu);
            comboBoxModel2.addElement(phongChieu);
        });
    }

    private void loadDataToCboPhim() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboPhim.getModel();
        comboBoxModel.removeAllElements();
        new PhimDaoImpl().getPhimDangChieu().forEach((phim) -> {
            comboBoxModel.addElement(phim);
        });
    }

    private void loadDataToCboDinhDang() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboDinhDang.getModel();
        comboBoxModel.removeAllElements();
        new DinhDangPhimDaoImpl().getAll().forEach((dinhDang) -> {
            comboBoxModel.addElement(dinhDang);
        });
    }

    private void setAddingState() {
        lblActionInfo.setText("Thêm suất chiếu");
        cboPhim.setSelectedIndex(0);
        cboDinhDang.setSelectedIndex(0);
        btnSaveOrUpdate.setText("Thêm");
        btnResetOrDelete.setText("Reset");
        btnSaveOrUpdate.setEnabled(true);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dcsNgayChieu.getDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Phim phim = (Phim) cboPhim.getModel().getSelectedItem();
        Date newGioBatDau = calendar.getTime();        

        spnGioBatDau.setValue(newGioBatDau);
        spnGioKetThuc.setValue(DateHelper.rollTime(newGioBatDau, Calendar.MINUTE, phim.getThoiLuong()));
        

        Component[] components = pnlLichChieu.getComponents();
        for (Component component : components) {
            if (component instanceof PanelSuatChieuItem) {
                ((PanelSuatChieuItem) component).setItemUnSelected();
            }
        }
        updatingIndex = -1;
    }

    private void setUpdatingState() {
        lblActionInfo.setText("Cập nhật suất chiếu");
        btnSaveOrUpdate.setText("Cập nhật");
        btnResetOrDelete.setText("Xoá");
    }

    private Date getMergeDateAndTime(Date inputTime, Date inputDate) {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(inputDate);
        String timeStr = new SimpleDateFormat("HH:mm:ss").format(inputTime);
        LocalDateTime dt = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.parse(timeStr));

        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }

    private boolean checkGioBatDauSuatChieu() {
        Date newGioBatDau = (Date) spnGioBatDau.getValue();
        Date newGioKetThuc = (Date) spnGioKetThuc.getValue();

        for (PanelSuatChieuItem panelSuatChieuItem : suatChieuItemList) {
            SuatChieu curSuatChieu = panelSuatChieuItem.getSuatChieu();
            if (panelSuatChieuItem.getSuatChieuIndex() != updatingIndex
                    && ((curSuatChieu.getGioBatDau().compareTo(newGioBatDau) >= 0
                    && curSuatChieu.getGioBatDau().compareTo(newGioKetThuc) <= 0)
                    || (curSuatChieu.getGioKetThuc().compareTo(newGioBatDau) >= 0
                    && curSuatChieu.getGioKetThuc().compareTo(newGioKetThuc) <= 0))) {

                String alertStr = "Suất chiếu bị trùng với suất:\n"
                        + curSuatChieu.getPhim().getTen() + " (" + DateHelper.toTimeString(curSuatChieu.getGioBatDau())
                        + " - " + DateHelper.toTimeString(curSuatChieu.getGioKetThuc()) + ")";

                DialogHelper.message(this, alertStr, DialogHelper.ERROR_MESSAGE);
                return false;
            }
        }

        if (newGioKetThuc.compareTo(newGioBatDau) > 0) {
            List<SuatChieu> nextDaySuatChieus = new SuatChieuDaoImpl().getSuatChieuByNgayVaByPhong(DateHelper.rollDays(dcsNgayChieu.getDate(), 1), (PhongChieu) cboPhongChieu.getSelectedItem());
            for (SuatChieu nextDaySuatChieu : nextDaySuatChieus) {
                if (nextDaySuatChieu.getGioBatDau().compareTo(newGioKetThuc) < 0
                        && nextDaySuatChieu.getGioKetThuc().compareTo(newGioKetThuc) > 0) {

                    String alertStr = "Suất chiếu bị trùng với suất:\n"
                            + nextDaySuatChieu.getPhim().getTen() + " (" + DateHelper.toTimeString(nextDaySuatChieu.getGioBatDau())
                            + " - " + DateHelper.toTimeString(nextDaySuatChieu.getGioKetThuc()) + ")"
                            + "\ncủa ngày hôm sau";

                    DialogHelper.message(this, alertStr, DialogHelper.ERROR_MESSAGE);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkThoiGianSuatChieu() {
        Date newGioBatDau = (Date) spnGioBatDau.getValue();
        Date newGioKetThuc = (Date) spnGioKetThuc.getValue();

        for (PanelSuatChieuItem panelSuatChieuItem : suatChieuItemList) {
            SuatChieu curSuatChieu = panelSuatChieuItem.getSuatChieu();
            if (panelSuatChieuItem.getSuatChieuIndex() != updatingIndex) {
                if ((curSuatChieu.getGioBatDau().compareTo(newGioBatDau) >= 0
                    && curSuatChieu.getGioBatDau().compareTo(newGioKetThuc) <= 0)
                    || 
                        (curSuatChieu.getGioKetThuc().compareTo(newGioBatDau) >= 0
                    && curSuatChieu.getGioKetThuc().compareTo(newGioKetThuc) <= 0)) {
                    String alertStr = "Suất chiếu bị trùng với suất:\n"
                            + curSuatChieu.getPhim().getTen() + " (" + DateHelper.toTimeString(curSuatChieu.getGioBatDau())
                            + " - " + DateHelper.toTimeString(curSuatChieu.getGioKetThuc()) + ")";

                    DialogHelper.message(this, alertStr, DialogHelper.ERROR_MESSAGE);
                    return false;
                }
            }
        }

        return true;
    }

    private Boolean updateSuatChieu() {
        SuatChieu updatingSuatChieu = suatChieuItemList.get(updatingIndex).getSuatChieu();
        boolean isUpdated = true;
        if (!checkGioBatDauSuatChieu()) {
            return null;
        }

        Date newGioBatDau = (Date) spnGioBatDau.getValue();
        Date newGioKetThuc = (Date) spnGioKetThuc.getValue();
        int durationChange = DateHelper.minutesDiff(updatingSuatChieu.getGioKetThuc(), newGioKetThuc);

        Phim phim = (Phim) cboPhim.getModel().getSelectedItem();
        DinhDangPhim dinhDangPhim = (DinhDangPhim) cboDinhDang.getModel().getSelectedItem();

        SuatChieu nextSuatChieu = null;
        if (updatingIndex + 1 < suatChieuItemList.size()) {
            nextSuatChieu = suatChieuItemList.get(updatingIndex + 1).getSuatChieu();
        }

        if (nextSuatChieu != null && updatingSuatChieu.getGioKetThuc().compareTo(nextSuatChieu.getGioBatDau()) > 0) {
            String alertStr = "Lưu ý: khi cập nhật thì những suất chiếu sau sẽ tăng thời gian bắt đầu";
            if (DialogHelper.confirm(this, alertStr)) {
//                Cap nhat thong tin suat chieu
                updatingSuatChieu.setPhim(phim);
                updatingSuatChieu.setDinhDangPhim(dinhDangPhim);
                updatingSuatChieu.setGioBatDau(newGioBatDau);
                updatingSuatChieu.setGioKetThuc(newGioKetThuc);

                try {
                    isUpdated = new SuatChieuDaoImpl().updateAnhHuongSuatChieuSau(updatingSuatChieu, durationChange);
                } catch (Exception e) {
                    e.printStackTrace();
                    isUpdated = false;
                }
            } else {
                return null;
            }
        } else {
            updatingSuatChieu.setPhim(phim);
            updatingSuatChieu.setDinhDangPhim(dinhDangPhim);
            updatingSuatChieu.setGioBatDau(newGioBatDau);
            updatingSuatChieu.setGioKetThuc(newGioKetThuc);

            isUpdated = new SuatChieuDaoImpl().update(updatingSuatChieu);
        }

        return isUpdated;
    }

    private SuatChieu insertSuatChieu() {
        Date gioBatDau = (Date) spnGioBatDau.getValue();
        Date gioKetThuc = (Date) spnGioKetThuc.getValue();

        if (!checkThoiGianSuatChieu()) {
            return null;
        }

        Date ngayChieu = dcsNgayChieu.getDate();
        PhongChieu phongChieu = (PhongChieu) cboPhongChieu.getModel().getSelectedItem();
        Phim phim = (Phim) cboPhim.getModel().getSelectedItem();
        DinhDangPhim dinhDangPhim = (DinhDangPhim) cboDinhDang.getModel().getSelectedItem();

        SuatChieu suatChieu = new SuatChieu(dinhDangPhim, phim, phongChieu, gioBatDau, gioKetThuc, ngayChieu);
        suatChieu.setId("");

        try {
            new SuatChieuDaoImpl().insert(suatChieu);
            DialogHelper.message(this, "Thêm dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.message(this, "Thêm dữ liệu thất bại!", DialogHelper.ERROR_MESSAGE);
            return null;
        }

        return suatChieu;
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
        jLabel3 = new javax.swing.JLabel();
        dcsNgayChieuFilter = new com.toedter.calendar.JDateChooser();
        cboPhongChieuFilter = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlLichChieu = new javax.swing.JPanel();
        btnAddNew = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblActionInfo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboPhim = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cboDinhDang = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        dcsNgayChieu = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        spnGioBatDau = new javax.swing.JSpinner();
        spnGioKetThuc = new javax.swing.JSpinner();
        btnSaveOrUpdate = new javax.swing.JButton();
        btnResetOrDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setPreferredSize(new java.awt.Dimension(310, 513));

        jLabel3.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 83, 104));
        jLabel3.setText("Danh sách suất chiếu");

        dcsNgayChieuFilter.setDateFormatString("dd-MM-yyyy");
        dcsNgayChieuFilter.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcsNgayChieuFilter.setOpaque(false);
        dcsNgayChieuFilter.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsNgayChieuFilterPropertyChange(evt);
            }
        });

        cboPhongChieuFilter.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhongChieuFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhongChieuFilterItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcsNgayChieuFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboPhongChieuFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcsNgayChieuFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPhongChieuFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setOpaque(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);

        pnlLichChieu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlLichChieuLayout = new javax.swing.GroupLayout(pnlLichChieu);
        pnlLichChieu.setLayout(pnlLichChieuLayout);
        pnlLichChieuLayout.setHorizontalGroup(
            pnlLichChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        pnlLichChieuLayout.setVerticalGroup(
            pnlLichChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlLichChieu);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        btnAddNew.setBackground(new java.awt.Color(52, 83, 104));
        btnAddNew.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnAddNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/add.png"))); // NOI18N
        btnAddNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddNew.setOpaque(true);
        btnAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddNewMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        jPanel3.setOpaque(false);

        jPanel9.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/ui/icons/video-camera.png"))); // NOI18N

        lblActionInfo.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        lblActionInfo.setForeground(new java.awt.Color(52, 83, 104));
        lblActionInfo.setText("Cập nhật suất chiếu");

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        jLabel4.setText("Vui lòng nhập đầy đủ thông tin");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblActionInfo)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblActionInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4))
        );

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel10.setText("Tên phim");

        cboPhim.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhimItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Phòng chiếu");

        cboPhongChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPhongChieu.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("Định dạng");

        cboDinhDang.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Ngày chiếu");

        dcsNgayChieu.setDateFormatString("dd-MM-yyyy");
        dcsNgayChieu.setEnabled(false);
        dcsNgayChieu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        dcsNgayChieu.setOpaque(false);

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("Giờ bắt đầu");

        jLabel15.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel15.setText("Giờ kết thúc");

        spnGioBatDau.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        spnGioBatDau.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnGioBatDauStateChanged(evt);
            }
        });

        spnGioKetThuc.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        spnGioKetThuc.setEnabled(false);

        btnSaveOrUpdate.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnSaveOrUpdate.setText("Cập nhật");
        btnSaveOrUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveOrUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveOrUpdateActionPerformed(evt);
            }
        });

        btnResetOrDelete.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnResetOrDelete.setText("Xoá");
        btnResetOrDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetOrDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetOrDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSaveOrUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetOrDelete))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnGioKetThuc))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnGioBatDau))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcsNgayChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDinhDang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcsNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveOrUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetOrDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveOrUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveOrUpdateActionPerformed
        if (btnSaveOrUpdate.getText().equals("Cập nhật")) {
            Boolean isUpdated = updateSuatChieu();

            if (isUpdated != null) {
                if (isUpdated) {
                    DialogHelper.message(this, "Cập nhật dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);
                    loadTimeLine();

                    suatChieuItemList.get(updatingIndex).setItemSelected();

                    setUpdatingState();
                } else {
                    DialogHelper.message(this, "Cập nhật dữ liệu thất bại!", DialogHelper.ERROR_MESSAGE);
                }
            }
        } else {
            SuatChieu newSuatChieu = insertSuatChieu();
            if (newSuatChieu != null) {
                loadTimeLine();

                for (PanelSuatChieuItem panelSuatChieuItem : suatChieuItemList) {
                    SuatChieu curSuatChieu = panelSuatChieuItem.getSuatChieu();
                    if (newSuatChieu.getPhim().getId().equals(curSuatChieu.getPhim().getId())
                            && newSuatChieu.getGioBatDau().equals(curSuatChieu.getGioBatDau())
                            && newSuatChieu.getGioKetThuc().equals(curSuatChieu.getGioKetThuc())) {
                        panelSuatChieuItem.setItemSelected();
                        updatingIndex = panelSuatChieuItem.getSuatChieuIndex();
                        break;
                    }
                }

                setUpdatingState();
            }
        }
    }//GEN-LAST:event_btnSaveOrUpdateActionPerformed

    private void cboPhongChieuFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongChieuFilterItemStateChanged
        if (isAllComboboxLoaded) {
            loadTimeLine();
            cboPhongChieu.getModel().setSelectedItem(cboPhongChieuFilter.getModel().getSelectedItem());

            updatingIndex = 0;
            suatChieuItemClickAction(suatChieuItemList.get(0));
        }
    }//GEN-LAST:event_cboPhongChieuFilterItemStateChanged

    private void dcsNgayChieuFilterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsNgayChieuFilterPropertyChange
        if ((dcsNgayChieu.getDate() != null && dcsNgayChieuFilter.getDate() != null)
                && !dcsNgayChieuFilter.getDate().equals(dcsNgayChieu.getDate())) {
            loadTimeLine();
            dcsNgayChieu.setDate(dcsNgayChieuFilter.getDate());

            updatingIndex = 0;
            suatChieuItemClickAction(suatChieuItemList.get(0));
        }
    }//GEN-LAST:event_dcsNgayChieuFilterPropertyChange

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        if (isAllComboboxLoaded) {
            spnGioBatDauStateChanged(null);
        }
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void spnGioBatDauStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnGioBatDauStateChanged
        if (spnGioBatDau.getValue() != null && spnGioKetThuc.getValue() != null && isAllComboboxLoaded) {
            Phim phim = (Phim) cboPhim.getModel().getSelectedItem();
            Date newGioBatDau = (Date) spnGioBatDau.getValue();
            newGioBatDau = getMergeDateAndTime(newGioBatDau, dcsNgayChieu.getDate());

            spnGioBatDau.setValue(newGioBatDau);
            spnGioKetThuc.setValue(DateHelper.rollTime(newGioBatDau, Calendar.MINUTE, phim.getThoiLuong()));
        }
    }//GEN-LAST:event_spnGioBatDauStateChanged

    private void btnResetOrDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetOrDeleteActionPerformed
        boolean isAccepted = DialogHelper.confirm(this, "Bạn có chắc chắn muốn xoá?");
        if (isAccepted) {
            SuatChieu updatingSuatChieu = suatChieuItemList.get(updatingIndex).getSuatChieu();
            try {
                new SuatChieuDaoImpl().delete(updatingSuatChieu);
                DialogHelper.message(this, "Xoá dữ liệu thành công!", DialogHelper.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.message(this, "Xoá dữ liệu thất bại!", DialogHelper.ERROR_MESSAGE);
            }
            updatingIndex -= 1;
            loadTimeLine();
            if (updatingIndex != -1) {
                suatChieuItemList.get(updatingIndex).setItemSelected();
            }
        }
    }//GEN-LAST:event_btnResetOrDeleteActionPerformed

    private void btnAddNewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddNewMouseReleased
        setAddingState();
    }//GEN-LAST:event_btnAddNewMouseReleased

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
            java.util.logging.Logger.getLogger(DialogCapNhatSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCapNhatSuatChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCapNhatSuatChieu dialog = new DialogCapNhatSuatChieu(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnAddNew;
    private javax.swing.JButton btnResetOrDelete;
    private javax.swing.JButton btnSaveOrUpdate;
    private javax.swing.JComboBox<String> cboDinhDang;
    private javax.swing.JComboBox<String> cboPhim;
    private javax.swing.JComboBox<String> cboPhongChieu;
    private javax.swing.JComboBox<String> cboPhongChieuFilter;
    private com.toedter.calendar.JDateChooser dcsNgayChieu;
    private com.toedter.calendar.JDateChooser dcsNgayChieuFilter;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActionInfo;
    private javax.swing.JPanel pnlLichChieu;
    private javax.swing.JSpinner spnGioBatDau;
    private javax.swing.JSpinner spnGioKetThuc;
    // End of variables declaration//GEN-END:variables
}
