package com.winit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_1 = "yyyy-MM-dd";

	public static final String FORMAT_2 = "HH:mm:ss";

	public DateUtils() {
	}

	/**
	 * 得到来源日期字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param s
	 *            格式化类型(例如:yyyy-MM-dd)
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getDateStr(Date date, String s) {
		SimpleDateFormat simpledateformat;
		return (simpledateformat = new SimpleDateFormat(s)).format(date);
	}

	/**
	 * 得到来源日期字符串
	 * 
	 * @param l
	 *            长整型日期
	 * @param s
	 *            格式化类型(例如:yyyy-MM-dd)
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getDateStr(long l, String s) {
		SimpleDateFormat simpledateformat;
		return (simpledateformat = new SimpleDateFormat(s)).format(new Date(l));
	}

	/**
	 * 得到当前日期的字符串
	 * 
	 * @param s
	 *            格式化类型(例如:yyyy-MM-dd)
	 * @return
	 */
	public static String getNow(String s) {
		return getDateStr(System.currentTimeMillis(), s);
	}

	/**
	 * 得到来源日期字符串的日期
	 * 
	 * @param s
	 *            来源日期字符串
	 * @param s1
	 *            格式化类型(例如:yyyy-MM-dd)
	 * @param l
	 *            出错返回默认类型
	 * @return
	 */
	public static Date getDate(String s, String s1, long l) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
		Date date;
		try {
			date = simpledateformat.parse(s);
		} catch (Exception _ex) {
			date = new Date(l);
		}
		return date;
	}

	/**
	 * 得到来源日期字符串的日期
	 * 
	 * @param s
	 *            来源日期字符串
	 * @param s1
	 *            格式化类型(例如:yyyy-MM-dd)
	 * @return
	 */
	public static Date getDate(String s, String s1) {
		return getDate(s, s1, 0L);
	}

	/**
	 * 得到来源日期字符串的时间
	 * 
	 * @param s
	 *            来源日期字符串
	 * @param s1
	 *            格式化类型
	 * @param l
	 *            出错返回默认类型
	 * @return
	 */
	public static long getTime(String s, String s1, long l) {
		return getDate(s, s1, l).getTime();
	}

	/**
	 * 得到来源日期字符串的时间
	 * 
	 * @param s
	 *            来源日期字符串
	 * @param s1
	 *            格式化类型
	 * @return
	 */
	public static long getTime(String s, String s1) {
		return getTime(s, s1, 0L);
	}

	/**
	 * 将来源字符串从一种格式转化为另一种格式
	 * 
	 * @param 来源日期字符串
	 * @param s1
	 *            格式化类型1
	 * @param s2
	 *            格式化类型2
	 * @return
	 */
	public static String convert(String s, String s1, String s2) {
		Date date = getDate(s, s1);
		if (null == date)
			return "";
		else
			return getDateStr(date, s2);
	}

	/**
	 * 将来源字符串从一种格式转化为另一种格式
	 * 
	 * @param 来源日期字符串
	 * @param s1
	 *            格式化类型1
	 * @param s2
	 *            格式化类型2
	 * @param c
	 *            替换格式化类型2的分割符
	 * @return
	 */
	public static String convert(String s, String s1, String s2, char c) {
		char ac[] = s.toCharArray();
		for (int i = 0; i < ac.length; i++)
			if (c == s.charAt(i))
				ac[i] = '-';

		Date date = getDate(new String(ac), s1.replace(c, '-'));
		char ac1[] = null != date ? getDateStr(date, s2.replace(c, '-')).toCharArray() : new char[0];
		for (int j = 0; j < ac1.length; j++)
			if (c == s.charAt(j))
				ac1[j] = s.charAt(j);

		return new String(ac1);
	}

	/**
	 * 格式化日期字符串为yyyyMMdd类型
	 * 
	 * @param 来源日期字符串
	 * @param s1
	 *            字符串格式
	 * @return
	 */
	public static String getSeason(String s, String s1) {
		String s2 = "";
		Date date = getDate(s, s1);
		GregorianCalendar gregoriancalendar = new GregorianCalendar();
		String s3 = "yyyy";
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s3);
		s2 = s2 + simpledateformat.format(date);
		gregoriancalendar.setTime(date);
		return s2 = s2 + getSeason(gregoriancalendar.get(2));
	}

	private static String getSeason(int i) {
		switch (i) {
		case 0: // '\0'
			return "01";

		case 1: // '\001'
			return "01";

		case 2: // '\002'
			return "01";

		case 3: // '\003'
			return "02";

		case 4: // '\004'
			return "02";

		case 5: // '\005'
			return "02";

		case 6: // '\006'
			return "03";

		case 7: // '\007'
			return "03";

		case 8: // '\b'
			return "03";

		case 9: // '\t'
			return "04";

		case 10: // '\n'
			return "04";

		case 11: // '\013'
			return "04";
		}
		return "";
	}

	/**
	 * 得到天数相差的周数
	 * 
	 * @param l
	 * @param l1
	 * @return
	 */
	@SuppressWarnings("unused")
	public static int getDayInterval(long l, long l1) {
		int i;
		return i = (int) ((l - l1) / 0x5265c00L);
	}

	/**
	 * 得到源天数几周后的日期
	 * 
	 * @param l
	 * @param l1
	 * @return
	 */
	public static long addDays(long l, int i) {
		return l + (long) i * 0x5265c00L;
	}

	/**
	 * 得到开始的日期
	 * 
	 * @param l
	 *            长整型日期
	 * @return
	 */
	@SuppressWarnings("unused")
	public static long startOfDay(long l) {
		String s;
		return getDate(s = getDateStr(l, "yyyyMMdd"), "yyyyMMdd").getTime();
	}

	/**
	 * 得到结束的日期
	 * 
	 * @param l
	 *            长整型时间
	 * @return
	 */
	public static long endOfDay(long l) {
		return (startOfDay(l) + 0x5265c00L) - 1L;
	}

	/**
	 * 得到当前时间的星期数
	 * 
	 * @param l
	 *            长整型时间
	 * @return
	 */
	public static String getDayOfWeekStr(long l) {
		GregorianCalendar gregoriancalendar;
		(gregoriancalendar = new GregorianCalendar()).setTimeInMillis(l);
		int i = gregoriancalendar.get(7);
		String s = "";
		switch (i) {
		case 1: // '\001'
			s = "\u5468\u65E5";
			break;

		case 2: // '\002'
			s = "\u5468\u4E00";
			break;

		case 3: // '\003'
			s = "\u5468\u4E8C";
			break;

		case 4: // '\004'
			s = "\u5468\u4E09";
			break;

		case 5: // '\005'
			s = "\u5468\u56DB";
			break;

		case 6: // '\006'
			s = "\u5468\u4E94";
			break;

		case 7: // '\007'
			s = "\u5468\u516D";
			break;
		}
		return s;
	}

	/**
	 * 判断当前时间是否属于某个时间段 checkCurTimeIsAPeriod("09:00","13:00");
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkCurTimeIsAPeriod(String startTime, String endTime) {
		Date nowTime = new Date();
		String time = nowTime.getHours() + ":" + nowTime.getMinutes();
		if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得14位的当前时间字符串
	 * 
	 * @return 14位的当前时间字符串
	 */
	public static String get14StrCurrentTime() {
		Date date = new Date();
		String s = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		s = s + simpledateformat.format(date);
		simpledateformat = new SimpleDateFormat("HHmmss");
		return s = s + simpledateformat.format(date);
	}

	/**
	 * 根据日历信息获得14为时间字符串
	 * 
	 * @param calendar
	 *            日历信息
	 * @return 14为时间字符串
	 */
	public static String get14StrTime(Calendar calendar) {
		Date date = calendar.getTime();
		String s = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		s = s + simpledateformat.format(date);
		simpledateformat = new SimpleDateFormat("HHmmss");
		return s = s + simpledateformat.format(date);
	}

	/**
	 * 根据分割符分解获得时间字符串
	 * 
	 * @param s
	 *            时间字符串
	 * @param s1
	 *            分割符
	 * @return 格式化后的时间字符串
	 */
	public static String getTimeStrBySplitor(String s, String s1) {
		if (s == null || s.length() < 8)
			return s;
		if (s.length() < 14)
			return s.substring(0, 4) + s1 + s.substring(4, 6) + s1 + s.substring(6, 8);
		else
			return s.substring(0, 4) + s1 + s.substring(4, 6) + s1 + s.substring(6, 8) + " " + s.substring(8, 10) + ":"
					+ s.substring(10, 12) + ":" + s.substring(12, 14);
	}

	/**
	 * 将带格式化的时间字符串转换为14位日期时间字符串
	 * 
	 * @param s
	 *            带格式化的时间字符串
	 * @return 14位日期时间字符串
	 */
	public static String get14Str(String s) {
		return s = StringUtil.replace(s = StringUtil.replace(s = StringUtil.replace(s, "-", ""), ":", ""), " ", "");
	}

	/**
	 * 得到当前日期格式为yyyy-MM-dd字符串
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(cal.getTime());
		return today;
	}

	/**
	 * 得到来源日期格式为yyyy-MM-dd字符串
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getDateFormString(String s) {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); // 格式化当前系统日期
		String dateTime = dateFm.format(new java.util.Date(s));
		return dateTime;
	}

	/**
	 * 
	 * 根据字符串格式去转换相应格式的日期和时间
	 * 
	 * 
	 * 
	 * @param java
	 *            .lang.String 必要参数
	 * 
	 * @return java.util.Date
	 * 
	 * @exception java.text.ParseException
	 * 
	 *                如果参数格式不正确会抛出此异常
	 * 
	 * **/

	public static Date reverse2Date(String date) {

		SimpleDateFormat simple = null;

		switch (date.trim().length()) {

		case 19:// 日期+时间
			simple = new SimpleDateFormat(FORMAT_0);

			break;

		case 10:// 仅日期

			simple = new SimpleDateFormat(FORMAT_1);

			break;

		case 8:// 仅时间
			simple = new SimpleDateFormat(FORMAT_2);

			break;

		default:

			break;

		}

		try {

			return simple.parse(date.trim());

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return null;

	}
	
	/**
	 * 
	 * 根据字符串格式去转换相应格式的日期和时间
	 * 
	 * 
	 * 
	 * @param java
	 *            .lang.String 必要参数
	 * 
	 * @return java.util.Date
	 * 
	 * @exception java.text.ParseException
	 * 
	 *                如果参数格式不正确会抛出此异常
	 * 
	 * **/

	public static java.sql.Date reverse2SqlDate(String date) {

		SimpleDateFormat simple = null;

		switch (date.trim().length()) {

		case 19:// 日期+时间
			simple = new SimpleDateFormat(FORMAT_0);

			break;

		case 10:// 仅日期

			simple = new SimpleDateFormat(FORMAT_1);

			break;

		case 8:// 仅时间
			simple = new SimpleDateFormat(FORMAT_2);

			break;

		default:

			break;

		}

		try {

			java.sql.Date sqldate = new java.sql.Date(simple.parse(date.trim()).getTime());
			return sqldate;

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return null;

	}
	

    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        if (dateStr != null && dateStr.trim().length() > 0) {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            try {
                formater.setLenient(false);
                d = formater.parse(dateStr);
            } catch (Exception e) {
                d = null;
            }
        }
        return d;
    }
    

	public static void main(String args[]) {
		System.out.println(DateUtils.getNow(DateUtils.FORMAT_0));
	}

}
