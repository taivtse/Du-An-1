package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.dao.GheNgoiDao;
import poly.app.core.daoimpl.GheNgoiDaoImpl;
import poly.app.core.daoimpl.PhongChieuDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;
import poly.app.core.utils.JsonFactoryUtil;

@Path("ghe-ngoi")
public class GheNgoiAPI {

    GheNgoiDao gheNgoiDao = new GheNgoiDaoImpl();

    @GET
    @Path("phong-chieu/{phongChieuId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGheNgoiByPhongChieu(@PathParam("phongChieuId") Integer phongChieuId) {
        PhongChieu phongChieu = new PhongChieuDaoImpl().getById(phongChieuId);
        List<GheNgoi> list = gheNgoiDao.getGheNgoiByPhongChieu(phongChieu);
        String json;
        try {
            json = JsonFactoryUtil.toJson(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GheNgoiAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError()
                    .entity(ex)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
    
    @GET
        @Path("suat-chieu/{suatChieuId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGheNgoiDaDatBySuatChieu(@PathParam("suatChieuId") String suatChieuId) {
        SuatChieu suatChieu = new SuatChieuDaoImpl().getById(suatChieuId);
        List<GheNgoi> list = gheNgoiDao.getGheNgoiDaDatBySuatChieu(suatChieu);
        String json;
        try {
            json = JsonFactoryUtil.toJson(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GheNgoiAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError()
                    .entity(ex)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
