package com.hz.school.util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static final String C_DATE_DIVISION = "-";
    /**
     * format : yyyy-MM-dd HH:mm:ss
     */
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * format : yyyy-MM-dd
     */
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";

    /**
     * format : yyyy/MM/dd
     */
    public static final String C_DATE_PATTONDEFAULT = "yyyy/MM/dd";
    public static final String C_DATE_PATTONDEFAULT2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * format : yyyyMM
     */
    public static final String C_DATE_PATTON_YEAR_MONTH = "yyyyMM";
    /**
     * format : yyyyMMdd
     */
    public static final String C_DATE_PATTON_YEAR_MONTH_DATE = "yyyyMMdd";
    /**
     * format : MM-dd
     */
    public static final String C_DATE_PATTON_MONTH_DAY = "MM-dd";
    /**
     * format : yyyy.MM
     */
    public static final String C_DATE_PATTON_YEARMONTH = "yyyy.MM";

    /**
     * format : yyyy-MM
     */
    public static final String C_DATE_PATTON_YEAR_MONTH2 = "yyyy-MM";

    /**
     * format : yyyyMM
     */
    public static final String C_DATE_PATTON_YEAR = "yyyy";

    /**
     * format : HH:mm:ss
     */
    public static final String C_TIME_PATTON_HHMMSS = "HH:mm:ss";

    /**
     * format : HH:mm
     */
    public static final String C_TIME_PATTON_HHMM = "HH:mm";

    /**
     * format : hh:mm
     */
    public static final String C_TIME_PATTON_hhMM = "hh:mm";

    /**
     * format (HK): dd/MM/yyyy
     */
    public static final String C_DATE_HK_PATTON_DEFAULT = "dd/MM/yyyy";

    private static final int C_ONE_SECOND = 1000;

    private static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;

    private static final int C_ONE_HOUR = 60 * C_ONE_MINUTE;

    public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

    public static String getDateFormat(Date d) {
        Calendar today = Calendar.getInstance();
        Calendar theDate = Calendar.getInstance();
        theDate.setTime(d);
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        if (theDate.before(today)) {
            return format(d, C_DATE_PATTON_DEFAULT);
        } else {
            return format(d, C_TIME_PATTON_HHMM);
        }
    }

    private static String format(Date aTs_Datetime, String as_Pattern) {
        if (aTs_Datetime == null || as_Pattern == null)
            return null;
        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(as_Pattern);
        return dateFromat.format(aTs_Datetime);
    }

    /**
     * 解析format指定的格式的日期
     */
    public static Date parseDate(String date, String format) {
        return parseDate(date, new SimpleDateFormat(format));
    }


    /**
     * 解析format指定的格式的日期
     */
    private static Date parseDate(String date, SimpleDateFormat format) {
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Date parse error: " + date
                    + " , expected format is " + format.toPattern(), ex);
        }
    }

    /**
     * 解析默认格式的日期
     */
    private static Date parseDate(String date) {
        if(StringUtil.isEmpty(date)){
            return null;
        }
        return parseDate(date, new SimpleDateFormat(C_DATE_PATTON_DEFAULT));
    }


    private static String formatDate(Long date) {
        if (date == null) {
            return "";
        }
        return formatDate(new Date(date), C_DATE_PATTON_DEFAULT);
    }

    public static String formatDate(String date) {
        if (StringUtil.isNumber(date)) {
            return formatDate(new Date(Long.parseLong(date)), C_DATE_PATTON_DEFAULT);
        }
        return "";
    }

    public static String formatDateTime(String date) {
        if (StringUtil.isNumber(date)) {
            return formatDate(new Date(Long.parseLong(date)), C_TIME_PATTON_DEFAULT);
        }
        return "";
    }

    public static String formatDateTime(Long date) {
        if (null == date) {
            return "";
        }
        return formatDate(new Date(date), C_TIME_PATTON_DEFAULT);
    }

    /**
     * 指定日期取出格式
     */
    private static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String fdate = "";
        try {
            fdate = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fdate;
    }

    /**
     * 将日期以 2007-01-30 的格式输出。
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormatter.format(date);
    }

    /**
     * 返回指定年的初始月
     */
    public static String parseYearBegin(Integer year) {
        return format(parseDate(year + "-01-01"), C_DATE_PATTON_YEAR_MONTH);
    }

    /**
     * 返回指定年的结束月
     */
    public static String parseYearEnd(Integer year) {
        return format(parseDate(year + "-12-31"), C_DATE_PATTON_YEAR_MONTH);
    }

    /**
     * 构造日期
     */
    private static Calendar cal = Calendar.getInstance();

    public static Date createDate(int year, int month, int day) {
        synchronized (cal) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            return cal.getTime();
        }
    }

    public static Date getCurrent() {
//    	 = Calendar.getInstance(TimeZone.getTimeZone(”GMT+08:00″));

//    	cal.setTimeZone(cal.getTimeZone().getTimeZone("GMT+8:00"));
        return cal.getTime();
    }

    public static boolean isCurrentHour(Integer month, Integer day, Integer hour) {
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal.get(Calendar.MONTH) + 1 == month
                && cal.get(Calendar.DAY_OF_MONTH) == day
                && cal.get(Calendar.HOUR_OF_DAY) == hour;
    }

    /**
     * 判断两个日期之间的月差别
     */
    public static int getMonthBetween2date(Date startDate, Date endDate) {
        String time1 = formatDate(startDate, C_DATE_PATTON_YEAR_MONTH);
        String time2 = formatDate(endDate, C_DATE_PATTON_YEAR_MONTH);
        ;
        DateFormat df = new SimpleDateFormat(C_DATE_PATTON_YEAR_MONTH);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        int i = 0;
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!c1.after(c2)) {
            i++;
            c1.add(Calendar.MONTH, 1);
        }
        return i;
    }

    /**
     * 返回两个日期之间的日差别列表
     */
    public static List<String> getDayListBetween2date(long startDate, long endDate) {
        List<String> dayBetween = new ArrayList<String>();
        String time1 = formatDate(startDate);
        String time2 = formatDate(endDate);
        ;
        DateFormat df = new SimpleDateFormat(C_DATE_PATTON_DEFAULT);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while (!c1.after(c2)) {
            i++;
            dayBetween.add(format(c1.getTime(), C_DATE_PATTON_DEFAULT));
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dayBetween;
    }


    /**
     * 返回两个日期之间的月差别列表
     */
    public static List<Integer> getMonthListBetween2date(String startDate, String endDate) {
        List<Integer> monthBetween = new ArrayList<Integer>();
        String time1 = formatDate(parseDate(startDate), C_DATE_PATTON_YEAR_MONTH);
        String time2 = formatDate(parseDate(endDate), C_DATE_PATTON_YEAR_MONTH);
        ;
        DateFormat df = new SimpleDateFormat(C_DATE_PATTON_YEAR_MONTH);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while (!c1.after(c2)) {
            i++;
            monthBetween.add(Integer.valueOf(format(c1.getTime(), C_DATE_PATTON_YEAR_MONTH)));
            c1.add(Calendar.MONTH, 1);
        }
        return monthBetween;
    }

    /**
     * 返回两个指定长度的月差别列表
     */
    public static List<Integer> getMonthListAddStep(String startDate, int addM) {
        List<Integer> monthBetween = new ArrayList<Integer>();
        String time1 = formatDate(parseDate(startDate), C_DATE_PATTON_YEAR_MONTH);
        DateFormat df = new SimpleDateFormat(C_DATE_PATTON_YEAR_MONTH);
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < addM; i++) {
            monthBetween.add(Integer.valueOf(format(c1.getTime(), C_DATE_PATTON_YEAR_MONTH)));
            c1.add(Calendar.MONTH, 1);
        }
        return monthBetween;
    }

    public static List<Integer> generateMonths2(int beginYm, int endYm) {
        List<Integer> mList = new ArrayList<Integer>();
        mList.add(beginYm);
        while (mList.get(mList.size() - 1) < endYm) {
            int nextYm = getNextYm(mList.get(mList.size() - 1));
            mList.add(nextYm);
        }
        return mList;
    }

    public static List<String> generateMonths(int beginYm, int endYm) {
        List<Integer> mList = new ArrayList<Integer>();
        List<String> months = new ArrayList<String>();
        mList.add(beginYm);
        while (mList.get(mList.size() - 1) < endYm) {
            int nextYm = getNextYm(mList.get(mList.size() - 1));
            mList.add(nextYm);
        }
        for (Integer i : mList) {
            months.add(String.valueOf(i));
        }
        return months;
    }

    private static int getNextYm(int ym) {
        int nextYm = 0;
        String ms = String.valueOf(ym).substring(4, 6);
        String ys = String.valueOf(ym).substring(0, 4);
        int mAdd = Integer.parseInt(ms) + 1;
        if (mAdd > 12) {
            int y = Integer.parseInt(ys) + 1;
            nextYm = Integer.parseInt(y + "01");
        } else {
            if (mAdd > 9) {
                nextYm = Integer.parseInt(ys + mAdd);
            } else {
                nextYm = Integer.parseInt(ys + "0" + mAdd);
            }
        }
        return nextYm;
    }

    /**
     * 取格式化后的当前年月
     * format:yyyymm
     *
     * @return
     */
    public static int getNowYearMonth() {
        return Integer.parseInt(DateUtil.format(new Date(), DateUtil.C_DATE_PATTON_YEAR_MONTH));
    }

    /**
     * 将当前时间格式化为int类型返回
     * format:yyyymm
     *
     * @param nowDate 需转换的时间
     * @return
     */
    public static int formatYearMonth(Date nowDate) {
        return Integer.parseInt(DateUtil.format(nowDate, DateUtil.C_DATE_PATTON_YEAR_MONTH));
    }

    /**
     * 取当前年月的前一月
     * format : 201004 -> 201003
     *
     * @param currentYearMonth
     * @return
     */
    public static Date getPreviousMonthTime(int currentYearMonth) {
        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt((currentYearMonth + "").substring(0, 4));
        int month = Integer.parseInt((currentYearMonth + "").substring(5, 6));
        c.set(year, month - 1, 0);
        return c.getTime();
    }

    /**
     * 得到nowDate时间后的第num天的时间
     *
     * @param nowDate 当前传进来的时间
     * @param num     之后的天数
     * @return
     */
    public static Date getAfterTime(Date nowDate, int num) {
        if (null == nowDate) {
            return null;
        }
        if (num == 0) {
            return nowDate;
        }
        cal.setTime(nowDate);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + num);
        return cal.getTime();
    }

    /**
     * 得到nowDate时间前的第num月的时间
     *
     * @param nowDate 当前传进来的时间
     * @param num     之前的月数
     * @return
     */
    public static Date getBeforeMonthTime(Date nowDate, int num) {
        if (null != nowDate) {
            cal.setTime(nowDate);
        }
        if (num == 0) {
            return nowDate;
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - num, cal.get(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 得到nowDate时间前的第num天的时间
     *
     * @param nowDate 当前传进来的时间
     * @param num     之前的天数
     * @return
     */
    public static Date getBeforeDay(Date nowDate, int num) {
        if (null != nowDate) {
            cal.setTime(nowDate);
        }
        if (num == 0) {
            return nowDate;
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), (cal.get(Calendar.DAY_OF_MONTH) - num));
        return cal.getTime();
    }

    public static boolean checkDateFormat(String dateStr) {
        if (dateStr.length() != 6) return false;
        try {
            Integer.parseInt(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 返回两个时间 相差的天
     */
    public static int betweenOfDay(long time1, long time2) {
        return (int) ((time2 - time1) / (1000 * 3600 * 24));
    }

    /**
     * 取集合中最大的时间
     *
     * @param dates
     * @return
     */
    public static Date getMaxDate(List<Long> dates) {
        Long maxDate = Collections.max(dates);
        return new Date(maxDate);
    }

    /**
     * 返回目录格式
     *
     * @return windows: /Y/m/d
     */
    static String getTimeSavePath() {
        Calendar calendar = Calendar.getInstance();
        return File.separator +
                calendar.get(Calendar.YEAR) +
                File.separator +
                (calendar.get(Calendar.MONTH) + 1) +
                File.separator +
                calendar.get(Calendar.DATE);
    }

    public static Long getStandardDays(Date date) {
        if (date == null) {
            return 0L;
        }
        Date d1900 = new Date(0, 0, 1);
        return (date.getTime() - d1900.getTime()) / 1000 / 3600 / 24 + 2;
    }

    /**
     * 日期转换为XMLGregorianCalendar
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }
    /**
     * long类型的转为XMLGregorianCalendar
     */
    public static XMLGregorianCalendar longToXMLGregorianCalendar(Long date) {
        if(null==date){
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }

    /**
     * XMLGregorianCalendar 转换为Date
     *
     * @param cal
     * @return
     */
    public static Date convertToDate(XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }

    //?年?月的最后一天
    private static Date getMonthLastDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(c.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        int last = c.getActualMaximum(c.DAY_OF_MONTH);
        c.set(c.DAY_OF_MONTH, last);
        c.set(c.HOUR_OF_DAY, 23);
        c.set(c.MINUTE, 59);
        c.set(c.SECOND, 59);
        c.set(c.MILLISECOND, 999);
        return c.getTime();
    }

    //?年?月的第一天
    private static Date getMonthFirstDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(c.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(c.DAY_OF_MONTH, 1);
        c.set(c.HOUR_OF_DAY, 0);
        c.set(c.MINUTE, 0);
        c.set(c.SECOND, 0);
        c.set(c.MILLISECOND, 0);
        return c.getTime();
    }

    ////当前年?月的第一天
    public static Date getMonthFirstDate(int month) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(c.YEAR);
        return getMonthFirstDate(currentYear, month);
    }

    ////当前年?月的最后一天
    public static Date getMonthLastDate(int month) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(c.YEAR);
        return getMonthLastDate(currentYear, month);
    }

    /**
     * 获取日期属于当年第几周
     * 星期一属于一周的第一天
     */
    public static int weekOfYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static void main(String[] args) {
        System.out.println(betweenOfDay(System.currentTimeMillis(), 1414054578859L));
    }


}
