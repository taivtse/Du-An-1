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
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.entities.SuatChieu;
import poly.app.core.utils.JsonFactoryUtil;

@Path("suat-chieu")
public class SuatChieuAPI {

    SuatChieuDao suatChieuDao = new SuatChieuDaoImpl();

    @GET
    @Path("{timestamp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("timestamp") Long timestamp) {
        Date date = new Date(timestamp);
        List<SuatChieu> suatChieus = suatChieuDao.getSuatChieuByNgay(date);
       System.out.println(date);
       System.out.println(date.getTime());
        String json;
        try {
            json = JsonFactoryUtil.toJson(suatChieus);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
