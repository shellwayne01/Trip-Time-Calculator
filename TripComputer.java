package triptimepackage;
import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TripComputer extends JPanel implements ActionListener {
	private double totalTime = 0.0; 	
	private boolean restStopTaken = false; 

	private JTextField distance;
	private JTextField speed;
	private JButton addLeg;
	private JTextField restTime;
	private JButton addRest;
	private JLabel result;

	public TripComputer(){
		setLayout(new GridLayout(7, 1));
		//setLayout(new FlowLayout());
		distance = new JTextField("Enter distance (mi)");
		speed = new JTextField("Enter speed (mph)"); //in miles/hour
		addLeg = new JButton("Add Leg");
		restTime = new JTextField("Enter rest time (hr)");
		addRest = new JButton("Add Rest Stop"); //in hours
		result = new JLabel("              Total Travel Time : ");
		
		add(distance);
		add(speed);
		add(addLeg);
		add(restTime);
		add(addRest); 
		add(result);
		
		addLeg.addActionListener(this);
		addRest.addActionListener(this);
	}
	
	private static double getDoubleFromTF(JTextField jtf) throws Exception{
		return Double.parseDouble(jtf.getText().trim()); 
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnClicked = e.getActionCommand().trim();
		double num1 = 0, num2 = 0 , num3 = 0; 
		
	try{
		switch (btnClicked) {
			case "Add Leg":
				num1 = getDoubleFromTF(distance); //gets the double from the t.f
				num2 = getDoubleFromTF(speed);
				ComputeLegTime(num1, num2); 
				break; 
			case "Add Rest Stop":	
				num3 = getDoubleFromTF(restTime);
					takeRestStop(num3);
				break;
			}
			result.setText("              Total Travel Time: " + totalTime + " Hours.");
			System.out.println("distance: " + num1 + "\nspeed: "+ num2 +"\nrestTime: "+ num3); //To check the values stored.
			System.out.println(restStopTaken);	
		}
		catch(Exception ex){
			result.setText("Something went wrong. "+ ex.getMessage());
		}
	} 
	
		
	public double ComputeLegTime (double distance, double speed) throws Exception{
		if ((distance < 0 ) || (speed < 0)){
			throw new Exception("Distance and speed must be greater than zero."); 
		}else{
		totalTime += distance/speed; 
		restStopTaken = false; 
		}
		return totalTime; 
	}
	
	public void takeRestStop (double time) throws Exception{
		if (time < 0){
			throw new Exception("Time must be greater than zero."); 
		}else if(restStopTaken){ //rest stop was already taken
			throw new Exception("Two consecutive rest stops are illegal.");
		}else{
		restStopTaken = true; 
		totalTime += time; 
		}
	}
	
	public double getTripTime(){ 
		return totalTime; 
	}
}
