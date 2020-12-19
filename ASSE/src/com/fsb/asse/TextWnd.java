package com.fsb.asse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listfooter;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ebank.fsb.common.GetUtil;
import com.fsb.asse.sub.ASSE_STRUCT;
import com.fsb.asse.wnd.BaseWnd;

public class TextWnd extends BaseWnd {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2623326332792363183L;

	public String uid;

	public String period;

	public Map getStructMap() {
		return structMap;
	}

	public void setStructMap(Map structMap) {
		this.structMap = structMap;
	}

	public Map structMap;

	// public Map dataMap;

	// public Map measureMap;

	public void onCreate() {
		// measureMap=new HashMap();
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

		period = (String) Sessions.getCurrent().getAttribute("period");

	}

	public void onTest() {
		Grid grid = (Grid) this.getFellow("yes");
		List<Row> rowList = grid.getRows().getChildren();
		for (Row row : rowList) {
			for (Object o : row.getChildren()) {
				if (o.getClass().toString()
						.contains("class org.zkoss.zul.Detail")) {
					Detail dts = (Detail) o;
					Hlayout hyl = (Hlayout) dts.getChildren().get(0);
					Grid gc = (Grid) hyl.getChildren().get(0);
					Row oneRow = (Row) gc.getRows().getChildren().get(0);
					Textbox tb = (Textbox) oneRow.getChildren().get(0);

					System.out.println(tb.getText());
				}
				// System.out.println(o.getClass().toString().contains("class org.zkoss.zul.Detail"));
			}
		}
	}

	public void onAddRow(Button btn) {

		Row row = (Row) btn.getParent().getParent();
		Row rowClone = (Row) row.clone();
		Rows rows = (Rows) row.getParent();
		rowClone.setContext("1");
		rows.appendChild(rowClone);

	}

	public void onMinusRow(Button btn) {
		// TODO Auto-generated method stub
		Row row = (Row) btn.getParent().getParent();
		int seq_Amount = row.getParent().getChildren().size();
		System.out.println(row.getContext());
		if (row.getContext().contains("guding")) {
			try {
				Messagebox.show("固定行数据无法删除。");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			if (seq_Amount > 1) {
				row.detach();
			} else {
				try {
					Messagebox.show("最后一行数据无法删除。");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void addDtls(Label lc) throws SuspendNotAllowedException,
			InterruptedException {

		Listitem li = (Listitem) lc.getParent().getParent();
		Listcell lcell = (Listcell) li.getChildren().get(0);
		Label seqLabel = (Label) lcell.getChildren().get(0);

		DtlsWnd scf = (DtlsWnd) Executions.createComponents("dtls.zul", null,
				null);
		scf.setLabel(lc);

		scf.setLabelSeq(seqLabel.getValue());

		scf.setStructMap(structMap);
		// ASSE_STRUCT as=(ASSE_STRUCT) structMap.get(uid);

		// scf.setMeasureMap(as.getPg_measure_dtl());

		scf.doModal();
	}

	public void onAdd() {
		Listbox lb = (Listbox) this.getFellow("box");
		Listitem li = (Listitem) lb.getItems().get(0);
		Listitem liClone = (Listitem) li.clone();
		int seq = lb.getItems().size();
		Listcell lc = (Listcell) liClone.getChildren().get(0);
		Label lab = (Label) lc.getChildren().get(0);
		Listcell lc1 = (Listcell) liClone.getChildren().get(1);
		Combobox cb = (Combobox) lc1.getChildren().get(0);
		cb.setDisabled(false);

		lab.setValue(String.valueOf(seq + 1));
		liClone.setContext("1");
		lb.appendChild(liClone);

	}

	public void onRemove() {
		Listbox lb = (Listbox) this.getFellow("box");
		if (lb.getSelectedItem() != null) {
			if (lb.getSelectedItem().getContext().contains("guding")) {
				try {
					Messagebox.show("固定行无法删除");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ASSE_STRUCT as = (ASSE_STRUCT) structMap.get(uid);
				as.getPg_measure_dtl().remove(
						String.valueOf(lb.getSelectedIndex() + 1));
				// scf.setMeasureMap(as.getPg_measure_dtl());
				// lb.getSelectedIndex()+1;

				lb.removeChild(lb.getSelectedItem());
				int seq = lb.getItems().size();
				for (int i = 0; i < seq; i++) {
					Listitem liseqItem = (Listitem) lb.getItems().get(i);
					Listcell lc = (Listcell) liseqItem.getChildren().get(0);
					Label lab = (Label) lc.getChildren().get(0);
					lab.setValue(String.valueOf(i + 1));

				}
			}
		} else {
			try {
				Messagebox.show("请选择要删除的行");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onDos(Intbox ib) {
		Listbox listbox = (Listbox) this.getFellow("box");

		List<Listitem> lists = listbox.getItems();
		int intAmount = 0;
		for (Listitem li : lists) {
			Listcell lc = (Listcell) li.getChildren().get(2);

			Intbox iC = (Intbox) lc.getChildren().get(0);
			if (iC.getValue() != null) {
				intAmount += iC.getValue();
			}
		}
		Listfooter lf = (Listfooter) this.getFellow("last");

		lf.setLabel("当前权重:" + String.valueOf(intAmount) + "%");
		if (intAmount > 100) {
			try {
				Messagebox.show("指标权重百分比超过100%，请重新调节");
				ib.setValue(0);
				onDos(ib);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onJustSave() {
		// List<String> ary = new ArrayList();
		Listbox listbox = (Listbox) this.getFellow("box");
		List<Listitem> listitemList = listbox.getItems();

		int j = 1;
		ASSE_STRUCT as = (ASSE_STRUCT) structMap.get(uid);

		for (Listitem li : listitemList) {
			Listcell oneCell = (Listcell) li.getChildren().get(0);
			String oneSeq = GetUtil.getTextValue((Label) oneCell.getChildren()
					.get(0));

			Listcell seCell = (Listcell) li.getChildren().get(1);
			String seGouMian = GetUtil.getComboContextValue((Combobox) seCell
					.getChildren().get(0));

			Listcell rdCell = (Listcell) li.getChildren().get(2);
			String rd = GetUtil.getComboContextValue((Intbox) rdCell
					.getChildren().get(0));

			String insertSQL = " INSERT INTO FUBON_ASS_PG_DTL VALUES ("
					+ ISNULL(period) + "," + ISNULL(uid) + "," + ISNULL("1")
					+ "," + ISNULL(oneSeq) + "," + ISNULL(seGouMian) + ","
					+ ISNULL(rd) + "," + ISNULL("") + "," + ISNULL("") + ")";
			// ary.add(insertSQL);

			myDBUtils.insertQry(insertSQL);

			List<String> measureList = (List) as.getPg_measure_dtl()
					.get(oneSeq);
			for (String obj : measureList) {
				myDBUtils.insertQry(obj);

			}

			j++;
		}

		// scf.setMeasureMap(as.getPg_measure_dtl());
		/* as.getPg_measure_dtl().put(uid, ary); */

		String mainSQL = " UPDATE FUBON_ASS_MAIN SET PG_STATUS='1' WHERE PERIOD="
				+ ISNULL(period) + " AND LOGINID=" + ISNULL(uid);

		/*
		 * for (String o : ary) { myDBUtils.insertQry(o); }
		 */
		/*
		 * Object[] obj = as.getPg_measure_dtl().keySet().toArray(); for (Object
		 * o : obj) { System.out.println(o);
		 * myDBUtils.insertQry(String.valueOf(o)); }
		 */
		myDBUtils.updQry(mainSQL);
		try {
			Messagebox.show("SAVE OK !");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onJustSubmit() {
		onJustSave();
		String strManager = null;
		try {
			strManager = getManager(uid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mainSQL = " UPDATE FUBON_ASS_MAIN SET PG_STATUS='2',workflow_no='"
				+ strManager
				+ "',previous_no='' WHERE PERIOD="
				+ ISNULL(period) + " AND LOGINID=" + ISNULL(uid);
		myDBUtils.updQry(mainSQL);
	}

	public String getManager(String loginid) throws ClassNotFoundException,
			SQLException {
		String dRtn = "";
		String arg = String.valueOf(myDBUtils.getSampleQry(
				"select manager from FUBON_ASS_WORKFLOW where loginid='"
						+ loginid + "'", "manager"));

		String[] argForm = arg.split(",");
		/*
		 * for(int i=0;i<argForm.length;i++){
		 * 
		 * }
		 */

		dRtn = argForm[0];
		return dRtn;

	}

	public String ISNULL(String obj) {
		if (obj != null) {
			obj = "'" + obj + "'";
		}
		return obj;

	}
}
