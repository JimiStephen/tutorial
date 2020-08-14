/**
 * The implementation of SPIs (Service Provider Interface) is based
 * on abstract classes and Java interfaces that are implemented by the service provider.
 * At runtime the Java class loading mechanism is used to dynamically locate and load classes that implement the SPI.
 *
 *
 * You can use the locale-sensitive services SPI to provide the following locale sensitive implementations:
 *
 * BreakIterator objects
 * Collator objects
 * Language code, Country code, and Variant name for the Locale class
 * Time Zone names
 * Currency symbols
 * DateFormat objects
 * DateFormatSymbol objects
 * NumberFormat objects
 * DecimalFormatSymbols objects
 *
 * @author jimi
 * @date 2020/8/14 14:37
 * @version 1.0
 */
package com.jimi.javase.internation;