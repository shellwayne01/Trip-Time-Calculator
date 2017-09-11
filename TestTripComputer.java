package triptimepackage;
import javax.swing.JFrame;

public class TestTripComputer extends JFrame {
	public TestTripComputer(){
		TripComputer myTC = new TripComputer(); 
		add(myTC); 
	}
	
	public static void main(String[]args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				TestTripComputer guiWindow = new TestTripComputer(); 
				guiWindow.setSize(600,500);   
				guiWindow.setVisible(true); 
				guiWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
			}
		});
	}
}
