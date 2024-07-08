package org.utexas.magic;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.utexas.magic.Parser.DirectoryParser;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        String fileDirectoryStr = "/Users/crimsonking/Pictures/txcap/";
        BufferedImage img = null;
        ImageInputStream stream = null;
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPG");
        ImageReader reader = readers.next();
        try {
            DirectoryParser directoryParser = new DirectoryParser();
            Set<String> listOFiles = directoryParser.listFilesUsingFilesList(fileDirectoryStr);
            int counterFiles = 0;
            if(!listOFiles.isEmpty()){
                for(String fName : listOFiles){
                    counterFiles += 1;
                    System.out.println(fName);

                    File jpegFile = new File(fileDirectoryStr+fName);
                    Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
                    int counter = 0;
                    for (Directory directory : metadata.getDirectories()) {
                        for (Tag tag : directory.getTags()) {
                            counter += 1;
                            System.out.println(tag);
                            //if(directory.getName().equalsIgnoreCase("File")) {
                            //    System.out.println(tag.getDescription());
                            //}
                        }
                    }
                    System.out.println(counter);
                }
            }
            System.out.println(counterFiles);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ImageProcessingException e) {
            System.out.println("Errored Out");
            throw new RuntimeException(e);
        }
    }
}
