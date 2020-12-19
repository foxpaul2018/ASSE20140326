package com.ebank.fsb.common;

import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.sql.CallableStatement;
import java.text.SimpleDateFormat;

public class MyDBUtils {
	public final String ourCode = "310000300701";

	private String driver = "net.sourceforge.jtds.jdbc.Driver";
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String userName;
	private String password;

	/**
	 * @param args
	 * 
	 */
	public Object getColumnQuery(String query, String dbDriver, String column)
			throws ClassNotFoundException, SQLException {
		Object dRtn = "";

		Connection con = null;
		Class.forName(dbDriver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			// Map map = new HashMap();
			dRtn = rs.getObject(column);
			// System.out.println(rs.getString("CUFULNM"));
		}
		st.close();
		con.close();
		return dRtn;

	}

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(driver);
			CallableStatement proc = null;
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void onExecProduce(String sqlName) {
		try {
			Class.forName(driver);

			CallableStatement proc = null;
			Connection con = java.sql.DriverManager.getConnection(
					this.getUrl(), this.getUserName(), this.getPassword());
			// Statement st = con.createStatement();
			proc = con.prepareCall("{ call " + sqlName + " }");
			int rs = proc.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isDuplicate(String sql, String args, int strCode) {
		boolean bRtn = false;
		Connection con = null;
		try {
			Class.forName(driver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt(args) > strCode) {
					bRtn = true;
				} else {
					bRtn = false;
				}

			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bRtn;
	}

	public boolean canSave(String sql, String args, String strCode) {
		boolean bRtn = true;
		Connection con = null;
		try {
			Class.forName(driver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString(args).trim().equals(strCode.trim())) {
					bRtn = true;
				} else {
					bRtn = false;
				}

			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bRtn;
	}

	public MyDBUtils() {

	}

	public void batchInsrt(List<String> insertSqls, String xdriver) {
		Connection con = null;
		try {
			Class.forName(xdriver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			con.setAutoCommit(false);
			Statement inserst = con.createStatement();
			for (String strSql : insertSqls) {
				System.out.println(strSql);
				inserst.addBatch(strSql);
			}
			inserst.executeBatch();
			con.commit();
			con.close();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	public String getFullYear() {
		String dRtn = null;
		Date dt = new Date();
		String strYear = String.valueOf(dt.getYear() + 1900);
		dRtn = strYear.substring(0, 4);
		return dRtn;
	}

	public String getYear() {
		String dRtn = null;
		Date dt = new Date();
		String strYear = String.valueOf(dt.getYear() + 1900);
		dRtn = strYear.substring(2, 4);
		return dRtn;
	}

	public String getMonth() {
		String dRtn = null;
		Date dt = new Date();
		String strMonth = String.valueOf(dt.getMonth() + 1);
		dRtn = strMonth;
		return dRtn;
	}

	public String getDate() {
		String dRtn = null;
		Date dt = new Date();
		String strDate = String.valueOf(dt.getDate());
		dRtn = strDate;
		return dRtn;
	}

	public String getYYYYMMDD() {
		String dRtn = null;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date das = new Date();
		String strdate = bartDateFormat.format(das);
		return strdate;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean updQry(String query) {
		boolean bRtn = false;
		Connection con = null;
		try {
			Class.forName(driver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			String sql = query;
			System.out.println(sql);
			st.executeUpdate(sql);
			con.commit();
			st.close();
			con.close();
			bRtn = true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			bRtn = false;
		}
		return bRtn;
	}

	public boolean delQry(String query) {
		boolean bRtn = false;
		Connection con = null;
		try {
			Class.forName(driver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			String sql = query;
			System.out.println(sql);
			st.executeUpdate(sql);
			con.commit();
			st.close();
			con.close();
			bRtn = true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			bRtn = false;
		}
		return bRtn;
	}

	public boolean insertQry(String query) {
		boolean bRtn = false;
		Connection con = null;
		try {
			Class.forName(driver);
			//System.out.println(this.getUserName());
			//System.out.println(this.getPassword());
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());

			con.setAutoCommit(false); // Begin New Transaction Not Auto Commit
			/* @2012-03-31 Add RollBack*** */
			Statement st = con.createStatement();
			String sql = query;
			//System.out.println(sql);
			st.execute(sql);
			con.commit();
			st.close();
			con.close();
			bRtn = true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			bRtn = false;
		}
		return bRtn;
	}

	public int insertQryGetGenKey(String query) {
		int bRtn = 0;
		Connection con = null;
		try {
			Class.forName(driver);

			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());

			con.setAutoCommit(false); // Begin New Transaction Not Auto Commit
			/* @2012-03-31 Add RollBack*** */
			Statement st = con.createStatement();
			String sql = query;
			System.out.println(sql);
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				bRtn = rs.getInt(1);
			}
			con.commit();
			st.close();
			con.close();
			// bRtn = true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			// bRtn = false;
		}
		return bRtn;
	}

	public boolean insertQryBatch(List batchQuery) {
		boolean bRtn = false;
		Connection con = null;
		try {
			Class.forName(driver);
			con = java.sql.DriverManager.getConnection(this.getUrl(),
					this.getUserName(), this.getPassword());
			con.setAutoCommit(false);// Begin New Transaction Not Auto Commit
			/* @2012-03-31 Add RollBack*** */
			Statement st = con.createStatement();
			// String sql=null;
			for (Object o : batchQuery) {
				// sql = (String) o;
				st.addBatch((String) o);
			}
			// System.out.println(sql);
			st.executeBatch();
			con.commit();
			st.close();
			con.close();
			bRtn = true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			bRtn = false;
		}
		return bRtn;
	}

	public Map getQuery(String query, Class className)
			throws ClassNotFoundException, SQLException {
		Map map = new HashMap();
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {

			for (Object o : className.getDeclaredFields()) {
				// className.getFields()
				// System.out.println(((Field) o).getName());

				map.put(((Field) o).getName(),
						rs.getString(((Field) o).getName()));
			}
		}
		st.close();
		con.close();
		return map;

	}

	public List getQueryDtls(String query, Class className, String dbDriver)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(dbDriver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Map map = new HashMap();
			for (Object o : className.getDeclaredFields()) {
				// className.getFields()
				// System.out.println(((Field) o).getName());

				map.put(((Field) o).getName(),
						rs.getString(((Field) o).getName()));

			}
			list.add(map);
		}
		st.close();
		con.close();
		return list;

	}

	public List getSimpleQuery(String query, String dbDriver)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(dbDriver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			if (rs.getString("trans_cus_id") != null) {
				String insert = "insert into Ctr_Loan_Cont_Cus values ( '"
						+ rs.getString("trans_cus_id") + "' )";
				list.add(insert);
			}
			// Map map = new HashMap();

			// System.out.println(rs.getString("cn_cont_no"));
			// System.out.println("trans_cus_id:	" +
			// rs.getString("trans_cus_id"));
			// System.out.println(rs.getString("CUFULNM"));
		}
		st.close();
		con.close();
		return list;

	}

	public List getQueryDtls(String query, Class className)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Map map = new HashMap();
			for (Object o : className.getDeclaredFields()) {
				// className.getFields()
				// System.out.println(((Field) o).getName());

				map.put(((Field) o).getName(),
						rs.getString(((Field) o).getName()));

			}
			list.add(map);
		}
		st.close();
		con.close();
		return list;

	}

	public Object getSampleQry(String query, String strArg)
			throws ClassNotFoundException, SQLException {
		Object dRtn = 0;
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			dRtn = rs.getObject(strArg);
		}
		st.close();
		con.close();
		return dRtn;

	}

	public List getQryList(String query, String strArg)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();
		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			list.add(rs.getString(strArg));
		}
		st.close();
		con.close();
		return list;

	}

	public List getObjQry(String query, String name)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out
					.println("Start Stack:.//////////////////////////////////");
			Class<?> booClass = Class.forName(name);
			Object boo;
			try {
				boo = booClass.newInstance();
				Method[] methods = booClass.getDeclaredMethods();
				// Field[] flds = booClass.getDeclaredFields();
				for (Method mt : methods) {
					// Method mt = methods[i];
					// Field fl = flds[i];
					if (mt.getName().substring(0, 3).equalsIgnoreCase("set")) {
						// Object
						// System.out.println(method.getName().substring(3,
						// method.getName().length()).toLowerCase());
						String uFld = mt.getName().substring(3,
								mt.getName().length());
						String nuFld = uFld.substring(0, 1).toLowerCase()
								+ uFld.substring(1, uFld.length());

						System.out.println(uFld);
						System.out.println(nuFld);
						System.out.println(rs.getObject(nuFld));
						if (rs.getObject(nuFld) != null) {
							if (nuFld.contains("usMemo")) {
								mt.invoke(boo, rs.getString(nuFld));
							} else if (nuFld.contains("memo")) {
								mt.invoke(boo, rs.getString(nuFld));
							} else {
								mt.invoke(boo, rs.getObject(nuFld));
							}

						}

					}
				}
				System.out
						.println("End Stack:.//////////////////////////////////");
				list.add(boo);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		st.close();
		con.close();
		return list;

	}

	public List getObjectQuery(String query, String name)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(driver);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Class<?> booClass = Class.forName(name);
			Object boo;
			try {
				boo = booClass.newInstance();
				Method[] methods = booClass.getDeclaredMethods();
				// Field[] flds = booClass.getDeclaredFields();
				for (Method mt : methods) {
					// Method mt = methods[i];
					// Field fl = flds[i];
					if (mt.getName().substring(0, 3).equalsIgnoreCase("set")) {
						// Object
						// System.out.println(method.getName().substring(3,
						// method.getName().length()).toLowerCase());
						String uFld = mt.getName().substring(3,
								mt.getName().length());
						String nuFld = uFld.substring(0, 1).toLowerCase()
								+ uFld.substring(1, uFld.length());
						if (rs.getString(nuFld) != null) {
							mt.invoke(boo, rs.getString(nuFld));

						}

					}
				}
				list.add(boo);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		st.close();
		con.close();
		return list;

	}

	public List getObjectQueryOrc(String query, String name, String drv)
			throws ClassNotFoundException, SQLException {
		List list = new ArrayList();

		Connection con = null;
		Class.forName(drv);
		con = java.sql.DriverManager.getConnection(this.getUrl(),
				this.getUserName(), this.getPassword());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Class<?> booClass = Class.forName(name);
			Object boo;
			try {
				boo = booClass.newInstance();
				Method[] methods = booClass.getDeclaredMethods();
				// Field[] flds = booClass.getDeclaredFields();
				for (Method mt : methods) {
					// Method mt = methods[i];
					// Field fl = flds[i];
					if (mt.getName().substring(0, 3).equalsIgnoreCase("set")) {
						// Object
						// System.out.println(method.getName().substring(3,
						// method.getName().length()).toLowerCase());
						String uFld = mt.getName().substring(3,
								mt.getName().length());
						String nuFld = uFld.substring(0, 1).toLowerCase()
								+ uFld.substring(1, uFld.length());
						if (rs.getString(nuFld) != null) {
							mt.invoke(boo, rs.getString(nuFld));

						}

					}
				}
				list.add(boo);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		st.close();
		con.close();
		return list;

	}

	public static String getClobString(ResultSet rs, String col) {
		try {
			Reader reader = rs.getCharacterStream(col);
			if (reader == null) {
				return null;
			}
			StringBuffer sb = new StringBuffer();
			char[] charbuf = new char[4096];
			for (int i = reader.read(charbuf); i > 0; i = reader.read(charbuf)) {
				sb.append(charbuf, 0, i);
			}
			return sb.toString();
		} catch (Exception e) {
			return " ";
		}
	}
}
