package com.jimi.javase.internation;

import java.util.*;
/**
 * <运行程序>
 * % java I18NSample fr FR
 * % java I18NSample en US
 *
 * @author <a href="jimi">jimi</a>
 * @date 2020/8/12 10:40
 */
public class I18NSample {

    static public void main(String[] args) {

        String language;
        String country;

        if (args.length != 2) {
            language = new String("en");
            country = new String("US");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);

        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }
}