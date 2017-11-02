package com.saturn.util;

public class MaintenanceInfo {
	private String timestamp;
	private String orderId;
	private int jCount;
	private int mCount;
	private String lasttime;
	private String route;
	private String state;
	
	
	
	
	public MaintenanceInfo(String timestamp, String orderId, int jCount, int mCount, String lasttime, String route,
			String state) {
		super();
		this.timestamp = timestamp;
		this.orderId = orderId;
		this.jCount = jCount;
		this.mCount = mCount;
		this.lasttime = lasttime;
		this.route = route;
		this.state = state;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getjCount() {
		return jCount;
	}
	public void setjCount(int jCount) {
		this.jCount = jCount;
	}
	public int getmCount() {
		return mCount;
	}
	public void setmCount(int mCount) {
		this.mCount = mCount;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
