package com.jimi.javase.date;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2019/11/27 10:35
 */
public class DateMondayTest {

    public static void main(String[] args) {

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(1980,0,1);

        Calendar calendarCurr = Calendar.getInstance();

        Map<Integer,Map<Integer,Integer>> weekDateCount = new HashMap<>();
        while (calendarStart.before(calendarCurr)){

            for (int i = 0; i < 12 ; i ++){
                calendarStart.set(Calendar.DAY_OF_MONTH,1);
                System.out.println(calendarStart.getTime());
                for (int j = 0; j < 7 ; j ++) {
                    int date = calendarStart.get(Calendar.DATE);
                    int weekOfDate = calendarStart.get(Calendar.DAY_OF_WEEK);
                    System.out.printf("第 %d 行 date %s,weekOfDate-[%s] \n", j , date,weekOfDate);
                    if (weekDateCount.containsKey(weekOfDate)) {
                        Map<Integer, Integer> dateCount = weekDateCount.get(weekOfDate);
                        if(dateCount.containsKey(date)){
                            dateCount.put(date,dateCount.get(date)+1);
                        }else{
                            dateCount.put(date,1);
                        }
                    } else {
                        Map<Integer, Integer> dateCount = new HashMap<>();
                        dateCount.put(date, 1);
                        weekDateCount.put(weekOfDate, dateCount);
                    }
                    calendarStart.add(Calendar.DAY_OF_MONTH,1);
                }
                calendarStart.add(Calendar.MONTH,1);
            }

        }

        System.out.println(weekDateCount);
//        //今天是几号
//        System.out.println(calendarCurr.get(Calendar.DATE));
//        //这个月第几周；
//        System.out.println(calendarCurr.get(Calendar.WEEK_OF_MONTH));
//        //获取当前日期的周三
//        System.out.println(calendarCurr.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//        //当前周的第一天
//        System.out.println(calendarCurr.getFirstDayOfWeek());



    }
}
