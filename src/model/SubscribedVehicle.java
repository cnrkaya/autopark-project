package model;
/**Represent a Subscribed Vehicle
 * @author caner
 */
public class SubscribedVehicle implements Vehicle {
	private String plate;
	private Subscription subscription;
	
	
	/**
	 * Constructor 
	 * @param plate     
	 * @param subscription  
	 */
	public SubscribedVehicle(String plate, Subscription subscription) {
		this.plate = plate;
		this.subscription = subscription;
	}

	/**
	*returns plate of the vehicle
	*@return plate in string
	*@Override
	*/
	public String getPlate() {
		return plate;
	}	

	
	/**
	 * @return subscription
	 */
	public Subscription getSubscription() {
		return subscription;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public String toString() {
		return " | SubscribedVehicle | plate: " + plate  + subscription;
	}	

	
}
