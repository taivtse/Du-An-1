package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.dao.SuatChieuDao;
import poly.app.core.daoimpl.PhimDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.Phim;
import poly.app.core.entities.SuatChieu;
import poly.app.core.helper.DateHelper;
import poly.app.core.utils.JsonFactoryUtil;

@Path("suat-chieu")
public class SuatChieuAPI {

    SuatChieuDao suatChieuDao = new SuatChieuDaoImpl();

    @GET
    @Path("date/{timestamp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("timestamp") Long timestamp) {
        Date date = new Date(timestamp);
        List<SuatChieu> suatChieus = suatChieuDao.getSuatChieuByNgay(date);
        String json;
        try {
            json = JsonFactoryUtil.toJson(suatChieus);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("7-days-by-phim/{phimId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get7Ngay(@PathParam("phimId") String phimId) {
        Date startDate = new Date();
        Date endDate = DateHelper.rollDays(new Date(), 7);
        Phim phim = new PhimDaoImpl().getById(phimId);
        List<SuatChieu> suatChieus = suatChieuDao.getSuatChieuTrongKhoangThoiGianByPhim(phim, startDate, endDate);
        String json;
        try {
            json = JsonFactoryUtil.toJson(suatChieus);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThongTin(@PathParam("id") String id) {
        SuatChieu suatChieu = suatChieuDao.getById(id);
        String json;
        try {
            json = JsonFactoryUtil.toJson(suatChieu);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
