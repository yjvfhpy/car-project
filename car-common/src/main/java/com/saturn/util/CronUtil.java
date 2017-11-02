package com.saturn.util;

import java.util.Calendar;

public class CronUtil {
	public static boolean excute(String rule){
		Calendar calendar = Calendar.getInstance();
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String[] rules = rule.split(" ");
		boolean result = judgeTime(minute, rules[0]);
		if(!result)
			return false;
		
		result = judgeTime(hour, rules[1]);
		if(!result)
			return false;
		
		result = judgeTime(dayOfMonth, rules[2]);
		if(!result)
			return false;
		
		result = judgeTime(month, rules[3]);
		if(!result)
			return false;
		
		result = judgeTime(dayOfWeek, rules[4]);
		if(result)
			return true;
		
		if(dayOfWeek == 0){
			result = judgeTime(7, rules[4]);
			if(!result)
				return false;
		}

		return true;
	}

	private static boolean judgeTime(int currentValue, String subRule){
		if("*".equals(subRule))
			return true;
		String[] subRules = subRule.split(",");
		if(subRules != null && subRules.length > 0){
			for(String seg : subRules){
				String[] segs = seg.split("-");
				if(segs.length==1){
					if(currentValue == Integer.parseInt(segs[0]))
						return true;
				}else if(segs.length==2){
					if(currentValue >= Integer.parseInt(segs[0]) && currentValue <= Integer.parseInt(segs[1]))
						return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		 if(!CronUtil.excute("* 6-24 * * *")){
			
			System.out.println("退出。。。");
		}else{
			 System.out.println("执行");
		}
		
	}
}
