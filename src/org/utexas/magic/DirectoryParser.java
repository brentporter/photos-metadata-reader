package org.utexas.magic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

/*************
 * @author crimsonking
 * Taken from notes/discription
 * <a href="https://www.baeldung.com/java-list-directory-files">Java List Directory Files</a>
 ************/

public class DirectoryParser {

    public Set<String> listFilesUsingFilesList(String dir) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
            return null;
        }
    }
}
