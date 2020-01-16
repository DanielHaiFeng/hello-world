package com.xa.dt.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TestDay {

    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        int Year = s1.nextInt();
        Scanner s2 = new Scanner(System.in);
        int Mon = s2.nextInt();
        Scanner s3 = new Scanner(System.in);
        int Day = s3.nextInt();
        System.out.println(calcDayCount(Year, Mon, Day));
    }

    /**
     * 计算指定日期是当年的第几天，不存在该日期返回-1
     *
     * @param Year
     * @param Mon
     * @param Day
     * @return
     */
    private static int calcDayCount(int Year, int Mon, int Day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Year, 0, 1);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Year, Mon - 1, Day);

            if (Year != calendar1.get(Calendar.YEAR)) {
                throw new RuntimeException("日期格式不对");
            }
            if (Mon != calendar1.get(Calendar.MONTH) + 1) {
                throw new RuntimeException("日期格式不对");
            }
            if (Day != calendar1.get(Calendar.DAY_OF_MONTH)) {
                throw new RuntimeException("日期格式不对");
            }

            int days = 0;
            while (true) {
                days += 1;
                if (isEquals(calendar.getTime(), calendar1.getTime())) {
                    break;
                } else {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                }
            }
            return days;
        } catch (Exception e) {
            return -1;
        }
    }

    private static boolean isEquals(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date1).equals(sdf.format(date2));
    }
}
