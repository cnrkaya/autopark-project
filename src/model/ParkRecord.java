package model;
/**Represents Park Records of Vehicles entering and leaving
 * 
 * @author caner
 *
 */
public class ParkRecord {
	private Time enterTime, exitTime;
	private Vehicle vehicle;
	public ParkRecord(Time enterTime, Vehicle vehicle) {
	 	this.enterTime = enterTime;
		this.vehicle = vehicle;

	}
	/** gives the parking duration of the vehicle associated with the parking record
	 * 
	 * @return parking duration : exit time - enter time
	 */
	public int getParkingDuration() {
		return exitTime.getDifference(enterTime);
	}
	public void setExitTime(Time time) {
		exitTime = time;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public Time getEnterTime() {
		return enterTime;
	}
	@Override
	public String toString() {
		return "enterTime: " + enterTime + " | exitTime: " + exitTime +vehicle ;
	}
	public  boolean isExit() {
		if(exitTime == null)
			return false;
		else
			return true;
	}
	
	
	
}
