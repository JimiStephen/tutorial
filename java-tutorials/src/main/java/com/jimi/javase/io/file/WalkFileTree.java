package com.jimi.javase.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WalkFileTree {
    public static void main(String[] args) {
        try {
            Path startingDir = Paths.get("F:\\Download") ;
            PrintFiles pf = new PrintFiles();
            Files.walkFileTree(startingDir, pf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
