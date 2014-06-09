import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;


public class StartScreen {
	private StartScreenModel model;
	public JFrame frame;

	
	/**
	 * Create the application.
	 */
	public StartScreen(StartScreenModel mod) {
		model = mod;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToRoutetracker = new JLabel("Welcome To RouteTracker");
		lblWelcomeToRoutetracker.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblWelcomeToRoutetracker.setBounds(290, 0, 199, 27);
		frame.getContentPane().add(lblWelcomeToRoutetracker);
		
		JList databaseList = new JList(model.getListModel());
		databaseList.setBounds(290, 49, 199, 413);
		frame.getContentPane().add(databaseList);
		
		JButton btnLocation = new JButton("Location");
		btnLocation.setBounds(311, 474, 158, 29);
		frame.getContentPane().add(btnLocation);
		
		JButton btnNewLocation = new JButton("New Location");
		btnNewLocation.setBounds(311, 515, 158, 29);
		frame.getContentPane().add(btnNewLocation);
	}
}
