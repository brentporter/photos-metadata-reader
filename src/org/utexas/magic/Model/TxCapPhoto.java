package org.utexas.magic.Model;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class TxCapPhoto implements Jsonable {

    private String Name;
    private String SourceImage;
    private String Thumbnail;
    private String Collect_Date;
    private String Collect_Time;
    private String GPS_Altitude;
    private String GPS_Latitude;
    private String GPS_Longitude;
    private String GPS_Map_Datum;
    private String numSatellites;
    private String Camera_Model;
    private String ResolutionDPI;
    private String Exposure_Time;
    private String Flag;
    private List<Float> coordinates;
    private String FeatureType;
    private String PointType;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getSourceImage() {
        return SourceImage;
    }
    public void setSourceImage(String sourceImage) {
        SourceImage = sourceImage;
    }
    public String getThumbnail() {
        return Thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
    public String getCollect_Date() {
        return Collect_Date;
    }
    public void setCollect_Date(String collect_Date) {
        Collect_Date = collect_Date;
    }
    public String getCollect_Time() {
        return Collect_Time;
    }
    public void setCollect_Time(String collect_Time) {
        Collect_Time = collect_Time;
    }
    public String getGPS_Altitude() {
        return GPS_Altitude;
    }
    public void setGPS_Altitude(String GPS_Altitude) {
        this.GPS_Altitude = GPS_Altitude;
    }
    public String getGPS_Latitude() {
        return GPS_Latitude;
    }
    public void setGPS_Latitude(String GPS_Latitude) {
        this.GPS_Latitude = GPS_Latitude;
    }
    public String getGPS_Longitude() {
        return GPS_Longitude;
    }
    public void setGPS_Longitude(String GPS_Longitude) {
        this.GPS_Longitude = GPS_Longitude;
    }
    public String getGPS_Map_Datum() {
        return GPS_Map_Datum;
    }
    public void setGPS_Map_Datum(String GPS_Map_Datum) {
        this.GPS_Map_Datum = GPS_Map_Datum;
    }
    public String getNumSatellites() {
        return numSatellites;
    }
    public void setNumSatellites(String numSatellites) {
        this.numSatellites = numSatellites;
    }
    public String getCamera_Model() {
        return Camera_Model;
    }
    public void setCamera_Model(String camera_Model) {
        Camera_Model = camera_Model;
    }
    public String getResolutionDPI() {
        return ResolutionDPI;
    }
    public void setResolutionDPI(String resolutionDPI) {
        ResolutionDPI = resolutionDPI;
    }
    public String getExposure_Time() {
        return Exposure_Time;
    }
    public void setExposure_Time(String exposure_Time) {
        Exposure_Time = exposure_Time;
    }
    public String getFlag() {
        return Flag;
    }
    public void setFlag(String flag) {
        Flag = flag;
    }
    public List<Float> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }
    public String getFeatureType() {
        return FeatureType;
    }
    public void setFeatureType(String featureType) {
        FeatureType = featureType;
    }
    public String getPointType() {
        return PointType;
    }
    public void setPointType(String pointType) {
        PointType = pointType;
    }
    public TxCapPhoto() {}


    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException caught) {
            throw new RuntimeException(caught);
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        JsonObject json = new JsonObject();
        json.put("name", this.name);
        json.put("age", this.age);
        json.put("messages", new JsonArray(this.messages));
        json.toJson(writer);
    }





}
