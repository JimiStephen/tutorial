package com.jimi.javase.internation;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/14 15:39
 */
public class CustomizingResourceBundleLoadDemo {

    public static void main(String[] args) {
        test(Locale.CHINA);
        test(new Locale("zh", "HK"));
        test(Locale.TAIWAN);
        test(Locale.CANADA);
    }

    private static void test(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("RBControl", locale,
                new ResourceBundle.Control() {
                    @Override
                    public List<Locale> getCandidateLocales(
                            String baseName,
                            Locale locale) {
                        if (baseName == null)
                            throw new NullPointerException();

                        if (locale.equals(new Locale("zh", "HK"))) {
                            return Arrays.asList(
                                    locale,
                                    Locale.TAIWAN,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        } else if (locale.equals(Locale.TAIWAN)) {
                            return Arrays.asList(
                                    locale,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        }

                        return super.getCandidateLocales(baseName, locale);
                    }
                });
        System.out.println("locale: " + locale);
        System.out.println("\tregion: " + rb.getString("region"));
        System.out.println("\tlanguage: " + rb.getString("language"));


    }
}
