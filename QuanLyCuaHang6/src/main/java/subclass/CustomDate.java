package subclass;

import java.sql.Date;
import java.time.LocalDate;

public class CustomDate {
	private Integer day;
	private Integer month;
	private Integer year;

	public CustomDate(Integer year, Integer month, Integer day) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public static boolean isValidDate(CustomDate d) {
		if(d == null) {return false;}
		if (d.getDay() == null || d.getMonth() == null || d.getYear() == null) {
			return false;
		}
		try {
			Date.valueOf(d.year + "-" + d.month + "-" + d.day);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Date toSQLDate() {
		if (CustomDate.isValidDate(this)) {
			return Date.valueOf(this.getYear() + "-" + this.getMonth() + "-" + this.getDay());
		}
		return null;
	}

	public static CustomDate of(int year, int month, int day) {
		CustomDate d = new CustomDate(year, month, day);
		if (CustomDate.isValidDate(d)) {
			return d;
		} else {
			return null;
		}
	}
	
	public static CustomDate of(Integer year, Integer month, Integer day) {
		CustomDate d = new CustomDate(year, month, day);
		if (CustomDate.isValidDate(d)) {
			return d;
		} else {
			return null;
		}
	}

	public static CustomDate of(Date d) {
		LocalDate ld = d.toLocalDate();
		CustomDate cd = new CustomDate(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
		return cd;
	}
	
	public static CustomDate searchDate(Integer year, Integer month, Integer day) {
		CustomDate cd = new CustomDate(year, month, day);
		return cd;
	}
	
	public static CustomDate of(String d) {
		if(d.matches("[0-9/]{7,10}")) {
			String[] arr = d.split("/");
			CustomDate cd = new CustomDate(Integer.parseInt(arr[2]), 
								Integer.parseInt(arr[1]), 
								Integer.parseInt(arr[0]));
			if(CustomDate.isValidDate(cd)) {
				return cd;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
	}

	public Integer getDay() {
		return day;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}

}
