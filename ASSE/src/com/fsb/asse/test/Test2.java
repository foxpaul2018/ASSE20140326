package com.fsb.asse.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {

	private static String driver = "net.sourceforge.jtds.jdbc.Driver";

	private static String url = "jdbc:jtds:sqlserver://sv260:1433;databaseName=OADB";

	private static String user = "sa";

	private static String pwd = "9999999999999";

	/**
	 * 
	 * @param args
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @throws SQLException
	 * 
	 */

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {

		getObj();

	}

	public static String getObj() throws ClassNotFoundException, SQLException {
		String dRtn = "";
		// TODO Auto-generated method stub
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(url, user, pwd);
		Statement st = con.createStatement();
		String qry = "SELECT * FROM STAFF_INFO where no='0606' ";
		// o(qry);
		ResultSet rs = st.executeQuery(qry);
		while (rs.next()) {
			String rst = rs.getString("DEPT");
			o("DEBUG:"+rst);
			String branchstatus = getXObj(rst);
			o("DEBUG:"+branchstatus);
			getCObj(rst, branchstatus);

		}
		st.close();
		con.close();
		return dRtn;
	}

	public static String getXObj(String arg) throws ClassNotFoundException,
			SQLException {
		String dRtn = "";
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(url, user, pwd);
		Statement st = con.createStatement();
		String qry = "SELECT * FROM FUBON_ASS_DEPT where d_no='" + arg + "'";
		// o(qry);
		ResultSet rs = st.executeQuery(qry);
		while (rs.next()) {
			dRtn = rs.getString("branch_status");
		}
		st.close();
		con.close();
		return dRtn;
	}

	public static String getCObj(String arg, String arg1)
			throws ClassNotFoundException, SQLException {
		String dRtn = "";
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(url, user, pwd);
		Statement st = con.createStatement();
		String qry = "SELECT * FROM FUBON_ASS_DEPT where d_no='" + arg + "'";
		// o(qry);
		ResultSet rs = st.executeQuery(qry);
		while (rs.next()) {
			if (Integer.valueOf(arg1) == 1) {
				if (Integer.valueOf(rs.getString("branch_status")) == 1) {
					System.out.println(rs.getString("HEAD"));
					getCObj(rs.getString("UP_NO"), "1");
				} else {
					System.out.println(rs.getString("HEAD"));
				}
			} else if (Integer.valueOf(arg1) == 2) {
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {
					System.out.println(rs.getString("HEAD"));
					getCObj(rs.getString("UP_NO"), "2");
				} else {
					System.out.println(rs.getString("HEAD"));
				}
			} else if (Integer.valueOf(arg1) == 0) {
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {
					System.out.println(rs.getString("HEAD"));
					getCObj(rs.getString("UP_NO"), "0");
				} else {
					System.out.println(rs.getString("HEAD"));
				}
			} else if (Integer.valueOf(arg1) == 3) {

				getCObj(rs.getString("UP_NO"), "1");

			}else if (Integer.valueOf(arg1) == 4) {

				//System.out.println(rs.getString("HEAD"));
				getCObj(rs.getString("UP_NO"), "0");
			}else if (Integer.valueOf(arg1) == 5) {

				System.out.println(rs.getString("HEAD"));
				//getCObj(rs.getString("UP_NO"), "0");
			}
		}
		st.close();
		con.close();
		return dRtn;
	}

	public static void o(Object obj) {
		System.out.println(obj);
	}
}
