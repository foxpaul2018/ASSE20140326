package com.ebank.fsb.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	public static Date strToDate() throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);
		Date date = bartDateFormat.parse(strdate);
		return date;
	}

	public static String getDateDay(String sdate) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		strDate = String.valueOf(today.getDate());

		return strDate;
	}

	public static String getDateMonth(String sdate) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		strDate = String.valueOf(today.getMonth() + 1);
		if (strDate.length() == 1) {
			strDate = "0" + strDate;
		}
		return strDate;
	}

	public static String getStrDateFull() {
		Date date = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static Date strToDateFullInfo() throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		/**
		 * @HH:mm:ss 24Hours
		 * @hh:mm:ss 12Hours
		 */
		Date das = new Date();
		String strdate = bartDateFormat.format(das);
		Date date = bartDateFormat.parse(strdate);
		return date;
	}

	public static String getFileNameDT()  {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHH");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);

		return strdate;
	}
	public static String getFullDeafult()  {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);

		return strdate;
	}
	public static String getDeafult()  {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMM");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);

		return strdate;
	}
	
	public static String getStrDate() throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);

		return strdate;
	}

	public static String getStrDateFullInfo() throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);

		return strdate;
	}

	public static Date strToDate(String string) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = bartDateFormat.parse(string);
		return date;
	}

	public static Date strToSimpleDate(String string) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = bartDateFormat.parse(string);
		return date;
	}

	public static String getStrDate(Date date) throws ParseException {
		String strdate = null;
		if (date != null) {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strdate = bartDateFormat.format(date);
		}
		return strdate;
	}

	public static String getStrDateSimple(Date date)  {
		String strdate = null;
		if (date != null) {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			strdate = bartDateFormat.format(date);
		}
		return strdate;
	}

	public static String getStrDateTh(Date date) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static String getStrDateThs(Date date)  {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		String strdate = bartDateFormat.format(date);
		return strdate;
	}
	

	

	public static String getStrDateSe(Date date) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static String getStrNewDateMonth(Date date) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM");
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static String getFormatDate(Date date, String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static String getFormatstrDate(String strDate, String strFormat)
			throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(strFormat);
		Date date = bartDateFormat.parse(strDate);
		String strdate = bartDateFormat.format(date);
		return strdate;
	}

	public static String getFormatDt(String sourceDT, String formatStr)
			throws ParseException {
		Date appdt = strToDate(sourceDT);
		String abb = getFormatDate(appdt, formatStr);
		return abb;
	}

	public static String getAddDate(int Days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.DATE, Days);
		date = cal.getTime();
		String strDate = df.format(date);
		return strDate;
	}
	public static String getAddMonth(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.MONTH, Days);
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}
	public static String getAddMonthSe(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.MONTH, Days);
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}

	public static String getAddDate(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.DATE, Days);
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getAddDate(20));
	}
	public static String get6DigDate(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
	//	cal.add(Calendar.MONTH, today.getDay());
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}
	public static String getMinusDate(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.MONTH, -Days);
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}
	public static String getMinDate(String sdate, int Days) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		Date today;
		sdate = sdate.replace("-", "/");
		today = new Date(sdate);
		cal.set(Calendar.YEAR, today.getYear() + 1900);
		// System.out.println(today.getYear() + 1900);
		cal.set(Calendar.MONTH, today.getMonth());
		// System.out.println(today.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, today.getDate());
		// System.out.println(today.getDate());
		Date date = cal.getTime();
		cal.add(Calendar.MONTH, -Days);
		date = cal.getTime();
		strDate = df.format(date);

		return strDate;
	}
}
