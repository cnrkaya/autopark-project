package model;

/**Represents a Subscription 
 * 
 * @author caner
 */
public class Subscription {
	public Date begin,end;
	public SubscribedVehicle vehicle;
	
	/**When constructing a Subscription , a SubscribedVehicle object is constructed 
	 * with given plate and this object in Constructor of Subscription 
	 * @param begin
	 * @param end
	 * @param plate
	 */
	public Subscription(Date begin, Date end, String plate) {
		this.begin = begin;
		this.end = end;
		vehicle = new SubscribedVehicle(plate , this); 
	}
	/**checks whether the subscripton has expired or has not yet started 
	 * 
	 * @return if the subscription is valid ,returns true else returns false
	 */
	public boolean isValid() {
		if( begin.isBeforeThan( Date.getToday() ) ||  begin.isEqualsWith( Date.getToday() ) )  
				if( end.isAfterThan( Date.getToday() ) || end.isEqualsWith( Date.getToday() )  ) 
					return true;
		return false;
	}
	
	public SubscribedVehicle getVehicle() {
		return vehicle;
	}

	@Override
	public String toString() {
		return " | Subscription begin: " + begin + " | end: " + end  ;
	}
	
	
}
