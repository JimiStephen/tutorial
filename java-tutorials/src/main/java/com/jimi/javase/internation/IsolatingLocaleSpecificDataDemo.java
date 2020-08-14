package com.jimi.javase.internation;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 *
 *
 * @author jimi
 * @version 1.0
 * @date 2020/8/12 14:23
 */
public class IsolatingLocaleSpecificDataDemo {

    static void displayValues(Locale currentLocale) {

        ResourceBundle stats =
                ResourceBundle.getBundle("StatsBundle",currentLocale);

        Integer gdp = (Integer)stats.getObject("GDP");
        System.out.println("GDP = " + gdp.toString());
        Integer pop = (Integer)stats.getObject("Population");
        System.out.println("Population = " + pop.toString());
        Double lit = (Double)stats.getObject("Literacy");
        System.out.println("Literacy = " + lit.toString());

    } // displayValues

    public static void main(String[] args) {
        Locale[] supportedLocales = {
                new Locale("en","CA"),
                new Locale("ja","JP"),
                new Locale("fr","FR")
        };

        for (int i = 0; i < supportedLocales.length; i ++) {
            System.out.println("Locale = " + supportedLocales[i]);
            displayValues(supportedLocales[i]);
            System.out.println();
        }

    }
}
