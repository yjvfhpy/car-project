package com.saturn.util.gpsoo;

import java.util.List;

public class GpsooHistory extends GpsooResult {
	private static final long serialVersionUID = -258058018025966934L;

	private List<GpsooGps> data;

	public List<GpsooGps> getData() {
		return data;
	}

	public void setData(List<GpsooGps> data) {
		this.data = data;
	}

	public static class GpsooGps {
		private long gps_time;
		private String lng;
		private String lat;
		private String course;
		private String speed;

		public long getGps_time() {
			return gps_time;
		}

		public void setGps_time(long gps_time) {
			this.gps_time = gps_time;
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

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getSpeed() {
			return speed;
		}

		public void setSpeed(String speed) {
			this.speed = speed;
		}
	}

}
