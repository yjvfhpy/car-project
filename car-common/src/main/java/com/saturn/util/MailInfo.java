package com.saturn.util;

public class MailInfo {
	private String smtpHost;
	private String mailFrom;
	private String mailTo;
	private String msgContent;
	private String subJect;
	
	public MailInfo(String mailTo, String msgContent, String subJect) {
		super();
		this.smtpHost = "smtp.ym.163.com";
		this.mailFrom = "liujian@saturngame.net";
		this.mailTo = mailTo;
		this.msgContent = msgContent;
		this.subJect = subJect;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getSubJect() {
		return subJect;
	}
	public void setSubJect(String subJect) {
		this.subJect = subJect;
	}
	

}
