package com.ebank.fsb.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Spinner;

import com.ebank.fsb.common.DateTools;

public class PutUtil {

	public static void onSetGridValue(Grid grid, int line, int cols,
			String value) {
		if (grid.getCell(line, cols).getClass().toString().contains("Label")) {
			((Label) grid.getCell(line, cols)).setValue(value);
		} else if (grid.getCell(line, cols).getClass().toString().contains(
				"Textbox")) {
			((Textbox) grid.getCell(line, cols)).setValue(value);
		} else if (grid.getCell(line, cols).getClass().toString().contains(
				"Hbox")) {
			int num = ((Hbox) grid.getCell(line, cols)).getChildren().size();
			for (int i = 0; i < num; i++) {
				if (((Hbox) grid.getCell(line, cols)).getChildren().get(i)
						.getClass().toString().contains("Label")) {
					((Label) ((Hbox) grid.getCell(line, cols)).getChildren()
							.get(i)).setValue(value);
				} else if (((Hbox) grid.getCell(line, cols)).getChildren().get(
						i).toString().contains("Textbox")) {
					((Textbox) ((Hbox) grid.getCell(line, cols)).getChildren()
							.get(i)).setValue(value);
				}
			}
		}
	}

	public static void onSetCompentValue(Component compent, int line, int cols,
			String value) {
		if (compent.getClass().toString().contains("Grid")) {
			if (((Grid) compent).getCell(line, cols).getClass().toString()
					.contains("Label")) {
				((Label) ((Grid) compent).getCell(line, cols)).setValue(value);
			} else if (((Grid) compent).getCell(line, cols).getClass()
					.toString().contains("Textbox")) {
				((Textbox) ((Grid) compent).getCell(line, cols))
						.setValue(value);
			} else if (((Grid) compent).getCell(line, cols).getClass()
					.toString().contains("Hbox")) {
				int num = ((Hbox) ((Grid) compent).getCell(line, cols))
						.getChildren().size();
				for (int i = 0; i < num; i++) {
					onSetCompentValue((Component) ((Hbox) ((Grid) compent)
							.getCell(line, cols)).getChildren().get(i), line,
							cols, value);
				}
			}
		} else if (compent.getClass().toString().contains("Label")) {
			((Label) compent).setValue(value);
		} else if (compent.getClass().toString().contains("Textbox")) {
			((Textbox) compent).setValue(value);
			
		}
	}

	public static void putNumValue(Component x, double dNum) {

		if (x.getClass().toString().contains("Textbox")) {
			((Textbox) x).setValue(String.valueOf(dNum));

		}

	}

	public static void putComboboxValue(List list, Component x) {
		if (x.getClass().toString().contains("Combobox")) {
			List listChild = ((Combobox) x).getChildren();
			if (listChild.isEmpty() && !list.isEmpty()) {
				if (list.get(0).getClass().toString().equals(
						"class java.lang.String")) {
					for (int i = 0; i < list.size(); i++) {
						Comboitem temp = new Comboitem();
						temp.setLabel((String) (list.get(i)));
						((Combobox) x).appendChild(temp);
					}
				}

			}
		}
	}

	public static void putComboboxValueByPush(List list, Component x) {
		if (x.getClass().toString().contains("Combobox")) {
			List listChild = ((Combobox) x).getChildren();
			if (listChild.isEmpty() && !list.isEmpty()) {
				if (list.get(0).getClass().toString().equals(
						"class java.lang.String")) {
					for (int i = 0; i < list.size(); i++) {
						Comboitem temp = new Comboitem();
						temp.setLabel((String) (list.get(i)));
						((Combobox) x).appendChild(temp);
					}
				} else if (list.get(0).getClass().toString().equals(
						"class com.intron.create.MediaEnum")) {
					for (int i = 0; i < list.size(); i++) {
						Comboitem temp = new Comboitem();
						// temp.setLabel(((MediaEnum) list.get(i)).getZhtw());
						// temp.setContext(((MediaEnum)
						// list.get(i)).getIdName());
						((Combobox) x).appendChild(temp);
					}
				}

			} else {
				Components.removeAllChildren((Combobox) x);
				if (list.get(0).getClass().toString().equals(
						"class java.lang.String")) {
					for (int i = 0; i < list.size(); i++) {
						Comboitem temp = new Comboitem();
						temp.setLabel((String) (list.get(i)));
						((Combobox) x).appendChild(temp);
					}
				} else if (list.get(0).getClass().toString().equals(
						"class com.intron.create.MediaEnum")) {
					for (int i = 0; i < list.size(); i++) {
						Comboitem temp = new Comboitem();
						// temp.setLabel(((MediaEnum) list.get(i)).getZhtw());
						// temp.setContext(((MediaEnum)
						// list.get(i)).getIdName());
						((Combobox) x).appendChild(temp);
					}
				}

			}
		}
	}

	public static void putComboContextValue(Component x, Object sText) {
		String sValue = null;
		if (x.getClass().toString().contains("Combobox")) {
			for (int i = 0; i < ((Combobox) x).getItemCount() && sValue == null; i++) {
				Comboitem temp = (Comboitem) ((Combobox) x).getItems().get(i);
				if (temp.getContext().equals(String.valueOf(sText))) {
					sValue = temp.getLabel();
				}
			}
		}
		((Combobox) x).setValue(String.valueOf(sValue));
	}

	public static void putComboValue(Component x, String sText) {
		// String sValue = null;
		if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setValue(sText);
		}
	}
	
	public static void putComboValue(Component x, int sIndex) {
		// String sValue = null;
		if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setSelectedIndex(sIndex);
		}
	}
	

	public static void putRowValue(Row r, int i, Object strText) {

		if (r.getChildren().get(i).getClass().toString().contains("Textbox")) {
			((Textbox) r.getChildren().get(i))
					.setValue(String.valueOf(strText));

		} else if (r.getChildren().get(i).getClass().toString().contains(
				"Combobox")) {
			((Combobox) r.getChildren().get(i)).setValue(String
					.valueOf(strText));

		} else if (r.getChildren().get(i).getClass().toString().contains(
				"Label")) {
			((Label) r.getChildren().get(i)).setValue(String.valueOf(strText));
		}

	}

	public static void putTextContextValue(Component x, String strText) {

		if (x.getClass().toString().contains("Textbox")) {
			((Textbox) x).setContext(strText);

		} else if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setContext(strText);

		} else if (x.getClass().toString().contains("Label")) {
			((Label) x).setContext(strText);
		}

	}

	public static void putTextValue(Component x, String strText) {
		if (x.getClass().toString().contains("Spinner")) {
			((Spinner) x).setValue(Integer.valueOf(strText));

		} 
		if (x.getClass().toString().contains("Textbox")) {
			
			((Textbox) x).setValue(strText);

		} else if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setValue(strText);

		} else if (x.getClass().toString().contains("Label")) {
			((Label) x).setValue(strText);
		}else if (x.getClass().toString().contains("Toolbarbutton")) {
			((org.zkoss.zul.Toolbarbutton) x).setLabel(strText);
		}

	}

	public static void putTextValue(Component x, String strText,String style) {
		if (x.getClass().toString().contains("Spinner")) {
			((Spinner) x).setValue(Integer.valueOf(strText));
			((Spinner) x).setStyle(style);
		} 
		if (x.getClass().toString().contains("Textbox")) {
			((Textbox) x).setValue(strText);
			((Textbox) x).setStyle(style);

		} else if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setValue(strText);
			((Combobox) x).setStyle(style);

		} else if (x.getClass().toString().contains("Label")) {
			((Label) x).setValue(strText);
			((Label) x).setStyle(style);
		}

	}
	
	public static void putDecimalValue(Component x, BigDecimal strText) {

		if (x.getClass().toString().contains("Decimalbox")) {
			((Decimalbox) x).setValue(strText);

		}

	}

	public static void putDateValue(Component x, Date date) {

		if (x.getClass().toString().contains("Datebox")) {
			((Datebox) x).setValue(date);

		}

	}

	public static void putDateValue(Component x, String strDate) {

		if (x.getClass().toString().contains("Datebox")) {
			Date dt;
			try {
				dt = DateTools.strToSimpleDate(strDate);
				((Datebox) x).setValue(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}
	
	
	public static void putCheckboxValue(Component x, boolean iValue) {

		if (x.getClass().toString().contains("Checkbox")) {
			((Checkbox) x).setChecked(iValue);
			// ((Checkbox) x).setLabel(labelValue);
		}

	}

	public static void putCheckboxValue(Component x, boolean iValue,
			String labelValue) {

		if (x.getClass().toString().contains("Checkbox")) {
			((Checkbox) x).setChecked(iValue);
			((Checkbox) x).setLabel(labelValue);
		}

	}

	public static void putIntValue(Component x, int iValue) {

		if (x.getClass().toString().contains("Radiogroup")) {
			((Radiogroup) x).setSelectedIndex(iValue);
		}

	}
}
