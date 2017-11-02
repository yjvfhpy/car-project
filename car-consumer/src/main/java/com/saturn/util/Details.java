package com.saturn.util;

public class Details {
	private String oid;
	private String service;
	private double pay;
	private String date;
	
	public Details(String oid, String service, double pay, String date) {
		super();
		this.oid = oid;
		this.service = service;
		this.pay = pay;
		this.date = date;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
