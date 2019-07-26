package com.jimi.javase.io.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class PathOperationsTest {
    public static void main(String[] args) {
      /*  //create path
        Path p1 = Paths.get("/tmp/foo");
       // Path p2 = Paths.get(args[0]);
        Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));
        Path p4 = FileSystems.getDefault().getPath("/users/sally");

//        The following example creates /u/joe/logs/foo.log assuming your home directory is /u/joe, or C:\joe\logs\foo.log if you are on Windows.

        Path p5 = Paths.get(System.getProperty("user.home"),"logs", "foo.log");
*/

        //testPathNormalize();

//        testPathConvert();
//        testJoinPath();
        compareTwoPath();
    }

    private static void testPathNormalize() {
        Path pathA = Paths.get("/home/./joe/foo");
        Path pathB = Paths.get("/home/sally/../joe/foo");

        System.out.printf("pathA is path %s \n", pathA.normalize());
        System.out.printf("pathABis path %s \n", pathB.normalize());
    }

    private static void testPathConvert() {
        //first method
        Path path1 = Paths.get("/home/log");
        System.out.printf("path1 to uri %s  \n", path1.toUri());

        //second method
        Path path2 = Paths.get("/home");
        System.out.printf("paht2 to AbolutePath %s \n", path2.toAbsolutePath());

        //third method
        try {
            //the file must exits
            Path path3 = Paths.get(".");
            System.out.printf("path3 to realPaht %s \n", path3.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void testJoinPath() {
        //use to path route
        // Microsoft Windows
        Path p1 = Paths.get("C:\\home\\joe\\foo");
        // Result is C:\home\joe\foo\bar
        System.out.format("%s%n", p1.resolve("bar"));
    }

    private static void createPahtBetweenTwo1() {
//        For example, consider two relative paths defined as joe and sally:

        Path p1 = Paths.get("joe");
        Path p2 = Paths.get("sally");
        //        In the absence of any other information, it is assumed that joe and sally are siblings, meaning nodes that reside at the same level in the tree structure. To navigate from joe to sally, you would expect to first navigate one level up to the parent node and then down to sally:

        // Result is ../sally
        Path p1_to_p2 = p1.relativize(p2);
        // Result is ../joe
        Path p2_to_p1 = p2.relativize(p1);
    }

    private static void createPahtBetweenTwo2() {
        Path p1 = Paths.get("home");
        Path p3 = Paths.get("home/sally/bar");
// Result is sally/bar
        Path p1_to_p3 = p1.relativize(p3);
// Result is ../..
        Path p3_to_p1 = p3.relativize(p1);
    }

    private static void compareTwoPath(){
        Path p1 = Paths.get("/home/path1");
        Path p2 = Paths.get("/home/path2");

        Path phead = Paths.get("/home");
        Path pend = Paths.get("path1");

        System.out.printf("p1 compare to p2 %s \n",p1.compareTo(p2));
        System.out.printf("p1 is start with home %s \n",p1.startsWith(phead));
        System.out.printf("p1 is end with path1 %s \n",p1.endsWith(pend));
    }
//--------------------------------------------------------------------------------
    private static void checkFile(){
        Path cpath = Paths.get("c://java.exe");

        Files.exists(cpath);
        Files.notExists(cpath);
        Files.isDirectory(cpath);

        Files.isReadable(cpath);
        Files.isWritable(cpath);
        Files.isExecutable(cpath);

        Path cpath1 = Paths.get("/copy/java.exe");
        try {
            Files.isSameFile(cpath,cpath1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void deleteFileOrDirector(){

        try {
            Path dPath = Paths.get("/home/dpath");
            Files.delete(dPath);
//            Files.deleteIfExists(dPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
