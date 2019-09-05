package model;

import java.text.SimpleDateFormat;
/**Represents a Date with day,month,year 
 * 
 * @author caner
 */
public class Date {
	
	private int day,month,year;

	/**Constructs a Date class with given parameters 
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public Date(int day, int month, int year) {

		this.day = day;
		this.month = month;
		this.year = year;
	}
	/**Checks whether the date is after than an other date 
	 * years and months are calculated in days and difference are taken  
	 * the order of the difference is the :date object calling method - other date (given parameter) 
	 * @param other
	 * @return if the date object is after than the other returns true , else returns false 
	 */
	public boolean isAfterThan(Date other) {
		int difference = (year * 365 + month * 30 + day) - (other.getYear() * 365 + other.getMonth() * 30 + other.getDay() );
	 	if(difference > 0)
			return true; 
		return false;
	}
	/**Checks whether the date is before than an other date 
	 * 
	 * 
	 * @param other
	 * @return  if the date object is before than the other returns true, else returns false 
	 */
	public boolean isBeforeThan(Date other) {
		if( year < other.getYear() )
			return true;
		if( year > other.getYear() )
			return false;
		if( month < other.getMonth() )
			return true;
		if( month > other.getMonth())
			return false;
		if( day < other.day)
			return true;
		else
			return false;
	}
	/**Checks whether the date equals to an other date 
	 * 
	 * @param other
	 * @return if the date object is equals to the other returns true , else returns false 
	 */
	public boolean isEqualsWith(Date other) {
		if( year == other.getYear() && month == other.getMonth() && day == other.getDay() )
			return true; 
		return false;		
	}
	/** pulls today's date from java.util.Date class in day-month-year format
	 * 
	 * @return today
	 */
	public static Date getToday() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String[] news = sdf.format(date).split("-");
		Date today = new Date( Integer.parseInt(news[0]), Integer.parseInt(news[1]) ,Integer.parseInt(news[2]) );
		return today;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	@Override
	public String toString() {
		return  day +"/" + month + "/" + year ;
	}
}
