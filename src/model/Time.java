package model;
/**Represents a time with hour and minute
 * 
 * @author caner
 *
 */
public class Time {
	int hour , minute;
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	/**takes the difference with other time
	 * 
	 * @param other
	 * @return the absolute value of time object difference with other given time object
	 */
	public int getDifference(Time other) {
		int minDif , hourDif;

			 minDif = minute- other.minute;
			 hourDif = hour - other.hour;
		//if the difference of minutes has negative value
		if(minDif < 0) { 
			minDif += 60;
			hourDif --;
		}
			
		return hourDif * 60 + minDif;  
	}

	@Override
	public String toString() {
		return hour + ":" + minute;
	}
}
