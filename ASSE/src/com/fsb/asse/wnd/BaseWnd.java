package com.fsb.asse.wnd;

import java.sql.SQLException;

import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.ebank.fsb.common.MyDBUtils;
import com.ebank.fsb.common.ReadProp;

public class BaseWnd extends Window {
	private static final long serialVersionUID = 1959065218975005293L;

	public MyDBUtils myDBUtils;
	public ReadProp rp;

	public void onCreate() throws ClassNotFoundException, SQLException {
		rp = new ReadProp();
		boolean serveruat = Boolean.valueOf(String.valueOf(rp
				.getPropValue("isuat")));

		myDBUtils = new MyDBUtils();

		if (serveruat) {
			myDBUtils.setUrl(rp.getPropValue("uurl"));
			myDBUtils.setUserName(rp.getPropValue("uusr"));
			myDBUtils.setPassword(rp.getPropValue("upwd"));
		} else {
			myDBUtils.setUrl(rp.getPropValue("purl"));
			myDBUtils.setUserName(rp.getPropValue("pusr"));
			myDBUtils.setPassword(rp.getPropValue("ppwd"));
		}
	}

	public void onAdd(Button btn) {
		// TODO Auto-generated method stub
		Row row = (Row) btn.getParent().getParent();
		Row rowClone = (Row) row.clone();
		Rows rows = (Rows) row.getParent();
		rows.appendChild(rowClone);

	}

	public void onMinus(Button btn) {
		// TODO Auto-generated method stub
		Row row = (Row) btn.getParent().getParent();
		int seq_Amount = row.getParent().getChildren().size();
		if (seq_Amount > 1) {
			row.detach();
		}
	}

	public void showMsg(String msgTxt) {

		try {
			Messagebox.show(msgTxt, "人事考核系统", 1, null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
