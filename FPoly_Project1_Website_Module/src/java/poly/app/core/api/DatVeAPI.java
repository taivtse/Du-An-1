package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.api.helper.TicketBookingHelper;
import poly.app.core.dao.VeBanDao;
import poly.app.core.dao.VeDatDao;
import poly.app.core.daoimpl.GheNgoiDaoImpl;
import poly.app.core.daoimpl.GiaVeDaoImpl;
import poly.app.core.daoimpl.KhachHangDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.daoimpl.VeBanDaoImpl;
import poly.app.core.daoimpl.VeDatDaoImpl;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.GiaVe;
import poly.app.core.entities.KhachHang;
import poly.app.core.entities.SuatChieu;
import poly.app.core.entities.VeBan;
import poly.app.core.entities.VeDat;
import poly.app.core.utils.JsonFactoryUtil;
import poly.app.core.utils.SMSUtil;

@Path("dat-ve")
public class DatVeAPI {

    VeDatDao veDatDao = new VeDatDaoImpl();
    VeBanDao veBanDao = new VeBanDaoImpl();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response datVe(@QueryParam("gheId") String gheId,
            @QueryParam("khachHangId") String khachHangId,
            @QueryParam("suatChieuId") String suatChieuId,
            @QueryParam("token") String token) {
        if (!TicketBookingHelper.isTokenAvailable(khachHangId, token)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        Map<String, Boolean> result = new HashMap<>();
        String json = "{}";

        String[] listGheId = gheId.split(",");
        KhachHang khachHang = new KhachHangDaoImpl().getById(khachHangId);
        SuatChieu suatChieu = new SuatChieuDaoImpl().getById(suatChieuId);

        VeDat veDat = new VeDat(khachHang);
        try {
            veDatDao.insert(veDat);
            veDat = veDatDao.getNewestVeDat();

            GiaVe giaVe = new GiaVeDaoImpl().getById(1);
            for (String ghe : listGheId) {
                GheNgoi gheNgoi = new GheNgoiDaoImpl().getById(Integer.parseInt(ghe));
                VeBan veBan = new VeBan("", gheNgoi, giaVe, suatChieu, new Date(), veDat, giaVe.getDonGia() + suatChieu.getDinhDangPhim().getPhuThu() + gheNgoi.getLoaiGhe().getPhuThu());
                veBanDao.insert(veBan);
            }

            String msg = "HE THONG CINES\nCam on ban da dat ve!\nMa ve dat cua ban la: " + veDat.getId();
            SMSUtil.sendSMS(msg, khachHang.getSoDienThoai());

            result.put("status", Boolean.TRUE);
            json = JsonFactoryUtil.toJson(result);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
            try {
                json = JsonFactoryUtil.toJson(result);
            } catch (JsonProcessingException ex) {
                e.printStackTrace();
                Logger.getLogger(DatVeAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
            return Response.ok(json)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        return Response.ok(json)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
