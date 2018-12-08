package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.daoimpl.DinhDangPhimDaoImpl;
import poly.app.core.daoimpl.GiaVeDaoImpl;
import poly.app.core.daoimpl.LoaiGheDaoImpl;
import poly.app.core.entities.DinhDangPhim;
import poly.app.core.entities.GiaVe;
import poly.app.core.entities.LoaiGhe;
import poly.app.core.utils.JsonFactoryUtil;

@Path("quy-tac-tinh-tien")
public class QuyTacTinhTienAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        List<GiaVe> giaVe = new GiaVeDaoImpl().getAll();
        List<DinhDangPhim> dinhDangPhim = new DinhDangPhimDaoImpl().getAll();
        List<LoaiGhe> loaiGhe = new LoaiGheDaoImpl().getAll();
        
        Map<String, Object> result = new HashMap<>();
        result.put("giaVe", giaVe);
        result.put("dinhDangPhim", dinhDangPhim);
        result.put("loaiGhe", loaiGhe);
        
        String json;
        try {
            json = JsonFactoryUtil.toJson(result);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
