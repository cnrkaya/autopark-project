package model;
/**Represents offical vehicle which is type of vehicle
 * 
 * @author caner
 *
 */
public class OfficalVehicle implements Vehicle{
	private String plate;

	/**Constructor 
	 * 
	 * @param plate
	 */
	public OfficalVehicle(String plate) {
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
		return true;
	}

	@Override
	public String toString() {
		return " | OfficalVehicle | plate: " + plate ;
	}
	
	
	

}
