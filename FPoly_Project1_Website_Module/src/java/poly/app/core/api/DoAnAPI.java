package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.dao.DoAnDao;
import poly.app.core.daoimpl.DoAnDaoImpl;
import poly.app.core.entities.DoAn;
import poly.app.core.utils.JsonFactoryUtil;

@Path("do-an")
public class DoAnAPI {

    DoAnDao doAnDao = new DoAnDaoImpl();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        DoAn entity = doAnDao.getById(id);
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<DoAn> list = doAnDao.getAll();
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
        DoAn doAn = null;
        try {
            doAn = JsonFactoryUtil.toObject(json, new TypeReference<DoAn>() {
            });
            doAnDao.insert(doAn);
        } catch (Exception ex) {
            Logger.getLogger(DoAnAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String json) {
        DoAn doAn = null;
        try {
            doAn = JsonFactoryUtil.toObject(json, new TypeReference<DoAn>() {
            });
            doAnDao.update(doAn);
        } catch (Exception ex) {
            Logger.getLogger(DoAnAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(String json) {
        DoAn doAn = null;
        try {
            doAn = JsonFactoryUtil.toObject(json, new TypeReference<DoAn>() {
            });
            doAnDao.delete(doAn);
        } catch (Exception ex) {
            Logger.getLogger(DoAnAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

        return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") String id) {
        try {
            doAnDao.deleteById(id);
        } catch (Exception ex) {
            Logger.getLogger(DoAnAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }

        return Response.ok().build();
    }
}
