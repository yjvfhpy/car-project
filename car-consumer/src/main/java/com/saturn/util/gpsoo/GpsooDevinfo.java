package com.saturn.util.gpsoo;

import java.util.List;

public class GpsooDevinfo extends GpsooResult {

	private static final long serialVersionUID = -2197583916382403837L;

	private List<Devinfo> data;

	public List<Devinfo> getData() {
		return data;
	}

	public void setData(List<Devinfo> data) {
		this.data = data;
	}

	class Devinfo {
		private String imei; // imei号
		private String name; // 设备名称
		private String number; // 车牌号
		private String phone; // 设备电话号码
		private int group_id; // 分组id
		private String group_name; // 分组名称
		private String dev_type; // 设备类型
		private int in_time; // 开通时间
		private int out_time;// 到期时间
		private String sudu;// 为-9表示未启用
		private boolean efence_support; // boolean 是否支持电子围栏
		private List<Children> children; // array 子客户数组

		public String getImei() {
			return imei;
		}

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public int getGroup_id() {
			return group_id;
		}

		public void setGroup_id(int group_id) {
			this.group_id = group_id;
		}

		public String getGroup_name() {
			return group_name;
		}

		public void setGroup_name(String group_name) {
			this.group_name = group_name;
		}

		public String getDev_type() {
			return dev_type;
		}

		public void setDev_type(String dev_type) {
			this.dev_type = dev_type;
		}

		public int getIn_time() {
			return in_time;
		}

		public void setIn_time(int in_time) {
			this.in_time = in_time;
		}

		public int getOut_time() {
			return out_time;
		}

		public void setOut_time(int out_time) {
			this.out_time = out_time;
		}

		public String getSudu() {
			return sudu;
		}

		public void setSudu(String sudu) {
			this.sudu = sudu;
		}

		public boolean isEfence_support() {
			return efence_support;
		}

		public void setEfence_support(boolean efence_support) {
			this.efence_support = efence_support;
		}

		public List<Children> getChildren() {
			return children;
		}

		public void setChildren(List<Children> children) {
			this.children = children;
		}

		@Override
		public String toString() {
			return "Devinfo [imei=" + imei + ", name=" + name + ", number=" + number + ", phone=" + phone
					+ ", group_id=" + group_id + ", group_name=" + group_name + ", dev_type=" + dev_type + ", in_time="
					+ in_time + ", out_time=" + out_time + ", sudu=" + sudu + ", efence_support=" + efence_support
					+ ", children=" + children + "]";
		}
		
	}

	class Children {
		private String id; // 子客户ID标识
		private String name; // 子客户登录名，可用于target传参，调用现有接口获取子客户相关信息
		private String showname; // 子客户名称

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getShowname() {
			return showname;
		}

		public void setShowname(String showname) {
			this.showname = showname;
		}

	}

}
