package com.insurer.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CommonUtility {

	
	public  LocalDate getLocalDate(Date date) {
		LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localdate;
	}
	
	public  Date getDate(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	public Date getDateFromString(String format,String input_date) {
		Date date=new Date();;
		try {
			date = new SimpleDateFormat(format).parse(input_date);
		} catch (ParseException e) {
			System.out.println("Date parse error occured");
		}//"yyyy-MM-dd"
		return date;
	}
	
	public String getStringFromDate(String format,Date date) {
		String dateInFormat="";		
		dateInFormat = new SimpleDateFormat(format).format(date);
		return dateInFormat;
	}
}
