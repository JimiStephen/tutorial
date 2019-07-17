package com.jimi.javase.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTutorials01 {

    public static String pathC = "C:\\IDE\\Java\\jdk1.8.0_181";
    public static Path path = Paths.get(pathC);

    public static void main(String... args) {
//        testPaths();
        checkPaths();


    }

    public static void testPaths() {


        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());

        path = path.resolve("C:\\Windows");
        path = path.relativize(Paths.get("C:\\IDE\\Java"));
        //path = path.relativize(Paths.get("E:\\down"));
        for (Path name : path) {
            System.out.println(name);
        }
    }

    public static void checkPaths() {

        System.out.format("%s ,files.exists： %s%n", path, Files.exists(path));
        System.out.format("%s ,Files.notExists： %s%n", path, Files.notExists(path));
        System.out.format("%s ,Files.isExecutable： %s%n", path, Files.isExecutable(path));

        Path p1 = Paths.get("C://Windows");
        //p1 = Paths.get("C://usr");
        Path p2 = Paths.get("C://Temp" +
                "");

        try {
            if (Files.isSameFile(p1, p2)) {
                // Logic when the paths locate the same file
                System.out.println(p1.toString() + " and " + p2.toString() + " is same file.");
            } else {
                System.out.println(p1.toString() + " and " + p2.toString() + " is different file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listEveryRoot() {
        File[] roots = File.listRoots();

        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i]);
            System.out.println("Total space = " + roots[i].getTotalSpace());
            System.out.println("Free space = " + roots[i].getFreeSpace());
            System.out.println("Usable space = " + roots[i].getUsableSpace());
            System.out.println();
        }
    }
}
