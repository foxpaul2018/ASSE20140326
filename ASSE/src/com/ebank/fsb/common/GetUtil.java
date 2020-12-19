package com.ebank.fsb.common;

import java.math.BigDecimal;
import java.util.Date;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Spinner;

public class GetUtil {

	public static boolean getCheckbox(Component x) {
		boolean dRetrun = false;
		if (x.getClass().toString().contains("Checkbox")) {
			dRetrun = ((Checkbox) x).isChecked();
		}

		return dRetrun;
	}

	public static int getCheckBoxInt(Component x) {
		int dRetrun = 0;
		if (x.getClass().toString().contains("Checkbox")) {
			if (((Checkbox) x).isChecked()) {
				dRetrun = 1;
			}
		}
		return dRetrun;
	}

	public static double getNumValue(Component x) {
		double dReturn = -1;
		if (x.getClass().toString().contains("Textbox")) {
			dReturn = Float.valueOf(((Textbox) x).getValue());
		}
		return dReturn;
	}

	public static int getIntBoxValue(Component x) {
		int dReturn = -1;
		if (x.getClass().toString().contains("Intbox")) {
			if (((Intbox) x).getValue() != null) {
				dReturn = ((Intbox) x).getValue();
			}
		}
		return dReturn;
	}

	public static String getComboContextValue(Component x) {
		String sReturn = null;
		if (x.getClass().toString().contains("Combobox")) {
			String sValue = ((Combobox) x).getValue();
			for (int i = 0; i < ((Combobox) x).getItemCount()
					&& sReturn == null; i++) {
				Comboitem temp = (Comboitem) ((Combobox) x).getItems().get(i);
				if (temp.getLabel().equals(sValue)) {
					sReturn = temp.getContext();
				}
			}
		}
		return sReturn;
	}

	public static String getComboValue(Component x) {
		String sReturn = null;
		if (x.getClass().toString().contains("Combobox")) {
			sReturn = ((Combobox) x).getValue();
		}
		return sReturn;
	}

	public static String getTextValue(Row row, int j) {
		String sReturn = null;
		if (row.getChildren().get(j).getClass().toString().contains("Textbox")) {
			sReturn = ((Textbox) row.getChildren().get(j)).getValue();
		} else if (row.getChildren().get(j).getClass().toString()
				.contains("Label")) {
			sReturn = ((Label) row.getChildren().get(j)).getValue();
		} else if (row.getChildren().get(j).getClass().toString()
				.contains("Combobox")) {
			sReturn = ((Combobox) row.getChildren().get(j)).getValue();
		}
		return sReturn;
	}

	public static String getStringValue(Component x, int j, int k) {
		String sReturn = null;
		if (x.getClass().toString().contains("Row")) {
			if (x.getChildren().get(j).getClass().toString()
					.contains("Textbox")) {
				sReturn = ((Textbox) x.getChildren().get(j)).getValue();
			}
			if (x.getChildren().get(j).getClass().toString().contains("Label")) {
				sReturn = ((Label) x.getChildren().get(j)).getValue();
			}
			if (x.getChildren().get(j).getClass().toString()
					.contains("Combobox")) {
				sReturn = ((Combobox) x.getChildren().get(j)).getValue();
			}
			if (x.getChildren().get(j).getClass().toString().contains("Hbox")) {
				sReturn = getTextValue((Component) ((Hbox) x.getChildren().get(
						j)).getChildren().get(k));
				// getStringValue((Component)
				// ((Hbox)x.getChildren().get(j)).getChildren().get(k),j,k);
			}
		}
		return sReturn;

		// return sReturn;

	}

	public static String getComboValue(Component x, String sValue) {
		String sReturn = null;
		if (x.getClass().toString().contains("Combobox")) {
			// String sValue = ((Combobox)x).getValue();
			for (int i = 0; i < ((Combobox) x).getItemCount()
					&& sReturn == null; i++) {
				Comboitem temp = (Comboitem) ((Combobox) x).getItems().get(i);
				if (temp.getContext().equals(sValue)) {
					sReturn = temp.getLabel();
				}
			}
		}
		return sReturn;
	}

	public static String getTextValue(Component x) {
		String strReturn = null;
		if (x.getClass().toString().contains("Spinner")) {
			strReturn = String.valueOf(((Spinner) x).getValue());

		}
		if (x.getClass().toString().contains("Textbox")) {
			strReturn = ((Textbox) x).getValue();

		} else if (x.getClass().toString().contains("Combobox")) {
			strReturn = ((Combobox) x).getValue();

		} else if (x.getClass().toString().contains("Label")) {
			strReturn = ((Label) x).getValue();

		} else if (x.getClass().toString().contains("Intbox")) {
			strReturn =String.valueOf(((Intbox) x).getValue());

		}
		return strReturn;

	}

	public static BigDecimal getDecimalValue(Component x) {
		BigDecimal strReturn = null;
		if (x.getClass().toString().contains("Decimalbox")) {
			strReturn = ((Decimalbox) x).getValue();
		}
		return strReturn;

	}

	public static String getTextContext(Component x) {
		String strReturn = null;
		if (x.getClass().toString().contains("Textbox")) {
			strReturn = ((Textbox) x).getContext();

		} else if (x.getClass().toString().contains("Combobox")) {
			strReturn = ((Combobox) x).getContext();

		} else if (x.getClass().toString().contains("Label")) {
			strReturn = ((Label) x).getContext();

		}
		return strReturn;

	}

	public static Date getDateValue(Component x) {
		Date dateReturn = null;
		if (x.getClass().toString().contains("Datebox")) {
			dateReturn = ((Datebox) x).getValue();
		}
		return dateReturn;

	}

	public static Double getDoubleValue(Component x) {
		Double iReturn = -1.0;
		if (x.getClass().toString().contains("Textbox")) {
			String temp = ((Textbox) x).getValue();
			if (!temp.equals(""))
				iReturn = Double.valueOf(temp);
		}
		return iReturn;

	}

	public static int getIntValue(Component x) {
		int iReturn = -1;
		if (x.getClass().toString().contains("Radiogroup")) {
			iReturn = ((Radiogroup) x).getSelectedIndex();
		}
		if (x.getClass().toString().contains("Textbox")) {
			String temp = ((Textbox) x).getValue();
			if (!temp.equals(""))
				iReturn = Integer.valueOf(temp);
		}
		return iReturn;

	}

}
