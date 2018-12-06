package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import poly.app.core.dao.DoAnChiTietDao;
import poly.app.core.daoimpl.DoAnChiTietDaoImpl;
import poly.app.core.entities.DoAnChiTiet;
import poly.app.core.utils.JsonFactoryUtil;

@Path("do-an-chi-tiet")
public class DoAnChiTietAPI {

    DoAnChiTietDao doAnChiTietDao = new DoAnChiTietDaoImpl();

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Integer id) {
        DoAnChiTiet entity = doAnChiTietDao.getById(id);
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
        List<DoAnChiTiet> list = doAnChiTietDao.getAll();
        String json;
        try {
            json = JsonFactoryUtil.toJson(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("order")
    public Response getAllAndOrderBySize() {
        List<DoAnChiTiet> list = doAnChiTietDao.getAllAndOrderBySize();
        String json;
        try {
            json = JsonFactoryUtil.toJson(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DinhDangPhimAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(String json) {
        DoAnChiTiet doAnChiTiet = null;
        try {
            doAnChiTiet = JsonFactoryUtil.toObject(json, new TypeReference<DoAnChiTiet>() {
            });
            doAnChiTietDao.insert(doAnChiTiet);
        } catch (IOException ex) {
            Logger.getLogger(DoAnChiTietAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            Logger.getLogger(DoAnChiTietAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

        return Response.ok().build();
    }
}
