package com.jimi.javase;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileReadWriteCreate {
    public static void main(String[] args) {

/*
        The OpenOptions Parameter
        Several of the methods in this section take an optional OpenOptions parameter. This parameter is optional and the API tells you what the default behavior is for the method when none is specified.

        The following StandardOpenOptions enums are supported:

        WRITE – Opens the file for write access.
        APPEND – Appends the new data to the end of the file. This option is used with the WRITE or CREATE options.
        TRUNCATE_EXISTING – Truncates the file to zero bytes. This option is used with the WRITE option.
        CREATE_NEW – Creates a new file and throws an exception if the file already exists.
        CREATE – Opens the file if it exists or creates a new file if it does not.
        DELETE_ON_CLOSE – Deletes the file when the stream is closed. This option is useful for temporary files.
        SPARSE – Hints that a newly created file will be sparse. This advanced option is honored on some file systems, such as NTFS, where large files with data "gaps" can be stored in a more efficient manner where those empty gaps do not consume disk space.
        SYNC – Keeps the file (both content and metadata) synchronized with the underlying storage device.
        DSYNC – Keeps the file content synchronized with the underlying storage device.
      */
        Path path = Paths.get("test.file");
        try {
            Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        try {
            Files.write(path,buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

//Buffered I/O Methods for Text Files
        Path file = Paths.get("buffer.file");
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }


        String s = "test content";
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(s, 0, s.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

//        Methods for Unbuffered Streams and Interoperable with java.io APIs
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }


        // Convert the string to a
        // byte array.
        String scontent = "Hello World! ";
        byte data[] = scontent.getBytes();
        Path p = Paths.get("./logfile.txt");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }


//        Methods for Channels and ByteBuffers
    }
}
