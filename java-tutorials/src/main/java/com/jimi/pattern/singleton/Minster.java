package com.jimi.pattern.singleton;

public class Minster {
    public static void main(String[] args) {

        //大臣每天早朝，看到都是同一个皇帝
        for (int day =0 ; day < 3; day++){
            King king = King.getInstance();
            king.order();
        }
    }
}
