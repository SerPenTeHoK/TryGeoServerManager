import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.decoder.RESTResource;
import it.geosolutions.geoserver.rest.encoder.datastore.GSDirectoryOfShapefilesDatastoreEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by SerP on 18.02.2016.
 */
public class tests {

    public static void main(String[] args) throws MalformedURLException {
        //String RESTURL  = "http://localhost:8180/geoserver";
        String RESTURL  = "http://192.168.1.80:8180/geoserver";
        String RESTUSER = "admin";
        String RESTPW   = "geoserver";
        String workSpaceName = "myWorkspace1";

        GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);

        if(reader.existGeoserver()) {
            System.out.println("Есть ");

            List<String> nsn = reader.getNamespaceNames();
            for(String tmpStr : nsn)
                System.out.println(tmpStr);
            RESTLayer layerTmp = reader.getLayer("ForOracleWS", "REGIONS2010");
            //String strToLoad = layerTmp.getResourceUrl();
            RESTResource oraResource = reader.getResource(layerTmp);
            System.out.println("Title : " + oraResource.getTitle());

            //RESTCoverage oraCover = reader.getCoverage("OT" );
            //System.out.println(oraCover.toString());
        }

        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);

        boolean created = publisher.createWorkspace("myWorkspace1");
        if(created)
            System.out.println("created\\n");
        else
            System.out.println("not created");

        boolean ok = publisher.publishDBLayer("myWorkspace1", "pg_kids", "easia_gaul_0_aggr", "EPSG:4326", "default_polygon");
        if(ok)
            System.out.println("ok");
        else
            System.out.println("not ok");

        boolean exists = reader.existsDatastore("myWorkspace", "myStore");
        if(exists)
            System.out.println("exists");
        else
            System.out.println("not exists");

        /*
        boolean exists = reader.existsDatastore(workSpaceName, storeName);
        if ( !exists ) {
            File shapeFile = new File( "path/to/zipFile.zip" );
            boolean published = publisher.publishShp(workspaceName,
                    storeName, "TheLayerName", shapeFile, "EPSG:4326", "");
        }
        GeoServerRESTStoreManager storeManager;
        reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        storeManager = new GeoServerRESTStoreManager( new URL(RESTURL), RESTUSER, RESTPW);
        RESTDataStore rds = reader.getDatastore(workSpaceName, storeName);
        GSDirectoryOfShapefilesDatastoreEncoder update = new GSDirectoryOfShapefilesDatastoreEncoder(rds);
        update.setDescription("New Description goes here");
        update.setEnabled(true);
        update.setCharset(Charset.forName("UTF-8"));
        update.setCreateSpatialIndex(true);
        storeManager.update(workSpaceName, update);
*/

    }
}
