package net.vzhang.util;

import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static int getAge(Date birthday) {
		
		int age = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.setTime(birthday);
		
		int brithYear = calendar.get(Calendar.YEAR);
		age = year - brithYear;
		return age;
	}
}
