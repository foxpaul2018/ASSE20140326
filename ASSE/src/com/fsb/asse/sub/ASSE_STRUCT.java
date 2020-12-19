package com.fsb.asse.sub;

import java.util.Map;

public class ASSE_STRUCT {

	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Map getPg_dtl() {
		return pg_dtl;
	}
	public void setPg_dtl(Map pg_dtl) {
		this.pg_dtl = pg_dtl;
	}
	public Map getPg_measure_dtl() {
		return pg_measure_dtl;
	}
	public void setPg_measure_dtl(Map pg_measure_dtl) {
		this.pg_measure_dtl = pg_measure_dtl;
	}
	public Map getWh_dtl() {
		return wh_dtl;
	}
	public void setWh_dtl(Map wh_dtl) {
		this.wh_dtl = wh_dtl;
	}
	public String loginid;
	public String period;
	public Map pg_dtl;
	public Map pg_measure_dtl;
	public Map wh_dtl;
	
	
	
}
