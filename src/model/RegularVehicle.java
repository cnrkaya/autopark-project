package model;
/**Represents regular vehicle which is type of vehicle
 * 
 * @author caner
 *
 */
public class RegularVehicle implements Vehicle{
	private String plate;
	
	/**Constructor
	 * 
	 * @param plate
	 */
	public RegularVehicle(String plate) {
		this.plate = plate;
	}

	@Override
	public String getPlate() {
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		return null;
	}

	@Override
	public boolean isSpecial() {
		return false;
	}

	@Override
	public String toString() {
		return " | RegularVehicle | plate: " + plate;
	}
	
	

}
