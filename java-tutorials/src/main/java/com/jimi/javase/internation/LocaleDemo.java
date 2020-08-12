package com.jimi.javase.internation;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/12 10:59
 */
public class LocaleDemo {
    public static void main(String[] args) {
        String language = "ISO";
        String country = "";
        String variant = "";

        Locale localeOne = new Locale(language, country, variant);
        Locale localeTwo = new Locale(language, country);
        Locale localeThree = new Locale(language);

        Locale localeForLang = Locale.forLanguageTag("en-US");

        // Define Some LanguageRange Objects
        Locale.LanguageRange range1 = new Locale.LanguageRange("en-US", Locale.LanguageRange.MAX_WEIGHT);
        Locale.LanguageRange range2 = new Locale.LanguageRange("en-GB*", 0.5);
        Locale.LanguageRange range3 = new Locale.LanguageRange("fr-FR", Locale.LanguageRange.MIN_WEIGHT);

        // Create Locale

        Locale l = Locale.forLanguageTag("en-US");

        // Create a Language Priority List

        String ranges = "en-US;q=1.0,en-GB;q=0.5,fr-FR;q=0.0";
        List<Locale.LanguageRange> languageRangesByParse = Locale.LanguageRange.parse(ranges);


        // Create a collection of Locale objects to filter
        Collection<Locale> locales = new ArrayList<>();
        locales.add(Locale.forLanguageTag("en-GB"));
        locales.add(Locale.forLanguageTag("ja"));
        locales.add(Locale.forLanguageTag("zh-cmn-Hans-CN"));
        locales.add(Locale.forLanguageTag("en-US"));

        // Express the user's preferences with a Language Priority List
        String rangesStr = "en-US;q=1.0,en-GB;q=0.5,fr-FR;q=0.0";
        List<Locale.LanguageRange> languageRangesbyStr = Locale.LanguageRange.parse(ranges);

        // Now filter the Locale objects, returning any matches
        List<Locale> results = Locale.filter(languageRangesbyStr, locales);

        // Print out the matches
        for (Locale locale : results) {
            System.out.println(locale.toString());
        }
        // Find the BEST match, and return just one result
        Locale lookupResult = Locale.lookup(languageRangesbyStr, locales);
        System.out.println(lookupResult.toString());

        // Create a collection of String objects to match against
        Collection<String> tags = new ArrayList<>();
        tags.add("en-GB");
        tags.add("ja");
        tags.add("zh-cmn-Hans-CN");
        tags.add("en-US");
        // Express user's preferences with a Language Priority List
        String rangeTags = "en-US;q=1.0,en-GB;q=0.5,fr-FR;q=0.0";
        List<Locale.LanguageRange> languageRangesByTags = Locale.LanguageRange.parse(rangeTags);

        // Now search the locales for the best match
        List<String> resultsFilterTags = Locale.filterTags(languageRangesByTags, tags);

        // Print out the matches
        for (String s : resultsFilterTags) {
            System.out.println(s);
        }
// Find the BEST match, and return just one result
        String lookupTagResult = Locale.lookupTag(languageRangesByTags, tags);
        System.out.println(lookupTagResult);


//        showAvailableLocales();
//        createYourLocale();
    }

    private static void showAvailableLocales() {
        Locale list[] = DateFormat.getAvailableLocales();
        for (Locale aLocale : list) {
            System.out.println(aLocale.toString());
        }
    }

    private static void createYourLocale() {
        String outputString = new String();
        Locale[] thaiLocale = {
                new Locale("th"),
                new Locale("th", "TH"),
                new Locale("th", "TH", "TH")
        };

        for (Locale locale : thaiLocale) {
            NumberFormat nf = NumberFormat.getNumberInstance(locale);
            outputString = outputString + locale.toString() + ": ";
            outputString = outputString + nf.format(573.34) + "\n";
            System.out.println(outputString);
        }
    }
}
