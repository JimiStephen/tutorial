package com.jimi.javase.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLink {
    public static void main(String[] args) {
        Path newLink = Paths.get("link.path");
        Path target = Paths.get("taget.path");
        //Creating a Symbolic Link
        try {
            Files.createSymbolicLink(newLink, target);
        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not support symbolic links.
            System.err.println(x);
        }

//        Creating a Hard Link
//        You can create a hard (or regular) link to an existing file by using the createLink(Path, Path) method. The second Path argument locates the existing file, and it must exist or a NoSuchFileException is thrown. The following code snippet shows how to create a link:

        Path existingFile = Paths.get("exits.file");
        try {
            Files.createLink(newLink, existingFile);
        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not
            // support adding an existing
            // file to a directory.
            System.err.println(x);
        }


        //detecting a symbolic link;
        boolean isSymbolicLink =
                Files.isSymbolicLink(newLink);

//        Finding the Target of a Link
//        You can obtain the target of a symbolic link by using the readSymbolicLink(Path) method, as follows:

        Path link = Paths.get("link.path");
        try {
            System.out.format("Target of link" +
                            " '%s' is '%s'%n", link,
                    Files.readSymbolicLink(link));
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
