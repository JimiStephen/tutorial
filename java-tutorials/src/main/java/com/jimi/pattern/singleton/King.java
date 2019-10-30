package com.jimi.pattern.singleton;

public class King {

    private static final King QingShiHuang = new King();

    private  King(){
        //约束只有一个皇帝
    }

    public static King getInstance(){
        return QingShiHuang;
    }

    public static void order(){
        System.out.println("皇帝，发布命令了。。。。");
    }
}
