package com.jimi.javase.awt.image;

import java.awt.image.BufferedImage;

public class BufferedImageTest {

    /**
     * Java 测试图片叠加方法
     */
    public static void overlyingImageTest() {

        String sourceFilePath = "C:/Users/JIMI/Pictures/Saved Pictures/OET.jpg";
        String waterFilePath = "C:/Users/JIMI/Pictures/Saved Pictures/OET-1.jpg";
        String saveFilePath = "C:/Users/JIMI/Pictures/Saved Pictures/overlyingImageNew.jpg";
        try {
            BufferedImage bufferImage1 = BufferedImageUtils.getBufferedImage(sourceFilePath);
            BufferedImage bufferImage2 = BufferedImageUtils.getBufferedImage(waterFilePath);

            // 构建叠加层
            BufferedImage buffImg = BufferedImageUtils.overlyingImage(bufferImage1, bufferImage2, 0, 0, 1.0f);
            // 输出水印图片
            BufferedImageUtils.generateSaveFile(buffImg, saveFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Java 测试图片合并方法
     */
    public static void imageMargeTest() {
        // 读取待合并的文件
        BufferedImage bi1 = null;
        BufferedImage bi2 = null;
        // 调用mergeImage方法获得合并后的图像
        BufferedImage destImg = null;
        System.out.println("下面是垂直合并的情况：");
        String saveFilePath = "C:/Users/JIMI/Pictures/Saved Pictures/OET.jpg";
        String divingPath = "C:/Users/JIMI/Pictures/Saved Pictures/OET-1.jpg";
        String margeImagePath = "C:/Users/JIMI/Pictures/Saved Pictures/margeNew.jpg";
        try {
            bi1 = BufferedImageUtils.getBufferedImage(saveFilePath);
            bi2 = BufferedImageUtils.getBufferedImage(divingPath);
            // 调用mergeImage方法获得合并后的图像
            BufferedImage  destLeftImg = BufferedImageUtils.mergeImage(bi1, bi2, false);
            BufferedImage destRightImg = BufferedImageUtils.mergeImage(bi1, bi2, false);
            destImg = BufferedImageUtils.mergeImage(destLeftImg, destRightImg, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存图像
        BufferedImageUtils.generateSaveFile(destImg, margeImagePath);
        System.out.println("垂直合并完毕!");
    }

    public static void main(String[] args) {
        // 测试图片的叠加
        //overlyingImageTest();
        // 测试图片的垂直合并
        imageMargeTest();
    }
}
