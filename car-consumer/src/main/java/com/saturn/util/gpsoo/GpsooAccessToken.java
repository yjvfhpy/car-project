package com.saturn.util.gpsoo;

public class GpsooAccessToken extends GpsooResult {
	private static final long serialVersionUID = -7972993793316273325L;
	private String access_token;
	private int expires_in;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
}
