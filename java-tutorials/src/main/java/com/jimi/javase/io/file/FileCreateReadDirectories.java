package com.jimi.javase.io.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileCreateReadDirectories {
    public static void main(String[] args) {
        listFileSystemRootDirectories();
        createDirectory();
        listDirectoryContent();
        listDirectoryGlob();
        DirectoryStream.Filter<Path> filter = createDirectoryFilter();
        onceDirectoryFilter(filter);


    }

    private static void onceDirectoryFilter(DirectoryStream.Filter<Path> filter) {
        //        Once the filter has been created, it can be invoked by using the newDirectoryStream(Path, DirectoryStream.Filter<? super Path>) method. The following code snippet uses the isDirectory filter to print only the directory's subdirectories to standard output:

        Path dir = Paths.get("directory.file");
        try (DirectoryStream<Path>
                     stream = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    private static DirectoryStream.Filter<Path> createDirectoryFilter() {
        //        Writing Your Own Directory Filter
//        Perhaps you want to filter the contents of a directory based on some condition other than pattern matching. You can create your own filter by implementing the DirectoryStream.Filter<T> interface. This interface consists of one method, accept, which determines whether a file fulfills the search requirement.
//        For example, the following code snippet implements a filter that retrieves only directories:

        Path path = Paths.get("path.file");
        return new DirectoryStream.Filter<Path>() {
            public boolean accept(Path file) throws IOException {
                return (Files.isDirectory(path));
            }
        };
    }

    private static void listDirectoryGlob() {
        //        Filtering a Directory Listing By Using Globbing
//        If you want to fetch only files and subdirectories where each name matches a particular pattern, you can do so by using the newDirectoryStream(Path, String) method, which provides a built-in glob filter. If you are not familiar with glob syntax, see What Is a Glob?
//
//        For example, the following code snippet lists files relating to Java: .class, .java, and .jar files.:

        Path dir = Paths.get("directory.file");
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(dir, "*.{java,class,jar}")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }

    private static void listDirectoryContent() {
        //List a directory's content
        Path dir = Paths.get("temp.file");

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory() {
        //create a directory
        Path dir = Paths.get("dir.file");
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        try {
            Files.createDirectory(dir, attr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listFileSystemRootDirectories() {
        //list a file system's root directories
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name : dirs) {
            System.out.println(name);
        }
    }
}
