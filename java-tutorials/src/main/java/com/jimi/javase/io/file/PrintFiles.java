package com.jimi.javase.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/*Kickstarting the Process
        Once you have implemented your FileVisitor, how do you initiate the file walk? There are two walkFileTree methods in the Files class.

        walkFileTree(Path, FileVisitor)
        walkFileTree(Path, Set<FileVisitOption>, int, FileVisitor)
        The first method requires only a starting point and an instance of your FileVisitor. You can invoke the PrintFiles file visitor as follows:

        Path startingDir = ...;
        PrintFiles pf = new PrintFiles();
        Files.walkFileTree(startingDir, pf);
        The second walkFileTree method enables you to additionally specify a limit on the number of levels visited and a set of FileVisitOption enums. If you want to ensure that this method walks the entire file tree, you can specify Integer.MAX_VALUE for the maximum depth argument.

        You can specify the FileVisitOption enum, FOLLOW_LINKS, which indicates that symbolic links should be followed.

        This code snippet shows how the four-argument method can be invoked:

        import static java.nio.file.FileVisitResult.*;

        Path startingDir = ...;

        EnumSet<FileVisitOption> opts = EnumSet.of(FOLLOW_LINKS);

        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, opts, Integer.MAX_VALUE, finder);*/
public class PrintFiles extends SimpleFileVisitor<Path> {

    // Print information about
    // each type of file.
//    Considerations When Creating a FileVisitor
//    A file tree is walked depth first, but you cannot make any assumptions about the iteration order that subdirectories are visited.
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        return CONTINUE;
    }

    // Print each directory visited.
//    Controlling the Flow
//    Perhaps you want to walk the file tree looking for a particular directory and, when found, you want the process to terminate. Perhaps you want to skip specific directories.
//
//    The FileVisitor methods return a FileVisitResult value. You can abort the file walking process or control whether a directory is visited by the values you return in the FileVisitor methods:
//
//    CONTINUE – Indicates that the file walking should continue. If the preVisitDirectory method returns CONTINUE, the directory is visited.
//            TERMINATE – Immediately aborts the file walking. No further file walking methods are invoked after this value is returned.
//            SKIP_SUBTREE – When preVisitDirectory returns this value, the specified directory and its subdirectories are skipped. This branch is "pruned out" of the tree.
//            SKIP_SIBLINGS – When preVisitDirectory returns this value, the specified directory is not visited, postVisitDirectory is not invoked, and no further unvisited siblings are visited. If returned from the postVisitDirectory method, no further siblings are visited. Essentially, nothing further happens in the specified directory.
    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                              IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException 
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}