package application;
import model.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**MenuController is a controller class that is associated with Menu.fxml XML based user-interface file
 *All button actions occur in this controller
 *This class also handles faulty user inputs , gets error messages  
 * 
 * @author caner
 *
 */
public class MenuController  implements Initializable {
    @FXML
    private Label cash;
    @FXML
    private TextField hour1;

    @FXML
    private TextField plate2;

    @FXML
    private TextField hour2;

    @FXML
    private TextField plate3;

    @FXML
    private TextField minute1;

    @FXML
    private TextField plate1;

    @FXML
    private Button enterButton;

    @FXML
    private DatePicker endDatepicker;

    @FXML
    private TextField minute2;

    @FXML
    private CheckBox isOfficial;

    @FXML
    private DatePicker beginDatepicker;

    @FXML
    private Button exitButton;

    @FXML
    private Button subscribeButton;
    
    @FXML
    private Label status1;
    
    @FXML
    private Label status2;
    
    @FXML
    private Label status3;

    @FXML
    private TextArea recordList;
    
    @FXML
    private Label parkedVehicles;

    @FXML
    private Label hourlyFee;
    
    @FXML
    private Label capacity;

    @FXML
    private ProgressBar density;
    
    @FXML
    private TextField cap;
    @FXML
    private TextField fee;
    @FXML
    private Button setButton;
    
    @FXML
    private Pane enterance;

    AutoPark myAutoPark;
    public void setAutoPark() {
    
   
    }
    @FXML
    public void setAutoPark(ActionEvent e) {
    	 myAutoPark = new AutoPark(Double.parseDouble( fee.getText() ),Integer.parseInt( cap.getText() ));
    	enterance.toBack();
		refresh();
    }
     
    private void refresh() {
    	
    	capacity.setText("Capacity :"+ myAutoPark.getCapacity() );
    	hourlyFee.setText("Hourly Fee : "+myAutoPark.getHourlyFee() ); 	
    	parkedVehicles.setText("Parked Vehicles : " + myAutoPark.getCurrentVehicleSize());
    	cash.setText("Cash : " + myAutoPark.getIncomeDaily());
    	
    	density.setProgress((double) myAutoPark.getCurrentVehicleSize() / myAutoPark.getCapacity() );
    	recordList.setText(myAutoPark.toString());
    	
    	//clears text fields
    	plate1.setText("");
    	plate2.setText("");
    	plate3.setText("");
    	hour1.setText("");
    	hour2.setText("");
    	minute1.setText("");
    	minute2.setText("");
    	
    	//sets value of Datepicker today
		beginDatepicker.setValue(LocalDate.now());
		endDatepicker.setValue(LocalDate.now());
    }
    
    @FXML
	 void vehicleEnters(ActionEvent event) {
		System.out.println("girdi");
		String plate = plate1.getText();

		if(!plate.equals("") && !hour1.getText().equals("") && !minute1.getText().equals("") ) {
			int hour = Integer.parseInt(hour1.getText()) ;
			int minute = Integer.parseInt(minute1.getText()) ;
			if(hour >=0 && hour <= 24)
				if(minute >=0 && minute <=60) {
					Time enterTime = new Time(hour,minute);
					if( myAutoPark.vehicleEnters(plate, enterTime,  isOfficial.isSelected()) ) {
					status1.setText("Vehicle Entery Successful");
					refresh();
					}
					else 
						status1.setText("The vehicle has already parked");
				}
				else
					status1.setText("the minute must range from  to 0 to 60");
			else
				status1.setText("the hour must range from  to 0 to 60");
		}
		else
			status1.setText("Fields can not be empty ");
	}
	
	@FXML
	void vehicleExits(ActionEvent event) {
		String plate = plate2.getText();
		//checking if the fields are empty
		if(!plate.equals("") && !hour2.getText().equals("") && !minute2.getText().equals("") ) {
			int hour = Integer.parseInt(hour2.getText()) ;
			int minute = Integer.parseInt(minute2.getText()) ;
			if(hour >=0 && hour <= 24)
				if(minute >=0 && minute <=60) {
					
					if ( myAutoPark.vehicleExits( plate ,new Time(hour , minute)) ) 
						status2.setText("Vehicle Exit successful");
					else
						status2.setText("Vehicle could not be found");
					refresh();
				}
				else
					status2.setText("the minute must range from  to 0 to 60");
			else
				status2.setText("the hour must range from  to 0 to 60");
		}
		else
			status2.setText("Fields can not be empty ");
	}
	
	@FXML	
	public void subscribe(ActionEvent event) {
		String plate = plate3.getText(); //getting the plate from the textfield
		//getting the date values from the datepicker on the menu
		LocalDate date1 = beginDatepicker.getValue();
		LocalDate date2 = endDatepicker.getValue();
		
		
		if(date1 != null &&  date2 != null && !plate.equals("")) {
			//creating date objects
			Date begin = new Date( date1.getDayOfMonth(),  date1.getMonthValue() ,date1.getYear() );
			Date end = new Date( date2.getDayOfMonth(),  date2.getMonthValue() ,date2.getYear() );	
			if(begin.isBeforeThan(end)) {
				Subscription newSub = new Subscription(begin, end, plate); //creates new subscription
				if( myAutoPark.addVehicle(newSub.getVehicle()) ) 
					status3.setText("Subscribe is succesful");
				else
					status3.setText("failed");
				refresh();
			}
			else
				status3.setText("Begin date can not be after than end date");
		}
		else
			status3.setText( "Fields can not be empty");
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		
	
	}
	

}
