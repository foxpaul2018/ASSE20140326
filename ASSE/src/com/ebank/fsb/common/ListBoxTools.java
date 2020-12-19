package com.ebank.fsb.common;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listfoot;
import org.zkoss.zul.Listfooter;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;

public class ListBoxTools {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void clearListBoxFree(Component componet, int ListItemNum,
			int ListcellNum) {
		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		Listhead listhead = (Listhead) listbox.getListhead().clone();
		listbox.getChildren().clear();
		listbox.appendChild(listhead);
		for (int j = 0; j < ListItemNum; j++) {
			Listitem listitem = new Listitem();
			for (int i = 0; i < ListcellNum; i++) {
				Listcell listcell = new Listcell();
				listitem.appendChild(listcell);
			}
			listbox.appendChild(listitem);
		}

	}

	public static void clearListBoxSeS(Component componet, boolean hasHead) {// clear
		// listbox

		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		if (hasHead) {
			Listhead listhead = (Listhead) listbox.getListhead().clone();
			Listitem listitem = (Listitem) ((Listitem) listbox.getItems().get(0)).clone();
			ClearUtil.clearXulElement(listitem);
			listbox.getChildren().clear();
			listbox.appendChild(listhead);
			listbox.appendChild(listitem);
		} else {
			listbox.getChildren().clear();

		}

	}
	
	
	public static void clearListBoxSe(Component componet, boolean hasHead) {// clear
		// listbox

		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		if (hasHead) {
			Listhead listhead = (Listhead) listbox.getListhead().clone();
			listbox.getChildren().clear();
			listbox.appendChild(listhead);

		} else {
			listbox.getChildren().clear();

		}

	}

	public static void clearListBoxRd(Component componet, int ListcellNum) {
		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		Listhead listhead = (Listhead) listbox.getListhead().clone();
		listbox.getChildren().clear();
		listbox.appendChild(listhead);
		Listitem listitem = new Listitem();
		for (int i = 0; i < ListcellNum; i++) {
			Listcell listcell = new Listcell();
			listitem.appendChild(listcell);
		}
		listbox.appendChild(listitem);
	}

	public static void clearListBoxTh(Component componet, int ListcellNum) {
		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		Listhead listhead = (Listhead) listbox.getListhead().clone();
		Listfoot lf = (Listfoot) listbox.getListfoot().clone();

		listbox.getChildren().clear();
		listbox.appendChild(listhead);

		// listbox.getChildren().clear();
		listbox.appendChild(lf);
		Listitem listitem = new Listitem();
		for (int i = 0; i < ListcellNum; i++) {
			Listcell listcell = new Listcell();
			listitem.appendChild(listcell);
		}
		listbox.appendChild(listitem);
	}

	public static void clearListBoxFate(Component componet, int ListcellNum) {
		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		Listhead listhead = (Listhead) listbox.getListhead().clone();
		Listfoot lf = (Listfoot) listbox.getListfoot().clone();
		Listfooter lft = (Listfooter) lf.getChildren().get(0);
		Paging pag = (Paging) lft.getChildren().get(0);
		pag.setPageSize(1);
		pag.setTotalSize(1);
		listbox.getChildren().clear();
		listbox.appendChild(listhead);

		// listbox.getChildren().clear();
		listbox.appendChild(lf);
		Listitem listitem = new Listitem();
		for (int i = 0; i < ListcellNum; i++) {
			Listcell listcell = new Listcell();
			listitem.appendChild(listcell);
		}
		listbox.appendChild(listitem);
	}

	public static void clearListBox(Component componet, boolean hasHead,
			int ListcellNum) {// clear listbox

		Listbox listbox = (Listbox) componet;
		listbox.setMold(null);
		if (hasHead) {
			Listhead listhead = (Listhead) listbox.getListhead().clone();
			listbox.appendChild(listhead);
		}
		listbox.getChildren().clear();
		Listitem listitem = new Listitem();
		for (int i = 0; i < ListcellNum; i++) {
			Listcell listcell = new Listcell();
			listitem.appendChild(listcell);
		}
		listbox.appendChild(listitem);
	}

	public static void onCreateListBox(Component componet, int ListitemNum,
			String setMold, int pageSize) {
		Listbox listbox = (Listbox) componet;
		for (int i = 0; i < ListitemNum; i++) {
			Listitem temp = (Listitem) listbox.getItems().get(0);
			Listitem temp1 = (Listitem) temp.clone();
			listbox.appendChild(temp1);
		}
		if (setMold != null && !setMold.equals(" ") && !setMold.equals("")) {
			listbox.setMold(setMold);
			listbox.setPageSize(pageSize);
		}
		// listbox.setMold("paging");
		// listbox.setPageSize(1);

		// listbox.setMold("paging");
		// listbox.setPageSize(1);
	}

}
