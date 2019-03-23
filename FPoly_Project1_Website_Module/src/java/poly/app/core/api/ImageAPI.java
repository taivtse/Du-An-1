package poly.app.core.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("images")
public class ImageAPI {

    /*
     * The maximum allowed file size in megabytes.
     * Files larger than this size will not be uploadable or downloadable.
     */
    private static final int MAX_SIZE_IN_MB = 1;

    /*
     * The directory where the images will be stored.
     * Make sure this directory exists before you run the service.
     */
    private static final java.nio.file.Path BASE_DIR = Paths.get("/Users/vothanhtai/Documents/WorkSpace/Java/FPT-Polytechnic/semester4-project1/FPoly_Project1_Website_Module/Images");

    /*
     * Upload a JPEG or PNG file.
     */
    @POST
    @Consumes("image/jpeg")
    public Response uploadImage(InputStream in, @HeaderParam("Content-Length") long fileSize){

        // Make sure the file is not larger than the maximum allowed size.
        if (fileSize > 1024 * 1024 * MAX_SIZE_IN_MB) {
            return Response.status(Status.BAD_REQUEST).entity("Image is larger than " + MAX_SIZE_IN_MB + "MB").build();
        }

        // Generate a random file name based on the current time.
        // This probably isn't 100% safe but works fine for this example.
        String fileName = "" + System.currentTimeMillis() + ".jpg";

        try {
            // Copy the file to its location.
            Files.copy(in, BASE_DIR.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return Response.serverError().entity(ex).build();
        }

        // Return a 201 Created response with the appropriate Location header.
        return Response.status(Status.CREATED).location(URI.create(fileName)).build();
    }

    /*
     * Download a image file.
     */
    @GET
    @Path("{folder}/{name}")
    @Produces("image/*")
    public InputStream getImage(@PathParam("folder") String folder, @PathParam("name") String fileName) throws IOException {
        java.nio.file.Path dest = BASE_DIR.resolve(folder).resolve(fileName);

        try {
            return Files.newInputStream(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
