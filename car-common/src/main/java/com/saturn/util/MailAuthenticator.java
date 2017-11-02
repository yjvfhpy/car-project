package com.saturn.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailAuthenticator extends Authenticator
{
    //******************************
	//由于发送邮件的地方比较多,
    //下面统一定义用户名,口令.
    //******************************
    public static String HUAWEI_MAIL_USER = "liujian@saturngame.net";
    public static String HUAWEI_MAIL_PASSWORD = "123456";
 
 
    public MailAuthenticator()
    {
    	super();
    }
 
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(HUAWEI_MAIL_USER, HUAWEI_MAIL_PASSWORD);
    }
 
}