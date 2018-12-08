package poly.app.core.helper;

import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import poly.app.core.daoimpl.NguoiDungDaoImpl;
import poly.app.core.entities.NguoiDung;

public class ShareHelper {

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static final Image APP_ICON;

    static {
        // Tải biểu tượng ứng dụng
        URL urlIconApp = ShareHelper.class.getResource("../../ui/icons/app-icon.png");
        APP_ICON = new ImageIcon(urlIconApp).getImage();
    }

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NguoiDung USER;

    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void logOut() {
        ShareHelper.USER = null;
    }

    /**
     * Kiểm tra xem đăng nhập hay chưa
     *
     * @return đăng nhập hay chưa
     */
    public static boolean isAuthenticated() {
        return ShareHelper.USER != null;
    }
}
