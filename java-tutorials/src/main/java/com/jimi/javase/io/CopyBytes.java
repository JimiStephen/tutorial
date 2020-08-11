package com.jimi.javase.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/11 13:53
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(ClassLoader.getSystemResource("byte-fis.txt").getFile());
            out = new FileOutputStream("./byte-fos.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
