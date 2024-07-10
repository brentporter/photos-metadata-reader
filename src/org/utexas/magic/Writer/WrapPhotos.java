package org.utexas.magic.Writer;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.utexas.magic.Model.TxCapPhoto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WrapPhotos {

    public boolean FinishPhotos(String finalName, ArrayList<TxCapPhoto> txCapPhotos){
        JsonObject jsonObject = new JsonObject();
        JsonArray list = new JsonArray();
        list.addAll(txCapPhotos);
        jsonObject.put("features", list);
        jsonObject.put("type", "FeatureCollection");
        try (FileWriter fileWriter = new FileWriter("/Users/crimsonking/Pictures/txcap/Tester3000.json", true)) {
            // convert object to json and write to file
            Jsoner.serialize(jsonObject, fileWriter);
            return true;
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
            return false;
        }
        //return false;
    }
}
