package com.jimi.javase.time;


import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.util.Arrays;

/**
 * A clock providing access to the current instant, date and time using a time-zone.
 * Instances of this class are used to find the current instant,
 * which can be interpreted using the stored time-zone to find the current date and time.
 * As such,
 * a clock can be used instead of System.currentTimeMillis() and TimeZone.getDefault().
 */
public class ClockDemo {

    public static void main(String[] args) {
//        clockConstruct("");
        Clock clock = Clock.systemUTC();
        Instant instant = clock.instant();
        System.out.println("args = " + instant);
        ZoneId zoneId = clock.getZone();
        System.out.printf("zoneId="+zoneId+"\n");
        Long millis = clock.millis();
        clock = clock.withZone(ZoneId.systemDefault());
        System.out.printf("millis-%s-clock-%s \n",millis,clock);
    }
    /**
     * clock 创建方法
     *
     * @param option
     * @return
     * @method clockConstruct
     */
    public static Clock clockConstruct(String option) {

        ZoneId zone =  ZoneId.systemDefault();
        System.out.println(zone);
        Instant istant = Instant.now();
        Clock clock = null;
        clock = Clock.fixed(istant,zone);
        System.out.println(clock);
        clock = Clock.systemDefaultZone();
        System.out.println(clock);
        clock = Clock.systemUTC();
        System.out.println(clock);
        clock = Clock.offset(clock, Duration.ofHours(10));
        System.out.println(clock);
        Clock.tick(clock,Duration.ofMinutes(40));
        System.out.println(clock);
        Clock.tickMinutes(zone);
        System.out.println(clock);
        Clock.tickSeconds(zone);
        System.out.println(clock);
        
        return null;
    }
}
