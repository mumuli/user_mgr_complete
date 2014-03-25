package net.vzhang.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class UserBirthdayTypeconverter extends StrutsTypeConverter {
	
	private DateFormat format;
	
	public UserBirthdayTypeconverter() {
		// TODO Auto-generated constructor stub
		format = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’");
	}
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		// TODO Auto-generated method stub
		Date date = null;
		try {
			date = format.parse(values[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		String str = null;
		if(o instanceof Date) {
			Date date = (Date)o;
			str = format.format(date);
		}
		return str;
	}

}
