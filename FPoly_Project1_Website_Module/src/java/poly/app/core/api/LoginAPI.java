package poly.app.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import poly.app.core.api.helper.TicketBookingHelper;
import poly.app.core.daoimpl.KhachHangDaoImpl;
import poly.app.core.entities.KhachHang;
import poly.app.core.utils.JsonFactoryUtil;
import poly.app.core.utils.StringUtil;

@Path("login")
public class LoginAPI {

    @POST
    @Path("/username={id}&password={password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("id") String id, @PathParam("password") String password) {
        password = StringUtil.generateMD5(password);
        String json = null;
        try {
            String token = TicketBookingHelper.saveToken(id);;
            KhachHang khachHang = new KhachHangDaoImpl().getByIdAndPassword(id, password);
            
            if (khachHang != null) {
                Map<String, Object> result = new HashMap<>();
                result.put("token", token);
                result.put("khachHang", khachHang);

                json = JsonFactoryUtil.toJson(result);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError()
                    .entity(ex)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("authenticate/khachHangId={id}&token={token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@PathParam("id") String id, @PathParam("token") String token) {
        String json = null;
        try {
            if (TicketBookingHelper.isTokenAvailable(id, token)) {
                KhachHang khachHang = new KhachHangDaoImpl().getById(id);
                json = JsonFactoryUtil.toJson(khachHang);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(LoginAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError()
                    .entity(ex)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON)
                .entity(json)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
