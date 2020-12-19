package com.ebank.fsb.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadProp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getPropValue(String propvalue) {
		LogTool lt = new LogTool();
		String dReturn = null;
		Properties prop = new Properties();
		InputStream fin;
		try {
			// fin = (InputStream) this.getClass().getResourceAsStream(
			// "FTPCFG.properties");
			fin = (InputStream) this.getClass().getResourceAsStream("/hr.properties");
			if (fin != null) {
				prop.load(fin);
				fin.close();
				dReturn = prop.getProperty(propvalue);

			} else {
				lt.log("FileNotFoundException");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			lt.log("IOException");
			e.printStackTrace();
		}
		return dReturn;
	}

}
