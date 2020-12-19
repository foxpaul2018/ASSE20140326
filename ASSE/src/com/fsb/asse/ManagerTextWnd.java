package com.fsb.asse;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zk.ui.Sessions;

import com.fsb.asse.sub.FUBON_ASS_MAIN;
import com.fsb.asse.wnd.BaseWnd;

public class ManagerTextWnd extends BaseWnd {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1469746139771311457L;

	public String uid;

	public String period;

	public void onCreate() {
		try {
			super.onCreate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		uid = (String) Sessions.getCurrent().getAttribute("uid");
		uid = "0120";
		period = (String) Sessions.getCurrent().getAttribute("period");
		period = "2014";
	}

	public void onPutDetails() throws ClassNotFoundException, SQLException {
		List<FUBON_ASS_MAIN> list;

		String sql = "SELECT * FROM FUBON_ASS_MAIN where ";
		list = myDBUtils
				.getObjectQuery(sql, "com.ebank.fsb.sub.FUBON_ASS_MAIN");

	}

}
