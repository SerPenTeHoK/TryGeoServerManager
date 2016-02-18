import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;

import java.net.MalformedURLException;

/**
 * Created by SerP on 18.02.2016.
 */
public class tests {

    public static void main(String[] args) throws MalformedURLException {
        String RESTURL  = "http://localhost:8083/geoserver";
        String RESTUSER = "admin";
        String RESTPW   = "geoserver";

        GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);

        boolean created = publisher.createWorkspace("myWorkspace1");
        if(created)
            System.out.printf("created");

        boolean ok = publisher.publishDBLayer("myWorkspace1", "pg_kids", "easia_gaul_0_aggr", "EPSG:4326", "default_polygon");
        if(ok)
            System.out.printf("ok");

        boolean exists = reader.existsDatastore("myWorkspace", "myStore");
        if(exists)
            System.out.printf("exists");


    }
}
