package model;
import java.util.ArrayList;

/**Autopark is class that responsible for main methods like vehicle enters,exits 
 * and holding records of AutoPark System
 * 
 * @author caner
 * @version 1.0
 */
public class AutoPark {
	private SubscribedVehicle[] subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	private double hourlyFee, incomeDaily;
	private int capacity, currentVehicle, lastSub,subVehicleSize;

	
	/**Class constructor specifying capacity and hourly fee of Autopark System to create
	 * 
	 * @param hourlyFee
	 * @param capacity
	 */
	public AutoPark(double hourlyFee, int capacity) {
		incomeDaily = 0; lastSub = 0; currentVehicle = 0; subVehicleSize =10;
		parkRecords = new ArrayList<ParkRecord>();
		subscribedVehicles = new SubscribedVehicle[subVehicleSize];  
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;	
	}
	/**Finds the subscribed vehicle to which the given plate belongs
	 * 
	 * @param plate of the subscribed vehicle to be searched
	 * @return If plate belongs to a subsricbed vehicle, returns SubscribedVehicle object ,else returns null
	 */
	public SubscribedVehicle searchVehicle(String plate) {
		int i;
		for(i=0; i<lastSub ; i++)
				if(subscribedVehicles[i].getPlate().equals(plate))
					return subscribedVehicles[i];
		return null;		
	}
	/**Searches the vehicle to which the given plate belongs
	 * 
	 * @param plate
	 * @return If plate belongs to a subsricbed vehicle, returns Vehicle object ,else returns null
	 */
	public boolean isParked(String plate) {
		for( ParkRecord aRecord : parkRecords)
			if(aRecord.getVehicle().getPlate().equals(plate) && !aRecord.isExit())
				return true;
		return false;	
	}
	/**Enlarges the subscribedVehicles array by current subVehicleSize 
	 * 
	 */
	private void enlargeVehicleArray() {
		subVehicleSize *= 2;
		SubscribedVehicle tmp[] = new SubscribedVehicle [subVehicleSize];
		for(int  i = 0; i<lastSub ; i++)
			tmp[i] = subscribedVehicles[i];
		subscribedVehicles = tmp;
		
	}
	
	/**adds the given subscribed vehichle to subscribed vehicles array
	 * 
	 * @param newVehicle
	 * @return if subscription is accepts return true ,else return false
	 */
	public boolean addVehicle(SubscribedVehicle newVehicle ) {
		//weather checks the vehicle has already susbcribed
			if (searchVehicle(newVehicle.getPlate() ) == null) {				
				
				if(lastSub >= subVehicleSize) //if array is full
					enlargeVehicleArray();
				subscribedVehicles[lastSub] = newVehicle;
				lastSub++;
				return true;
			}	
		return false;
	}
	/** Handles vehicle entry.
	 * A vehicle that has already parked cannot enter again.
	 * When a vehicle enters to the park, its plate is searched within the subscribed vehicles array.
	 * If such vehicle is found, the parking record that is to be created and to be added to the array is associated
	 * with that vehicle.
	 * Otherwise, this means that the vehicle is either a regular vehicle or an official vehicle.
	 * In this case, such a vehicle will be created and associated with the parking record.
	 * 
	 * @param plate
	 * @param enter
	 * @param isOfficial
	 * @return if the vehicle has already parked or no parking space available returns false ,else return true
	 */
	public boolean vehicleEnters(String plate , Time enter , boolean isOfficial ) {

		for(ParkRecord aRecord : parkRecords)
			if(aRecord.getVehicle().getPlate().equals(plate) && !aRecord.isExit() ) {		 	
				System.out.println("The vehicle has already parked");
				return false;
			}
		if( currentVehicle  < capacity ) { //if there is free space in the auto park
			ParkRecord newRecord ;
			SubscribedVehicle subVehicle = searchVehicle(plate);  //searches if the vehicle has a subscription

			if( subVehicle != null && subVehicle.getSubscription().isValid()) 
				//subscribed vehicle
					newRecord= new ParkRecord(enter , (SubscribedVehicle)subVehicle);				
			else if( isOfficial == true) 
				//official vehicle
				newRecord = new ParkRecord(enter , new OfficalVehicle(plate));
			else 
				//regular vehicle
				newRecord = new ParkRecord(enter , new RegularVehicle(plate));				
			
			currentVehicle++;
			parkRecords.add(newRecord);
			return true;
			}	
		System.out.println("full capacity");
		return false;	
	}
	/**This method determines whether the vehicle with the given plate, which is exiting the
	 * park, will pay a parking fee or not.If so, the fee to be paid is the multiplication of parking duration and
	 * the hourly fee. The parking fee is added to the member incomeDaily
	 * Fee is calculated discretely: A regular vehicle that has parked for 55 minutes pays nothing 
	 * but one that has parked for 1 hours and 35 minutes pays only fee for one hour 
	 * @param plate
	 * @param exit
	 * @return 
	 */
	public boolean vehicleExits(String plate , Time exit) {
	//searches park records
	for(ParkRecord aRecord : parkRecords)
		if(aRecord.getVehicle().getPlate().equals(plate) && !aRecord.isExit() ) {

			if(exit.getDifference(aRecord.getEnterTime()) < 0) //if exit time before than enter time
				return false;			
			aRecord.setExitTime(exit);
			//if vehicle is regular
			if(aRecord.getVehicle().isSpecial() == false ){
				//then regular
				double fee = hourlyFee * (int)(aRecord.getParkingDuration() / 60);  
				incomeDaily += fee;		
			}		
			currentVehicle--;
			//parkRecords.remove(aRecord);
			return true;
		}
		return false; //vehicle could not found
	}
	public int getCurrentVehicleSize() {
		return currentVehicle;
	}
	public double getHourlyFee() {
		return hourlyFee;
	}
	public double getIncomeDaily() {
		return incomeDaily;
	}
	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		String info = "";
		
		info += "\nSubscribed Vehicles\n";
		for(int i=0 ; i<lastSub ;i++)
			if(subscribedVehicles[i].getSubscription().isValid())
				info += subscribedVehicles[i] + "\n";
		
		info += "\nParked Vehicles\n";
		for (ParkRecord aRecord : parkRecords )
			if(!aRecord.isExit()) 
				info += aRecord + "\n";
		
		info += "\nPark Records\n";
		for (ParkRecord aRecord : parkRecords )
				info += aRecord + "\n";
		return info;
	}
	
	
}	