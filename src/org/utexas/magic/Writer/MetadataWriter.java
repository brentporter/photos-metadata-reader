package org.utexas.magic.Writer;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileWriter;
import java.io.IOException;

/***********
 * @author crimsonking
 * JSON Simple https://mkyong.com/java/json-simple-example-read-and-write-json/#setup-jsonsimple
 * JavaDocs https://cliftonlabs.github.io/json-simple/target/apidocs/index.html
 * Reference
 * {"name":"crimsonking","messages":["msg A","msg B","msg C"],"age":??}
 *         JsonObject jsonObject = new JsonObject();
 *         jsonObject.put("name", "crimsonking");
 *         jsonObject.put("age", 54);
 *
 *         // JSON Array
 *         JsonArray list = new JsonArray();
 *         list.add("msg A");
 *         list.add("msg B");
 *         list.add("msg C");
 *
 *         jsonObject.put("messages", list);
 ************/

public class MetadataWriter {

    public boolean WriteGeoJSON(){
        //Feature Class at beginning of GeoJSON
        JsonObject jsonObject = new JsonObject();

        JsonObject jsonIOProps = new JsonObject();
        jsonIOProps.put("Name","S0912A0476_A_0007.JPG");
        jsonIOProps.put("SourceImage","https://web.corral.tacc.utexas.edu/CSR/Public/17harvey/TxCAP/20170912/S0912A0476_A/S0912A0476_A_0007.JPG");
        jsonIOProps.put("Thumbnail","https://web.corral.tacc.utexas.edu/CSR/Public/17harvey/TxCAP/20170912/S0912A0476_A/S0912A0476_A_0007.JPG");
        jsonIOProps.put("Collect_Date","2017-09-12");
        jsonIOProps.put("Collect_Time","13:45:27");
        jsonIOProps.put("GPS_Altitude","173 meters above sea level");
        jsonIOProps.put("GPS_Latitude","N 29 deg 53'11.340000000000003");
        jsonIOProps.put("GPS_Longitude","W 97 deg 51'30.426000000000073");
        jsonIOProps.put("GPS_Map_Datum","WGS 84");
        jsonIOProps.put("numSatellites","05 GPS Satellites");
        jsonIOProps.put("Camera_Model","NIKON D7100");
        jsonIOProps.put("ResolutionDPI","300");
        jsonIOProps.put("ExposureTime","1/1000");
        jsonIOProps.put("Flag","None");

        JsonArray listCoords = new JsonArray();
        listCoords.add(-97.85845166666667);
        listCoords.add(29.886483333333334);
        JsonObject jsonIOGeom = new JsonObject();
        jsonIOGeom.put("type","Point");
        jsonIOGeom.put("coordinates",listCoords);

        //Individual Features (Objects)
        JsonObject jsonFeature1 = new JsonObject();
        jsonFeature1.put("geometry",jsonIOGeom);
        jsonFeature1.put("properties",jsonIOProps);
        jsonFeature1.put("type","Feature");

        // JsonArray listProp = new JsonArray();

        // JSON Array
        JsonArray list = new JsonArray();
        list.add(jsonFeature1);
        // list.add("msg B");
        // list.add("msg C");

        jsonObject.put("features", list);
        jsonObject.put("type", "FeatureCollection");

        try (FileWriter fileWriter = new FileWriter("/Users/crimsonking/Pictures/txcap/tester3.json",true)) {

            Jsoner.serialize(jsonObject, fileWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
