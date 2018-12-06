package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.dao.DinhDangPhimDao;
import poly.app.core.daoimpl.DinhDangPhimDaoImpl;
import poly.app.core.entities.DinhDangPhim;
import poly.app.core.utils.JsonFactoryUtil;

@Path("dinh-dang-phim")
public class DinhDangPhimAPI {

    DinhDangPhimDao dangPhimDao = new DinhDangPhimDaoImpl();

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        DinhDangPhim entity = dangPhimDao.getById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + id).build();
        }

        String json;
        try {
            json = JsonFactoryUtil.toJson(entity);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    public Response getAll() {
        List<DinhDangPhim> list = dangPhimDao.getAll();
        String json;
        try {
            json = JsonFactoryUtil.toJson(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
