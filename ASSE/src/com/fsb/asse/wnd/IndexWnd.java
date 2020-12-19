package com.fsb.asse.wnd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Center;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Window;

import com.ebank.fsb.common.PutUtil;
import com.fsb.asse.TextWnd;
import com.fsb.asse.sub.ASSE_STRUCT;
import com.fsb.asse.sub.user_mt_info;

public class IndexWnd extends BaseWnd {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708855714945926405L;

	public String uid;

	public String period;

	public Map structMap;
	
	
	public void onCreate() throws ClassNotFoundException, SQLException {
		try {
			super.onCreate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		structMap=new HashMap();
		
		uid = "0606";
		period = "2014";
		Sessions.getCurrent().setAttribute("uid", uid);
		Sessions.getCurrent().setAttribute("period", period);
		
		

		String strSQL = "select f.loginid,s.C_NAME,D_CNAME,s.C_POST from FUBON_ASS_MAIN f join STAFF_INFO s on f.loginid=s.no join DEPT_INFO d on s.DEPT=d.D_NO  and s.NO ='"
				+ uid + "'";

		try {
			List<user_mt_info> listDTL = (List<user_mt_info>) myDBUtils
					.getObjectQuery(strSQL, "com.fsb.asse.sub.user_mt_info");
			for (user_mt_info umi : listDTL) {
				PutUtil.putTextValue(this.getFellow("DEPT"), umi.getD_CNAME());
				PutUtil.putTextValue(this.getFellow("USERNAME"),
						umi.getC_NAME());
				PutUtil.putTextValue(this.getFellow("CPOST"), umi.getC_POST());
				PutUtil.putTextValue(this.getFellow("NO"), umi.getLoginid());
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*** 1.Now Generate Normal User Tree Menu ***/

		/* 1.先实现个人目标部分 */

		String qrySQL = "select pg_status from FUBON_ASS_MAIN where loginid='"
				+ uid + "' ";
		Object o = myDBUtils.getSampleQry(qrySQL, "pg_status");
		if (Integer.valueOf(String.valueOf(o)) == 0) {
			Treecell tc = (Treecell) this.getFellow("nianchutarget");
			tc.setLabel("年初目标制定(未提交)");
		}
	
		ASSE_STRUCT as=new ASSE_STRUCT();
		as.setLoginid(uid);
		as.setPeriod(period);
		
		
		Map pgMap=new HashMap();
		as.setPg_dtl(pgMap);
		
		Map pgMeasureMap=new HashMap();
		as.setPg_measure_dtl(pgMeasureMap);
		
		Map whMap=new HashMap();
		as.setWh_dtl(whMap);
		
		
		
		structMap.put(uid, as);
		
		
		
		
		/*** 1.End *********************************/
	}

	public void onSaveCheck() {
		Center cen = (Center) this.getFellow("inc");
		Include include = (Include) cen.getChildren().get(0);

		Window win = (Window) include.getChildren().get(0);
		// Window win=(Window) this.getPage().getFellow("tw");

		Grid grid = (Grid) win.getFellow("yes");
		System.out.println(grid);
	}

	public void onDoTarget() {
		Include inc = (Include) this.getFellow("includePage");
		
		
		inc.setSrc("x3.zul");
		TextWnd win = (TextWnd) inc.getChildren().get(0);
		win.setStructMap(structMap);
	}
}
