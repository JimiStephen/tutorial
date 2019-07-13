package com.jimi.javase.io.file.operation;

import java.io.File;
import java.io.FileFilter;

public class ListFilePaths {

    public static void main(String[] args) {
        //listFilePaths(1, "D:\\workspaces\\tutorial\\java-tutorials");
        listFilePaths01(1, "D:\\workspaces\\tutorial\\java-tutorials");
    }

    public static void listFilePaths(int layers, String path) {

        File file = new File(path);

        if (!file.exists()) {
            //目录不存在
            System.out.println("目录不存在，请检查输入的目录。");
            return;
        }

        System.out.format("%s%s%n", generateRepeatStr(layers, "+"), file.getName());

        //if (file.isFile()) {
        //是文件则打印出文件名

        File[]  files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    return true;
                }
                return false;
            }
        });
        for (File item : files   ) {
            System.out.format("%s%s%n", generateRepeatStr(layers, "-"), item.getName());
        }
        //}

        //if (file.isDirectory()) {
        //是目录的处理


        File[] folds = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                return false;
            }
        });

        for (File item : folds) {
            layers++;
            listFilePaths(layers, item.getPath());
        }


        //}

    }

    public static void listFilePaths01(int layers, String path) {

        File file = new File(path);

        if (!file.exists()) {
            //目录不存在
            System.out.println("目录不存在，请检查输入的目录。");
            return;
        }

        System.out.format("%s%s%n", generateRepeatStr(layers, "+"), file.getName());

        file.listFiles();


    }

    private static String generateRepeatStr(int count, String repeatStr) {
        StringBuilder sb = new StringBuilder("|");

        if (null == repeatStr || "".equals(repeatStr)) {
            repeatStr = "*";
        }

        for (int counts = count; counts > 0; counts--) {
            sb.append(repeatStr);
        }

        return sb.toString();
    }
}
