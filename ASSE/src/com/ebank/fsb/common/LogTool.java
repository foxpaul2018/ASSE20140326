package com.ebank.fsb.common;


import java.io.PrintWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class LogTool {
	private PrintWriter logPrint;

	private String logFile = "";

	private String logName = "";

	/**
	 * ����û��ͷ��log
	 */
	public LogTool() {
		checkDate();
	}

	/**
	 * ����log�ļ�����?
	 */
	public LogTool(String logName) {
		this.logName = logName;
		checkDate();
	}

	/**
	 * �õ�log�ļ���
	 */
	private String getLogFile() {
		String date = "";
		Calendar cd = Calendar.getInstance();
		int y = cd.get(Calendar.YEAR);
		int m = cd.get(Calendar.MONTH) + 1;
		int d = cd.get(Calendar.DAY_OF_MONTH);
		date = "./log/" + logName + y + "-";

		if (m < 10)
			date += 0;

		date += m + "-";

		if (d < 10)
			date += 0;

		date += d + ".log";

		return date;
	}

	/**
	 * ����log���ԣ����û���½�log�ļ�
	 */
	private void newLog() {
		logFile = getLogFile();

		try {
			logPrint = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			(new File("./log")).mkdir();

			try {
				logPrint = new PrintWriter(new FileWriter(logFile, true), true);
			} catch (IOException ex) {
				System.err.println("�޷�����־�ļ���" + logFile);
				logPrint = new PrintWriter(System.err);
			}
		}
	}

	/**
	 * ����������ı��½�log�ļ�
	 */
	private void checkDate() {
		if (logFile == null || logFile.trim().equals("") || !logFile.equals(getLogFile())) {
			newLog();
		}
	}

	/**
	 * ���ı���Ϣд����־�ļ���ȱʡΪuserdir/log
	 */
	public void log(String msg) {
		checkDate();
		logPrint.println(DateTools.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ": " + msg);
	}

	/**
	 * ���ı���Ϣ���쳣д����־�ļ�
	 */
	public void log(Throwable e, String msg) {
		checkDate();
		logPrint.println(new Date() + ": " + msg);
		e.printStackTrace(logPrint);
	}
}
