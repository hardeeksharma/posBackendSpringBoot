package com.pawan.pos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParse {

	public static String parseDate(String Date) {

		String[] temp = Date.split("-");
		StringBuilder builder = new StringBuilder();
		builder.append(temp[2]);
		builder.append("-");
		builder.append(temp[1]);
		builder.append("-");
		builder.append(temp[0]);
		return builder.toString();
	}

	public static Date dateParse(String date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date d1 = null;
		d1 = sdf.parse(date);
		System.out.println(d1);
		return d1;
	}

}
