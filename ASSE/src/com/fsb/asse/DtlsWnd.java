package com.fsb.asse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Button;
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

public class DtlsWnd extends Window {

	public String uid;

	public String period;

	/*public Map getMeasureMap() {
		return measureMap;
	}

	public void setMeasureMap(Map measureMap) {
		this.measureMap = measureMap;
	}

	public Map measureMap;
*/
	
	public Map getStructMap() {
		return structMap;
	}

	public void setStructMap(Map structMap) {
		this.structMap = structMap;
	}

	public Map structMap;
	
	
	public String getLabelSeq() {
		return labelSeq;
	}

	public void setLabelSeq(String labelSeq) {
		this.labelSeq = labelSeq;
	}

	public String labelSeq;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	private Label label;

	public void onCreate() {

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

	public void addDtls(Listcell lc) {

	}

	public void onDos(Intbox ib) {
		Listbox listbox = (Listbox) this.getFellow("dtls");

		List<Listitem> lists = listbox.getItems();
		int intAmount = 0;
		for (Listitem li : lists) {
			Listcell lc = (Listcell) li.getChildren().get(1);

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

	public void onAdd() {
		Listbox lb = (Listbox) this.getFellow("dtls");
		Listitem li = (Listitem) lb.getItems().get(0);
		Listitem liClone = (Listitem) li.clone();
		lb.appendChild(liClone);

	}

	public void onRemove() {
		Listbox lb = (Listbox) this.getFellow("dtls");
		if (lb.getSelectedItem() != null) {
			lb.removeChild(lb.getSelectedItem());
		} else {
			try {
				Messagebox.show("请选择要删除的行");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onSave() {
		/****1.保存检查***********/
		
		/****2.数据暂存***********/
		
		List ary = new ArrayList();
		Listbox listbox = (Listbox) this.getFellow("dtls");
		List<Listitem> listitemList = listbox.getItems();
		int j = 1;
		for (Listitem li : listitemList) {
			Listcell oneCell = (Listcell) li.getChildren().get(0);
			Listcell seCell = (Listcell) li.getChildren().get(1);
			String strMeasure = GetUtil.getTextValue((Textbox) oneCell
					.getChildren().get(0));
			String strIndexWeight = GetUtil.getTextValue((Intbox) seCell
					.getChildren().get(0));

			String insertSQL = " INSERT INTO FUBON_ASS_PG_MESU_DTL VALUES ("
					+ ISNULL(period) + "," + ISNULL(uid) + ","
					+ ISNULL(strMeasure) + "," + ISNULL(strIndexWeight) + ","
					+ ISNULL("") + "," + ISNULL("") + "," + ISNULL("") + ","
					+ ISNULL("") + "," + ISNULL(String.valueOf(j)) + ","
					+ ISNULL(this.getLabelSeq())+" )";
			ary.add(insertSQL);

			j++;
		}
		ASSE_STRUCT as=(ASSE_STRUCT) structMap.get(uid);
		
		//scf.setMeasureMap(as.getPg_measure_dtl());
		as.getPg_measure_dtl().put(this.getLabelSeq(), ary);

		if (ary.size() > 0) {
			label.setValue("衡量指标已设");
			label.setStyle("color:blue");
			this.detach();
		}else{
			try {
				Messagebox.show("没有数据保存");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onClose() {
		this.detach();
	}

	public String ISNULL(String obj) {
		if (obj != null) {
			obj = "'" + obj + "'";
		}
		return obj;

	}

}
