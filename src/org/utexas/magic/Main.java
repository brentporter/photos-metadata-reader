package org.utexas.magic;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.utexas.magic.Model.TxCapPhoto;
import org.utexas.magic.Parser.DirectoryParser;
import org.utexas.magic.Writer.MetadataWriter;
import org.utexas.magic.Writer.WrapPhotos;
import org.utexas.magic.properties.ReadInProperties;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    static String basereaddir;
    static String baseurl;
    static String date;
    static String basesortie;
    static String thumbnails;
    public static void main(String[] args){


        ReadInProperties rip = new ReadInProperties();
        String[] propAry = rip.getProperties();
        if(propAry.length == 5) {
            basereaddir = propAry[0];
            baseurl = propAry[1];
            date = propAry[2];
            basesortie = propAry[3];
            thumbnails = propAry[4];

            String fileDirectoryStr = "/Users/crimsonking/Pictures/txcap/";
            BufferedImage img = null;
            ImageInputStream stream = null;
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPG");
            ImageReader reader = readers.next();
            ArrayList<TxCapPhoto> aryPhotosIn = new ArrayList<>();

            try {
                DirectoryParser directoryParser = new DirectoryParser();
                Set<String> listOFiles = directoryParser.listFilesUsingFilesList(basereaddir);
                int counterFiles = 0;
                if (!listOFiles.isEmpty()) {
                    for (String fName : listOFiles) {
                        counterFiles += 1;
                        System.out.println(fName);
                        if (fName.endsWith("JPG") || fName.endsWith("jpg")) {
                            ArrayList<String> aryFields = new ArrayList<>();
                            File jpegFile = new File(basereaddir + fName);
                            Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
                            int counter = 0;
                            for (Directory directory : metadata.getDirectories()) {
                                for (Tag tag : directory.getTags()) {
                                    counter += 1;
                                    System.out.println(tag);
                                    if(directory.getName().equalsIgnoreCase("Exif IFD0")){
                                        String scratchExif = tag.toString().substring(12);
                                        if(scratchExif.contains("Model -")){
                                            //System.out.println(scratchExif);
                                            scratchExif = scratchExif.substring(8);
                                            System.out.println(scratchExif);
                                            aryFields.add(scratchExif);
                                        }
                                        //X Resolution -
                                        if(scratchExif.contains("X Resolution -")){
                                            //System.out.println(scratchExif);
                                            scratchExif = scratchExif.substring(15,17);
                                            System.out.println(scratchExif);
                                            aryFields.add(scratchExif);
                                        }
                                    }
                                    //[Exif SubIFD]
                                    if(directory.getName().equalsIgnoreCase("Exif SubIFD")){
                                        String scratchExif = tag.toString().substring(13);
                                        if(scratchExif.contains("Exposure Time -")){
                                            System.out.println(scratchExif);
                                            scratchExif = scratchExif.substring(17);
                                            System.out.println(scratchExif);
                                            aryFields.add(scratchExif);
                                        }
                                    }
                                    if(directory.getName().equalsIgnoreCase("GPS")){
                                        String scratch = tag.toString().substring(10);
                                        if(scratch.contains("Latitude -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(11);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        if(scratch.contains("Longitude -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(12);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        if(scratch.contains("Altitude -")){
                                            System.out.println(scratch);
                                            scratch = scratch.substring(11);
                                            System.out.println(scratch+" above sea level");
                                            aryFields.add(scratch+" above sea level");
                                        }
                                        if(scratch.contains("Time-Stamp -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(13);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        if(scratch.contains("Satellites -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(12);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        if(scratch.contains("Map Datum -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(11);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        if(scratch.contains("Date Stamp -")){
                                            //System.out.println(scratch);
                                            scratch = scratch.substring(13);
                                            System.out.println(scratch);
                                            aryFields.add(scratch);
                                        }
                                        //ryFields.add(directory.getName());
                                        //System.out.println(directory.getName());
                                        //System.out.println(tag);
                                        //System.out.println(tag.toString().substring(10));
                                        //System.out.println(directory.getName().substring(10));
                                    }
                                    // if(directory.getName().equalsIgnoreCase("File")) {
                                    //    System.out.println(tag.getDescription());
                                    //}
                                }
                            }
                            System.out.println(counter);
                            String fNameFile = fName.substring(0, fName.length() - 4);
                            System.out.println(fNameFile + ", " + basereaddir);
                            String combinedSourceURL = baseurl +"TxCAP/"+ date +basesortie;
                            aryPhotosIn.add(PopWriteTxCap2(combinedSourceURL, fNameFile, aryFields));
                        }
                    }
                }
                System.out.println(counterFiles);
                //TxCapPhoto txCapPhoto = new TxCapPhoto();
                //PopWriteTxCap();
            /*
            MetadataWriter metadataWriter = new MetadataWriter();
            System.out.println(metadataWriter.WriteGeoJSON());
            System.out.println(metadataWriter.WriteGeoJSON());
            */
                //RollupPhotos(aryPhotosIn);
                WritePhotosWrapper(aryPhotosIn);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ImageProcessingException e) {
                System.out.println("Errored Out");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("You need to read how to set up the properties file correctly " +
                    "before running this tool. Please see the readme.md or contact bporter@csr.utexas.edu " +
                    "for support if problems persist.");
        }
    }

    public static void PopWriteTxCap(){
        TxCapPhoto txCapPhoto = new TxCapPhoto();
        txCapPhoto.setName("S0912A0476_A_0007.JPG");
        txCapPhoto.setSourceImage("https://web.corral.tacc.utexas.edu/CSR/Public/17harvey/TxCAP/20170912/S0912A0476_A/S0912A0476_A_0007.JPG");
        txCapPhoto.setThumbnail("https://web.corral.tacc.utexas.edu/CSR/Public/17harvey/TxCAP/20170912/S0912A0476_A/S0912A0476_A_0007.JPG");
        txCapPhoto.setCollect_Date("2017-09-12");
        txCapPhoto.setCollect_Time("13:45:27");
        txCapPhoto.setGPS_Altitude("173 meters above sea level");
        txCapPhoto.setGPS_Latitude("N 29 deg 53'11.340000000000003");
        txCapPhoto.setGPS_Longitude("W 97 deg 51'30.426000000000073");
        txCapPhoto.setGPS_Map_Datum("WGS 84");
        txCapPhoto.setNumSatellites("05 GPS Satellites");
        txCapPhoto.setCamera_Model("NIKON D7100");
        txCapPhoto.setResolutionDPI("300");
        txCapPhoto.setExposure_Time("1/1000");
        txCapPhoto.setFlag("None");
        List<Float> coordinatesIn = new ArrayList<>();
        coordinatesIn.add(29.886483333333334F);
        coordinatesIn.add(-97.85845166666667F);
        txCapPhoto.setCoordinates(coordinatesIn);
        txCapPhoto.setFeatureType("Feature");
        txCapPhoto.setPointType("Point");
        try (FileWriter fileWriter = new FileWriter("/Users/crimsonking/Pictures/txcap/tester4005.json",true)) {
            // convert object to json and write to file
            Jsoner.serialize(txCapPhoto, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TxCapPhoto PopWriteTxCap2(String IncomingDirectory, String IncomingFile, ArrayList<String> aryFieldsIn){
        System.out.println("Here");
        System.out.println(aryFieldsIn.size());
        /*
        Order of the arraylist
        Camera_Model,ResolutionDPI,ExposureTime
        GPS_Latitude,GPS_Longitude,GPS_Altitude
        Collect_Time, satellites, Datum, Collect_Date,

         */
        TxCapPhoto txCapPhoto = new TxCapPhoto();
        txCapPhoto.setName(IncomingFile + ".JPG");
        txCapPhoto.setSourceImage(IncomingDirectory+IncomingFile+".JPG");
        txCapPhoto.setThumbnail(IncomingDirectory+"thumbnails/"+IncomingFile+"_tn.JPG");
        txCapPhoto.setCollect_Date(aryFieldsIn.get(9));
        txCapPhoto.setCollect_Time(aryFieldsIn.get(6));
        txCapPhoto.setGPS_Altitude(aryFieldsIn.get(5));
        txCapPhoto.setGPS_Latitude(aryFieldsIn.get(3));
        txCapPhoto.setGPS_Longitude(aryFieldsIn.get(4));
        txCapPhoto.setGPS_Map_Datum(aryFieldsIn.get(8));
        txCapPhoto.setNumSatellites(aryFieldsIn.get(7));
        txCapPhoto.setCamera_Model(aryFieldsIn.get(0));
        txCapPhoto.setResolutionDPI(aryFieldsIn.get(1));
        txCapPhoto.setExposure_Time(aryFieldsIn.get(2));
        txCapPhoto.setFlag("None");
        List<Float> coordinatesIn = new ArrayList<>();
        coordinatesIn.add(29.886483333333334F);
        coordinatesIn.add(-97.85845166666667F);
        txCapPhoto.setCoordinates(coordinatesIn);
        txCapPhoto.setFeatureType("Feature");
        txCapPhoto.setPointType("Point");

        /*try (FileWriter fileWriter = new FileWriter(IncomingDirectory+IncomingFile+".json",true)) {
            // convert object to json and write to file
            Jsoner.serialize(txCapPhoto, fileWriter);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }*/
        return txCapPhoto;
    }

    public static void RollupPhotos(ArrayList<TxCapPhoto> aryPhotos){
        for(TxCapPhoto tcp:aryPhotos) {
            try (FileWriter fileWriter = new FileWriter("/Users/crimsonking/Pictures/txcap/Tester3000.json", true)) {
                // convert object to json and write to file
                Jsoner.serialize(tcp, fileWriter);
            } catch (IOException e) {
                //throw new RuntimeException(e);
                System.out.println(e);
            }
        }

    }
    public static void WritePhotosWrapper(ArrayList<TxCapPhoto> aryPhotos){
        String finalSortieName = baseurl +"TxCAP/"+ date +basesortie;
        WrapPhotos wrapPhotos = new WrapPhotos();
        boolean results = wrapPhotos.FinishPhotos(finalSortieName,aryPhotos);
        if(results){
            System.out.println("Worked");
        } else {
            System.out.println("Failed");
        }
    }
}
