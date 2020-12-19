package com.ebank.fsb.common;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

public class ClearUtil {
	
	public static void clearCombobox(Component x){
		if (x.getClass().toString().contains("Combobox")) {
			((Combobox) x).setValue("");
			((Combobox) x).getChildren().clear();
		}
	}
	
	public static void clearXulElement(Component x) {
		try {
			if (x.getClass().toString().contains("Textbox")) {
				if (((Textbox) x).getName() != null) {
					if (((Textbox) x).getName().equals("filename")) {
					} else if (((Textbox) x).getName().equals("nopera")) {
					} else {
						((Textbox) x).setValue("");
					}
				} else {
					((Textbox) x).setValue("");
				}
			}
			if (x.getClass().toString().contains("Button")) {
			}
			if (x.getClass().toString().contains("Label")) {
			}
			if (x.getClass().toString().contains("Intbox")) {
				((Intbox) x).setValue(null);
			}
			if (x.getClass().toString().contains("Combobox")) {
				((Combobox) x).setValue("");
			}
			if (x.getClass().toString().contains("Checkbox")) {
				((Checkbox) x).setChecked(false);
			}
			if (x.getClass().toString().contains("Datebox")) {
				((Datebox) x).setValue(null);
			}
			if (x.getClass().toString().contains("Hbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
			if (x.getClass().toString().contains("Vbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));
				}
			}
			if (x.getClass().toString().contains("Tabbox")) {
				int num = ((Tabbox) x).getTabpanels().getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) ((Tabbox) x)
							.getTabpanels().getChildren().get(i));//
				}
			} else {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Clear Exception");
		}
	}
	
	public static void clearXulElementNoDate(Component x) {
		try {
			if (x.getClass().toString().contains("Textbox")) {
				if (((Textbox) x).getName() != null) {
					if (((Textbox) x).getName().equals("filename")) {

					} else if (((Textbox) x).getName().equals("nopera")) {

					} else {
						((Textbox) x).setValue("");
					}
				} else {
					((Textbox) x).setValue("");
				}
			}
			if (x.getClass().toString().contains("Button")) {
			}
			if (x.getClass().toString().contains("Label")) {
			}
			if (x.getClass().toString().contains("Intbox")) {
				((Intbox) x).setValue(null);
			}
			if (x.getClass().toString().contains("Combobox")) {
				((Combobox) x).setValue("");
			}
			if (x.getClass().toString().contains("Checkbox")) {
				((Checkbox) x).setChecked(false);
			}
			if (x.getClass().toString().contains("Hbox")) {

				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElementNoDate((AbstractComponent) x.getChildren().get(i));//
				}
			}
			if (x.getClass().toString().contains("Vbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElementNoDate((AbstractComponent) x.getChildren().get(i));
				}
			}
			if (x.getClass().toString().contains("Tabbox")) {
				int num = ((Tabbox) x).getTabpanels().getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElementNoDate((AbstractComponent) ((Tabbox) x)
							.getTabpanels().getChildren().get(i));//
				}
			} else {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElementNoDate((AbstractComponent) x.getChildren().get(i));//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Clear Exception");
		}
	}
	
	public static void clearXulElement(AbstractComponent x) {
		try {
			if (x.getClass().toString().contains("Textbox")) {
				if (((Textbox) x).getName() != null) {
					if (((Textbox) x).getName().equals("filename")) {

					} else if (((Textbox) x).getName().equals("nopera")) {

					} else {
						((Textbox) x).setValue("");
					}
				} else {
					((Textbox) x).setValue("");
				}
			}
			if (x.getClass().toString().contains("Button")) {

			}
			if (x.getClass().toString().contains("Label")) {
			}
			if (x.getClass().toString().contains("Combobox")) {
				((Combobox) x).setValue("");
			}
			if (x.getClass().toString().contains("Listcell")) {
			//	Listcell lc=new Listcell)();
				((Listcell) x).setLabel("");
			}
			if (x.getClass().toString().contains("Checkbox")) {
				((Checkbox) x).setChecked(false);
			}
			if (x.getClass().toString().contains("Intbox")) {
				((Intbox) x).setValue(null);
			}
			if (x.getClass().toString().contains("Datebox")) {
				((Datebox) x).setValue(null);
			}
			if (x.getClass().toString().contains("Hbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
			if (x.getClass().toString().contains("Vbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));
				}
			}
			if (x.getClass().toString().contains("Listitem")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));
				}
			}
			if (x.getClass().toString().contains("Tabbox")) {
				int num = ((Tabbox) x).getTabpanels().getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) ((Tabbox) x)
							.getTabpanels().getChildren().get(i));//
				}
			} else {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Clear Exception");
		}
	}

	
	public static void clearXulElementSingle(Component x) {
		try {
			if (x.getClass().toString().contains("Textbox")) {
				if (((Textbox) x).getName() != null) {
					if (((Textbox) x).getName().equals("filename")) {
					} else if (((Textbox) x).getName().equals("nopera")) {
					} else {
						((Textbox) x).setValue("");
					}
				} else {
					((Textbox) x).setValue("");
				}
			}
			
			if (x.getClass().toString().contains("Hbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
			if (x.getClass().toString().contains("Vbox")) {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));
				}
			}
			if (x.getClass().toString().contains("Tabbox")) {
				int num = ((Tabbox) x).getTabpanels().getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) ((Tabbox) x)
							.getTabpanels().getChildren().get(i));//
				}
			} else {
				int num = x.getChildren().size();
				for (int i = 0; i < num; i++) {
					clearXulElement((AbstractComponent) x.getChildren().get(i));//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Clear Exception");
		}
	}
}
