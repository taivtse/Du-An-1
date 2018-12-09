package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.dao.PhimDao;
import poly.app.core.daoimpl.PhimDaoImpl;
import poly.app.core.entities.Phim;
import poly.app.core.utils.JsonFactoryUtil;

@Path("phim")
public class PhimAPI {

    PhimDao phimDao = new PhimDaoImpl();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Phim phim = phimDao.getById(id);
        String json;
        try {
            json = JsonFactoryUtil.toJson(phim);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PhimAPI.class.getName()).log(Level.SEVERE, null, ex);
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
