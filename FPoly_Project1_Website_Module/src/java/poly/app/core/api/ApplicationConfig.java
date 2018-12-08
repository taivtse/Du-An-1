package poly.app.core.api;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(poly.app.core.api.DinhDangPhimAPI.class);
        resources.add(poly.app.core.api.GheNgoiAPI.class);
        resources.add(poly.app.core.api.PhimAPI.class);
        resources.add(poly.app.core.api.QuyTacTinhTienAPI.class);
        resources.add(poly.app.core.api.SuatChieuAPI.class);
    }
    
}