package com.saturn.util.gpsoo;

import java.util.List;

public class GpsooMonitor extends GpsooResult{
	private static final long serialVersionUID = 4398164010115786313L;
	
	private List<MonitorDevice> data;
	public List<MonitorDevice> getData() {
		return data;
	}
	public void setData(List<MonitorDevice> data) {
		this.data = data;
	}

	public static class MonitorDevice {
		private String imei;
		private int device_info;
		private long gps_time;
		private long sys_time;
		private long heart_time;
		private long server_time;
		private String lng;
		private String lat;
		private long course;
		private long speed;
		private String status;
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
		public int getDevice_info() {
			return device_info;
		}
		public void setDevice_info(int device_info) {
			this.device_info = device_info;
		}
		public long getGps_time() {
			return gps_time;
		}
		public void setGps_time(long gps_time) {
			this.gps_time = gps_time;
		}
		public long getSys_time() {
			return sys_time;
		}
		public void setSys_time(long sys_time) {
			this.sys_time = sys_time;
		}
		public long getHeart_time() {
			return heart_time;
		}
		public void setHeart_time(long heart_time) {
			this.heart_time = heart_time;
		}
		public long getServer_time() {
			return server_time;
		}
		public void setServer_time(long server_time) {
			this.server_time = server_time;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public long getCourse() {
			return course;
		}
		public void setCourse(long course) {
			this.course = course;
		}
		public long getSpeed() {
			return speed;
		}
		public void setSpeed(long speed) {
			this.speed = speed;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	}

}
