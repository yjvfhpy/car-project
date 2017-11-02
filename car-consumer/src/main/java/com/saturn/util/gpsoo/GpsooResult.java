package com.saturn.util.gpsoo;

import java.io.Serializable;

public class GpsooResult implements Serializable {
	private static final long serialVersionUID = 3393793847981746799L;
    private int ret;
    private String msg;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
