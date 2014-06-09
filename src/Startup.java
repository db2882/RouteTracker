import java.awt.EventQueue;


public class Startup {

	public static void main(String[] args) {
		final StartScreenModel model = new StartScreenModel();
		
		final StartScreen window = new StartScreen(model);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
		StartScreenController controller = new StartScreenController(model, window);

	}
	
	

}
