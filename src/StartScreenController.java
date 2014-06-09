import java.awt.EventQueue;
import java.sql.SQLException;


public class StartScreenController {
	private StartScreen view;
	private StartScreenModel model;
	private DatabaseConnector connector;

	StartScreenController(StartScreenModel mod, StartScreen screen){
		model = mod;
		view = screen;
		connector = new DatabaseConnector("localhost","","root","david_data");
		try {
			model.setListModel(connector.getLocations(connector.getConnection()));
		} catch (SQLException e) {
			System.out.println("Error Establishing Database Connection");
			e.printStackTrace();
		}
	}
	
	

}
