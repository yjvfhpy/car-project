package com.saturn.util.gpsoo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.saturn.util.HttpUtil;
import com.saturn.util.gpsoo.GpsooDevinfo.Devinfo;

public class GpsooApi {

	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	/**
	 * 获取accesstoken URL
	 */
	private static String ACCESS_TOKEN_URL = "http://api.gpsoo.net/1/auth/access_token";
	/**
	 * 根据经纬度得到中文地址URL
	 */
	private static String ADDRESS_URL = "http://api.gpsoo.net/1/tool/address";

	/**
	 * 查询账号下所有设备的相关信息URL
	 */
	private static String DEVINFO_URL = "http://api.gpsoo.net/1/account/devinfo";

	/**
	 * 获取一个账户名下所有设备最新位置信息URL
	 */
	private static String MONITOR_URL = "http://api.gpsoo.net/1/account/monitor";

	/**
	 * 获取单个/批量设备最新位置信息
	 */
	private static String TRACKING_URL = "http://api.gpsoo.net/1/devices/tracking";

	public static void main(String[] args) throws Exception {
		 GpsooDevinfo devinfo = getDevinfo("20007326982310146707817232daa01b58800a474f6e45b2878383fd7000010010014010");
		 List<Devinfo> datas = devinfo.getData();
		 for (Devinfo dev : datas) {
			System.out.println(dev);
		}
		
	}

	/**
	 * 通用参数
	 * 
	 * @return
	 */
	private static Map<String, Object> getRequestCommonString(String token) {
		long time = System.currentTimeMillis() / 1000;
		Map<String, Object> params = new HashMap<>();
		params.put("access_token", token);
		params.put("account", Constant.STORE_LOGIN_USER);
		params.put("time", time);
		params.put("format", "json");
		return params;
	}

	/**
	 * 获取accesstoken
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public static GpsooAccessToken getAccesstoken(String account, String password, long time) {
		String signature = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + time);
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("time", time);
		params.put("signature", signature);

		String result = HttpUtil.get(ACCESS_TOKEN_URL, null, params);
		System.out.println("获取accesstoken:" + result);
		Type tType = new TypeToken<GpsooResult>() {

		}.getType();
		GpsooResult gr = gson.fromJson(result, tType);
		if (0 == gr.getRet()) {
			Type tType1 = new TypeToken<GpsooAccessToken>() {
			}.getType();
			GpsooAccessToken token = gson.fromJson(result, tType1);
			return token;
		}
		return null;
	}

	/**
	 * 根据经纬度得到中文地址
	 * 
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static String getAddress(String lng, String lat, String token) {
		Map<String, Object> params = getRequestCommonString(token);
		params.put("map_type", "BAIDU");
		params.put("lang", "zh-cn");
		params.put("lat", lat);
		params.put("lng", lng);

		String result = HttpUtil.get(ADDRESS_URL, null, params);
		String address = "";

		Type tType = new TypeToken<GpsooResult>() {
		}.getType();
		GpsooResult gr = gson.fromJson(result, tType);
		if (0 == gr.getRet()) {
			Type tType1 = new TypeToken<GpsooAddress>() {
			}.getType();
			GpsooAddress gpsooAddress = gson.fromJson(result, tType1);
			address = gpsooAddress.getAddress();
			address = address.replace(".", "");
		}
		return address;

	}

	/**
	 * 获取一个账户名下所有设备最新位置信息
	 * 
	 * @return
	 */
	public static GpsooMonitor getMonitor(String token) {
		Map<String, Object> params = getRequestCommonString(token);
		params.put("target", Constant.STORE_LOGIN_USER);
		params.put("map_type", "BAIDU");

		String result = HttpUtil.get(MONITOR_URL, null, params);

		Type tType = new TypeToken<GpsooResult>() {
		}.getType();
		GpsooResult gr = gson.fromJson(result, tType);
		if (0 == gr.getRet()) {
			Type tType1 = new TypeToken<GpsooMonitor>() {
			}.getType();
			GpsooMonitor monitor = gson.fromJson(result, tType1);
			return monitor;
		}
		return null;
	}

	/**
	 * 获取单个/批量设备最新位置信息
	 * 
	 * @return
	 */
	public static GpsooMonitor getTracking(String token, String imei) {
		Map<String, Object> params = getRequestCommonString(token);
		params.put("imeis", imei);
		params.put("map_type", "BAIDU");
		String result = HttpUtil.get(TRACKING_URL, null, params);
		Type tType = new TypeToken<GpsooResult>() {

		}.getType();
		GpsooResult gr = gson.fromJson(result, tType);
		if (0 == gr.getRet()) {
			Type tType1 = new TypeToken<GpsooMonitor>() {

			}.getType();
			GpsooMonitor tracking = gson.fromJson(result, tType1);
			return tracking;
		}
		return null;
	}

	/**
	 * 查询账号下所有设备的相关信息
	 * 
	 * @return
	 */
	public static GpsooDevinfo getDevinfo(String token) {
		Map<String, Object> params = getRequestCommonString(token);
		params.put("target", Constant.STORE_LOGIN_USER);
		String result = HttpUtil.get(DEVINFO_URL, null, params);
		Type tType = new TypeToken<GpsooResult>() {

		}.getType();
		GpsooResult gr = gson.fromJson(result, tType);
		if (0 == gr.getRet()) {
			Type tType1 = new TypeToken<GpsooDevinfo>() {

			}.getType();
			GpsooDevinfo devinfo = gson.fromJson(result, tType1);
			return devinfo;
		}
		return null;
	}

}
