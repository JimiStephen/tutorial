package com.jimi.javase.internation;

import java.text.*;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * same as InternationalizedMortgageCalculator;
 *
 * @author jimi
 * @version 1.0
 * @date 2020/8/14 15:56
 */
public class FomattingDemo {

    public static void main(String[] args) {

/*
        //Numbers and Currencies
        String[] localeStrs = new String[]{"fr_FR", "de_DE", "en_US"};
        for (String item : localeStrs) {
            String[] lArr = item.split("_");
            String language = lArr[0];
            String region = lArr[1];

            //Predefined Formats
            Locale locale = new Locale.Builder().setLanguage(language).setRegion(region).build();
            displayNumber(locale);
            displayCurrency(locale);
            displayPercent(locale);
        }
        */

        //Customizing Formats
        Locale[] locales = {
                new Locale("en", "US"),
                new Locale("de", "DE"),
                new Locale("fr", "FR")
        };

        /*
        customNumFomatte("###,###.###", 123456.789);
        customNumFomatte("###.##", 123456.789);
        customNumFomatte("000000.000", 123.78);
        customNumFomatte("$###,###.###", 12345.67);
        customNumFomatte("\u00a5###,###.###", 12345.67);

        Locale currentLocale = new Locale("en", "US");

        DecimalFormatSymbols unusualSymbols =
                new DecimalFormatSymbols(currentLocale);
        unusualSymbols.setDecimalSeparator('|');
        unusualSymbols.setGroupingSeparator('^');
        String strange = "#,##0.###";
        DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
        weirdFormatter.setGroupingSize(4);
        String bizarre = weirdFormatter.format(12345.678);
        System.out.println(bizarre);

        for (int i = 0; i < locales.length; i++) {
            customLocaleNumFomatte("###,###.###", 123456.789, locales[i]);
        }
*/

/*

        //Dates and Times
        for (int i = 0; i < locales.length; i++) {
            displayDate(locales[i]);
        }

        showDateStyles(new Locale("en","US"));
        showDateStyles(new Locale("fr","FR"));

        showTimeStyles(new Locale("en","US"));
        showTimeStyles(new Locale("de","DE"));

        showBothStyles(new Locale("en","US"));
        showBothStyles(new Locale("fr","FR"));

        for (int i = 0; i < locales.length; i++) {
            customDisplayDate(locales[i]);
            System.out.println();
        }

        String[] patterns = {
                "dd.MM.yy",
                "yyyy.MM.dd G 'at' hh:mm:ss z",
                "EEE, MMM d, ''yy",
                "h:mm a",
                "H:mm",
                "H:mm:ss:SSS",
                "K:mm a,z",
                "yyyy.MMMMM.dd GGG hh:mm aaa"
        };

        for (int k = 0; k < patterns.length; k++) {
            customDisplayPattern(patterns[k], new Locale("en","US"));
            System.out.println();
        }
        changeWeekDays();
*/


// Messages see IsolatingLocaleSpecificDataDemo;
        displayMessage(new Locale("en", "US"));
        System.out.println();
        displayMessage(new Locale("de", "DE"));

        displayChoiceMessages(new Locale("en", "US"));
        System.out.println();
        displayChoiceMessages(new Locale("fr", "FR"));
    }

    /***--------------------------------Numbers and Currencies-----------------------------------------**/
    static public void displayNumber(Locale currentLocale) {

        Integer quantity = new Integer(123456);
        Double amount = new Double(345987.246);
        NumberFormat numberFormatter;
        String quantityOut;
        String amountOut;

        numberFormatter = NumberFormat.getNumberInstance(currentLocale);
        quantityOut = numberFormatter.format(quantity);
        amountOut = numberFormatter.format(amount);
        System.out.println(quantityOut + "   " + currentLocale.toString());
        System.out.println(amountOut + "   " + currentLocale.toString());
    }

    static public void displayCurrency(Locale currentLocale) {

        Double currencyAmount = new Double(9876543.21);
        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter =
                NumberFormat.getCurrencyInstance(currentLocale);

        System.out.println(
                currentLocale.getDisplayName() + ", " +
                        currentCurrency.getDisplayName() + ": " +
                        currencyFormatter.format(currencyAmount));
    }

    static public void displayPercent(Locale currentLocale) {

        Double percent = new Double(0.75);
        NumberFormat percentFormatter;
        String percentOut;

        percentFormatter = NumberFormat.getPercentInstance(currentLocale);
        percentOut = percentFormatter.format(percent);
        System.out.println(percentOut + "   " + currentLocale.toString());
    }

    static public void customNumFomatte(String pattern, double value) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String output = decimalFormat.format(value);
        System.out.printf("%s %s %s \n", value, pattern, output);
    }

    static public void customLocaleNumFomatte(String pattern, double value, Locale loc) {
        NumberFormat nf = NumberFormat.getInstance(loc);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(pattern);

        String output = df.format(value);
        System.out.printf("%s %s %s \n", pattern, output, loc.toString());
    }

    /***--------------------------------Dates and Times-----------------------------------------**/
    static public void displayDate(Locale currentLocale) {

        Date today;
        String dateOut;
        DateFormat dateFormatter;

        dateFormatter =
                DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
        today = new Date();
        dateOut = dateFormatter.format(today);

        System.out.println(dateOut + "   " + currentLocale.toString());
    }

    static public void showBothStyles(Locale currentLocale) {

        Date today;
        String result;
        DateFormat formatter;

        int[] styles = {
                DateFormat.DEFAULT,
                DateFormat.SHORT,
                DateFormat.MEDIUM,
                DateFormat.LONG,
                DateFormat.FULL
        };

        System.out.println();
        System.out.println("Locale: " + currentLocale.toString());
        System.out.println();

        today = new Date();

        for (int k = 0; k < styles.length; k++) {
            formatter = DateFormat.getDateTimeInstance(
                    styles[k], styles[k], currentLocale);
            result = formatter.format(today);
            System.out.println(result);
        }
    }

    static public void showDateStyles(Locale currentLocale) {

        Date today = new Date();
        String result;
        DateFormat formatter;

        int[] styles = {
                DateFormat.DEFAULT,
                DateFormat.SHORT,
                DateFormat.MEDIUM,
                DateFormat.LONG,
                DateFormat.FULL
        };

        System.out.println();
        System.out.println("Locale: " + currentLocale.toString());
        System.out.println();

        for (int k = 0; k < styles.length; k++) {
            formatter =
                    DateFormat.getDateInstance(styles[k], currentLocale);
            result = formatter.format(today);
            System.out.println(result);
        }
    }

    static public void showTimeStyles(Locale currentLocale) {

        Date today = new Date();
        String result;
        DateFormat formatter;

        int[] styles = {
                DateFormat.DEFAULT,
                DateFormat.SHORT,
                DateFormat.MEDIUM,
                DateFormat.LONG,
                DateFormat.FULL
        };

        System.out.println();
        System.out.println("Locale: " + currentLocale.toString());
        System.out.println();

        for (int k = 0; k < styles.length; k++) {
            formatter =
                    DateFormat.getTimeInstance(styles[k], currentLocale);
            result = formatter.format(today);
            System.out.println(result);
        }
    }

    static public void customDisplayDate(Locale currentLocale) {

        Date today;
        String result;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("EEE d MMM yy", currentLocale);
        today = new Date();
        result = formatter.format(today);

        System.out.println("Locale: " + currentLocale.toString());
        System.out.println("Result: " + result);
    }

    static public void customDisplayPattern(String pattern, Locale currentLocale) {

        Date today;
        SimpleDateFormat formatter;
        String output;

        formatter = new SimpleDateFormat(pattern, currentLocale);
        today = new Date();
        output = formatter.format(today);

        System.out.println(pattern + "   " + output);
    }

    /**
     * <Setter Method	Example of a Symbol the Method Modifies
     * setAmPmStrings	PM
     * setEras	AD
     * setMonths	December
     * setShortMonths	Dec
     * setShortWeekdays	Tue
     * setWeekdays	Tuesday
     * setZoneStrings	PST  >
     *
     * @author <a href="jimi">xianyao.ye</a>
     * @date 2020/8/14 16:50
     */
    static public void changeWeekDays() {

        Date today;
        String result;
        SimpleDateFormat formatter;
        DateFormatSymbols symbols;
        String[] defaultDays;
        String[] modifiedDays;

        symbols = new DateFormatSymbols(new Locale("en", "US"));
        defaultDays = symbols.getShortWeekdays();

        for (int i = 0; i < defaultDays.length; i++) {
            System.out.print(defaultDays[i] + "  ");
        }
        System.out.println();

        String[] capitalDays = {
                "", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        symbols.setShortWeekdays(capitalDays);

        modifiedDays = symbols.getShortWeekdays();
        for (int i = 0; i < modifiedDays.length; i++) {
            System.out.print(modifiedDays[i] + "  ");
        }

        System.out.println();
        System.out.println();

        formatter = new SimpleDateFormat("E", symbols);
        today = new Date();
        result = formatter.format(today);
        System.out.println("Today's day of the week: " + result);
    }

    /***--------------------------------Messages-----------------------------------------**/

    static  void displayMessage(Locale currentLocale) {

        System.out.println("currentLocale = " + currentLocale.toString());
        System.out.println();

        ResourceBundle messages =
                ResourceBundle.getBundle("MessageBundle",currentLocale);

        Object[] messageArguments = {
                messages.getString("planet"),
                new Integer(7),
                new Date()
        };

        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);

        formatter.applyPattern(messages.getString("template"));
        String output = formatter.format(messageArguments);

        System.out.println(output);

    }

    static void displayChoiceMessages(Locale currentLocale) {

        System.out.println("currentLocale = " + currentLocale.toString());
        System.out.println();

        ResourceBundle bundle =
                ResourceBundle.getBundle("ChoiceBundle",currentLocale);

        MessageFormat messageForm = new MessageFormat("");
        messageForm.setLocale(currentLocale);

        double[] fileLimits = {0,1,2};

        String [] fileStrings = {
                bundle.getString("noFiles"),
                bundle.getString("oneFile"),
                bundle.getString("multipleFiles")
        };

        ChoiceFormat choiceForm = new ChoiceFormat(fileLimits, fileStrings);

        String pattern = bundle.getString("pattern");
        Format[] formats = {choiceForm, null, NumberFormat.getInstance()};

        messageForm.applyPattern(pattern);
        messageForm.setFormats(formats);

        Object[] messageArguments = {null, "XDisk", null};

        for (int numFiles = 0; numFiles < 4; numFiles++) {
            messageArguments[0] = new Integer(numFiles);
            messageArguments[2] = new Integer(numFiles);
            String result = messageForm.format(messageArguments);
            System.out.println(result);
        }
    }

}
