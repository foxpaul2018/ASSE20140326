package com.fsb.asse.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	private static String driver = "net.sourceforge.jtds.jdbc.Driver";

	private static String url = "jdbc:jtds:sqlserver://sv260:1433;databaseName=OADB";
	
	private static String user = "sa";
	
	private static String pwd = "9999999999";

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
		
		getObj("70");
		
	}

	public static String getObj(String arg) throws ClassNotFoundException,
			SQLException {
		String dRtn = "";
		// TODO Auto-generated method stub
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(url, user, pwd);
		Statement st = con.createStatement();
		String qry = "SELECT * FROM FUBON_ASS_DEPT WHERE D_NO='" + arg + "'";
		// o(qry);
		ResultSet rs = st.executeQuery(qry);
		while (rs.next()) {
			if (Integer.valueOf(rs.getString("branch_status")) == 0) {
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {
					System.out.println(rs.getString("HEAD"));
					getObj(rs.getString("UP_NO"));
				} else {
					System.out.println(rs.getString("HEAD"));
				}
			} else if (Integer.valueOf(rs.getString("branch_status")) == 1) {
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {

					System.out.println(rs.getString("HEAD"));

					getObj(rs.getString("UP_NO"));

				} else {

					System.out.println(rs.getString("HEAD"));

				}
			} else if (Integer.valueOf(rs.getString("branch_status")) == 2) {
				//System.out.println(rs.getString("branch_status"));
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {

					System.out.println(rs.getString("HEAD"));
					
						getObj(rs.getString("UP_NO"));
					
				} else {

					System.out.println(rs.getString("HEAD"));

				}
			}else if (Integer.valueOf(rs.getString("branch_status")) == 3) {
				//System.out.println(rs.getString("branch_status"));
				if (Integer.valueOf(rs.getString("UP_NO")) > 0) {

					System.out.println(rs.getString("HEAD"));
					
						getObj(rs.getString("UP_NO"));
					
				} else {

					System.out.println(rs.getString("HEAD"));

				}
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
