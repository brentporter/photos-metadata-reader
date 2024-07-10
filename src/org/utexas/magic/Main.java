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

    public static void main(String[] args){
        String fileDirectoryStr = "/Users/crimsonking/Pictures/txcap/";
        BufferedImage img = null;
        ImageInputStream stream = null;
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPG");
        ImageReader reader = readers.next();
        ArrayList<TxCapPhoto> aryPhotosIn = new ArrayList<>();
        try {
            DirectoryParser directoryParser = new DirectoryParser();
            Set<String> listOFiles = directoryParser.listFilesUsingFilesList(fileDirectoryStr);
            int counterFiles = 0;
            if(!listOFiles.isEmpty()){
                for(String fName : listOFiles){
                    counterFiles += 1;
                    System.out.println(fName);
                    if(fName.endsWith("JPG")||fName.endsWith("jpg")) {
                        File jpegFile = new File(fileDirectoryStr + fName);
                        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
                        int counter = 0;
                        for (Directory directory : metadata.getDirectories()) {
                            for (Tag tag : directory.getTags()) {
                                counter += 1;
                                System.out.println(tag);
                                // if(directory.getName().equalsIgnoreCase("File")) {
                                //    System.out.println(tag.getDescription());
                                //}
                            }
                        }
                        System.out.println(counter);
                        String fNameFile = fName.substring(0,fName.length()-4);
                        System.out.print(fNameFile + ", "+ fileDirectoryStr);
                        aryPhotosIn.add(PopWriteTxCap2(fileDirectoryStr,fNameFile));
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
            RollupPhotos(aryPhotosIn);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ImageProcessingException e) {
            System.out.println("Errored Out");
            throw new RuntimeException(e);
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

    public static TxCapPhoto PopWriteTxCap2(String IncomingDirectory, String IncomingFile){
        TxCapPhoto txCapPhoto = new TxCapPhoto();
        txCapPhoto.setName(IncomingFile + ".JPG");
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
}
