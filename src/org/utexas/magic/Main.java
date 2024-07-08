package org.utexas.magic;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args){
        BufferedImage img = null;
        ImageInputStream stream = null;
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPG");
        ImageReader reader = readers.next();
        try {
            File jpegFile = new File("/Users/crimsonking/Pictures/txcap/S0912A0737_B_5876.JPG");
            Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
            /*stream = ImageIO.createImageInputStream(new File("/Users/crimsonking/Pictures/S0912A0737_B_5876.JPG"));

            reader.setInput(stream, true);

            IIOMetadata metadata = reader.getImageMetadata(0);
            //img = ImageIO.read(new File("/Users/crimsonking/Pictures/S0912A0737_B_5876.JPG"));
            //System.out.println(img.getColorModel().getPixelSize());
            System.out.println(metadata.);*/
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ImageProcessingException e) {
            System.out.println("Errored Out");
            throw new RuntimeException(e);
        }
    }
}
