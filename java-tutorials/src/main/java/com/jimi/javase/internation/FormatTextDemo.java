package com.jimi.javase.internation;

import java.text.CollationKey;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Locale;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/8/17 9:40
 */
public class FormatTextDemo {
    public static void main(String[] args) {
//        checkCharacterCollator();
//        testRuleBaseCollator();
        testCollationKey();
    }

    private static void testCollationKey() {
        Collator enUSCollator = Collator.getInstance(new Locale("en","US"));

        String [] words = {
                "peach",
                "apricot",
                "grape",
                "lemon"
        };

        CollationKey[] keys = new CollationKey[words.length];

        for (int k = 0; k < keys.length; k ++) {
            keys[k] = enUSCollator.getCollationKey(words[k]);
        }

        collationKeySortArray(keys);
        collationKeyDisplayWords(keys);
    }

    private static void testRuleBaseCollator() {
        String englishRules =
                ("< a,A < b,B < c,C < d,D < e,E < f,F " +
                        "< g,G < h,H < i,I < j,J < k,K < l,L " +
                        "< m,M < n,N < o,O < p,P < q,Q < r,R " +
                        "< s,S < t,T < u,U < v,V < w,W < x,X " +
                        "< y,Y < z,Z");

        String smallnTilde  = new String("\u00F1");
        String capitalNTilde = new String("\u00D1");

        String traditionalSpanishRules =
                ("< a,A < b,B < c,C " +
                        "< ch, cH, Ch, CH "  +
                        "< d,D < e,E < f,F " +
                        "< g,G < h,H < i,I < j,J < k,K < l,L " +
                        "< ll, lL, Ll, LL "  +
                        "< m,M < n,N " +
                        "< " + smallnTilde + "," + capitalNTilde + " " +
                        "< o,O < p,P < q,Q < r,R " +
                        "< s,S < t,T < u,U < v,V < w,W < x,X " +
                        "< y,Y < z,Z");

        String [] words = {
                "luz",
                "curioso",
                "llama",
                "chalina"
        };

        try {
            RuleBasedCollator enCollator =
                    new RuleBasedCollator(englishRules);
            RuleBasedCollator spCollator =
                    new RuleBasedCollator(traditionalSpanishRules);

            collatorSortStrings(enCollator, words);
            printStrings(words);

            System.out.println();

            collatorSortStrings(spCollator, words);
            printStrings(words);
        } catch (ParseException pe) {
            System.out.println("Parse exception for rules");
        }
    }

    private static void checkCharacterCollator() {
        collatorTestCompare();
        System.out.println();

        Collator fr_FRCollator = Collator.getInstance(new Locale("fr","FR"));
        Collator en_USCollator = Collator.getInstance(new Locale("en","US"));

        String eWithCircumflex = new String("\u00EA");
        String eWithAcute = new String("\u00E9");

        String peachfr = "p" + eWithAcute + "ch" + eWithAcute;
        String sinfr = "p" + eWithCircumflex + "che";

        String [] words = {
                peachfr,
                sinfr,
                "peach",
                "sin"
        };

        collatorSortStrings(fr_FRCollator, words);
        System.out.println("Locale: fr_FR");
        printStrings(words);

        System.out.println();

        collatorSortStrings(en_USCollator, words);
        System.out.println("Locale: en_US");
        printStrings(words);
    }

    public static void collatorSortStrings(Collator collator, String[] words) {
        String tmp;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // Compare elements of the array two at a time.
                if (collator.compare(words[i], words[j] ) > 0 ) {
                    // Swap words[i] and words[j]
                    tmp = words[i];
                    words[i] = words[j];
                    words[j] = tmp;
                }
            }
        }
    }

    public static void printStrings(String [] words) {
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }

    public static void collatorTestCompare() {

        Collator myCollator = Collator.getInstance(new Locale("en", "US"));

        System.out.println(myCollator.compare("abc", "def"));
        System.out.println(myCollator.compare("rtf", "rtf"));
        System.out.println(myCollator.compare("xyz", "abc"));
    }

    public static void collationKeySortArray(CollationKey[] keys) {

        CollationKey tmp;

        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // Compare the keys
                if( keys[i].compareTo( keys[j] ) > 0 ) {
                    // Swap keys[i] and keys[j]
                    tmp = keys[i];
                    keys[i] = keys[j];
                    keys[j] = tmp;
                }
            }
        }
    }

    static void collationKeyDisplayWords(CollationKey[] keys) {

        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i].getSourceString());
        }
    }
}
