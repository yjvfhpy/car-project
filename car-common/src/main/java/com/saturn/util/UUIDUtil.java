package com.saturn.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class UUIDUtil
{
    /**
     * 获取随机uuid
     * 
     * @return
     */
    public static String getRandomUUID()
    {
        //UUID uuid = UUID.randomUUID();
        String uuid =UUID.randomUUID().toString().trim().replaceAll("-", "").toUpperCase();
        return uuid;
    }

    /**
     * 根据高位低位生成uuid
     * 
     * @param mostSigBits
     * @param leastSigBits
     * @return
     */
    public static String getUUID( long mostSigBits, long leastSigBits )
    {
        UUID uuid = new UUID( mostSigBits, leastSigBits );
        //return uuid.toString().replace( "-", "" );
        return uuid.toString().trim().replaceAll("-", "").toUpperCase();
    }
    
    /**
     * 生成4位随机数，带字符和数字
     * @return
     */
    public static String generateWord() {  
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",  
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };  
        List<?> list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
       }  
        String afterShuffle = sb.toString();  
       String result = afterShuffle.substring(5, 9);  
       return result;  
    }
    
    /**
     * 生成5位随机数，带字符和数字
     * @return
     */
    public static String getWord5() {  
        String[] beforeShuffle = new String[] { "1","2", "3", "4", "5", "6", "7",  
                "8", "9","0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };  
        List<?> list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
       }  
        String afterShuffle = sb.toString();  
       String result = afterShuffle.substring(5, 10);  
       return result;  
    }
    
    /**
     * 根据传入num判断产生几位随机数（纯数字）
     * @param num
     * @return
     */
    public static String getRandomNum( int num )
    {
        String[] digits = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
        Random rnum = new Random( new Date().getTime() );
        for ( int i = 0; i < digits.length; i++ )
        {
            int index = Math.abs( rnum.nextInt() ) % 10;
            String tmpDigit = digits[index];
            digits[index] = digits[i];
            digits[i] = tmpDigit;
        }
        String returnStr = digits[0];
        for ( int i = 1; i < num; i++ )
        {
            returnStr = digits[i] + returnStr;
        }
        return returnStr;
    }
    
    /**
     * 根据传入num判断产生几位随机数（纯数字）
     * @param num
     * @return
     */
    public static String getRandomNum17( int num )
    {
        String[] digits = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "10", "11", "12", "13", "14", "15", "16", "17" };
        Random rnum = new Random( new Date().getTime() );
        for ( int i = 0; i < digits.length; i++ )
        {
            int index = Math.abs( rnum.nextInt() ) % 10;
            String tmpDigit = digits[index];
            digits[index] = digits[i];
            digits[i] = tmpDigit;
        }
        String returnStr = digits[0];
        for ( int i = 1; i < num; i++ )
        {
            returnStr = digits[i] + returnStr;
        }
        return returnStr;
    }
    
    //随机获取名字
    public static String getRandomName(int length){
		 String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
	        Random random = new Random();   
	        StringBuffer sb = new StringBuffer();   
	        for (int i = 0; i < length; i++) {   
	            int number = random.nextInt(base.length());   
	            sb.append(base.charAt(number));   
	        }   
	        return sb.toString();
	}
    
    public static void main(String[] args) {
		System.out.println(getRandomNum(6));
	}

}
