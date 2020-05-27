package com.jimi.javase.io.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/27 14:10
 */
public class FileTest {

    public static void main(String[] args) {
        Path path = Paths.get("java-tutorials/src/main/resources/data/bookmarks_2020_3_4.html");
        System.out.println(path.toAbsolutePath().toFile());
        System.out.println(path.getFileName());
        System.out.println(path.toFile().exists());
        System.out.println(path.toAbsolutePath().toFile().exists());


        File fileA = new File(ClassLoader.getSystemResource("./data/bookmarks_2020_3_4.html").getFile());
    }
}
