package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.util.Date;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import web.entities.LoaiPhim;
import web.entities.Phim;

@Path("/MyWebService")
@ApplicationPath("/resources")
public class RestService extends Application{
//    Path: http://localhost:8080/resources/MyWebService:sayHello
    @GET
    @Path(":sayHello")
    public String getHelloMsg(){
        return "Hello World";
    }
    
//    Path: http://localhost:8080/resources/MyWebService/echo?message=ahihi
    @GET
    @Path("/echo")
    public Response getEchoMsg(@QueryParam("message") String msg){
        return Response.ok("Your message was: " + msg).build();
    }
    
    @GET
    @Path("/object")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleObject getObject(){
        return new SimpleObject(1, "Vo Thanh Tai");
    }
    
    @GET
    @Path("/object2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObject2(){
        return Response.status(Response.Status.NOT_FOUND).entity(new SimpleObject(1, "Vo Thanh Tai")).build();
    }
    
    @GET
    @Path("/loaiphim")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLoaiPhim(){
        LoaiPhim loaiPhim = new LoaiPhim(0, "Phim hai");
        loaiPhim.getPhims().add(new Phim("ph1", null, "chu cao vang", 150, 0, new Date(), "Tieng viet", "Viet nam", "Khong biet", "Dang chieu"));
        loaiPhim.getPhims().add(new Phim("ph2", null, "chu cao xanh", 190, 18, new Date(), "Tieng anh", "My", "Biet roi", "Ngung chieu"));
        
//        for (Phim phim : loaiPhim.getPhims()) {
//            System.out.println(phim.getTen());
//        }
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        
        String jsonInString = "";
        try {
            jsonInString = jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(loaiPhim);
//            System.out.println(jsonInString);
        } catch (JsonProcessingException ex) {
            return ex.getMessage();
        }
        
        return jsonInString;
    }
}