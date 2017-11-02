package com.saturn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DateTimeUtil
{
    public static long Hours24TimeMillis = 86400000L;

    /**
     * 得到当前的小时数,0-23
     * 
     * @return
     */
    public static int getCurrentHour()
    {
        Calendar c = Calendar.getInstance();
        c.setTime( new Date() );
        return c.get( Calendar.HOUR_OF_DAY );
    }

    /**
     * 获得当前时间的yyyyMM
     * 
     * @return
     */
    public static String getCurrentYearMonth()
    {
        SimpleDateFormat df = new SimpleDateFormat( "yyyyMM" );
        return df.format( new Date() );
    }

    public static String getCurrentYearMonthDate()
    {
        SimpleDateFormat df = new SimpleDateFormat( "yyyyMMdd" );
        return df.format( new Date() );
    }
    
    //获取当前时间
    public static String getCurrentDate()
    {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        return df.format( new Date() );
    }

    public static String getCurrentYearMonthDate( String format )
    { // yyyy-MM-dd
        SimpleDateFormat df = new SimpleDateFormat( "format" );
        return df.format( new Date() );
    }

    public static String getTomorrowYearMonthDate()
    {
        Date date = new Date();// 取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.add( Calendar.DATE, 1 );// 明天，如果是-1则是昨天
        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" );
        return formatter.format( date );
    }
    
    public static String getYesterdayYearMonthDate()
    {
        Date date = new Date();// 取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.add( Calendar.DATE, -1 );// 明天，如果是-1则是昨天
        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
        return formatter.format( date );
    }
    //获取当前月的第一天
    public static String getDayMonthFirstDate(){
    	Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
         SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
         String firstDay = format.format(c.getTime());
    	
        
        return firstDay;
    }
    
    /**
     * 是否在24小时内
     * 
     * @param date
     * @return
     */
    public static boolean in24Hours( Date date )
    {
        return inTime( date, Hours24TimeMillis );
    }

    public static boolean inTime( Date date, long timeMillis )
    {
        Long currentTimeMillis = System.currentTimeMillis();
        if ( currentTimeMillis - date.getTime() < timeMillis )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean inToday( Date date )
    {
        return inTime( date, Hours24TimeMillis );
    }

    public static boolean isCurrentHourIn( String hours )
    {
        // 是否在关闭的时间段
        if ( null == hours )
        {
            return false;
        }

        List<Integer> ch = StringUtil.toIntArray( hours );
        if ( ch != null && ch.contains( DateTimeUtil.getCurrentHour() ) )
        {
            return true;
        }
        return false;
    }
    
    
    public static String getMmss(){
    	 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
         return df.format( new Date() );
    }
    
    public static void main(String[] args) throws ParseException {
	     
	    System.out.println( getMmss().length());
	}
}
