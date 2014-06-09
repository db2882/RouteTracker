import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Route {
	private int IDCounter = 0;
	
	public void updateGrade(Connection conn, int routeId, int grade) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET GRADE=" + grade + " WHERE ID=" + routeId;
		executeUpdate(conn, createString);
	}

	public void updateColor(Connection conn, int routeId, String color) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET COLOR ='" + color + "' WHERE ID=" + routeId;
		executeUpdate(conn, createString);
	}

	public void updateLocation(Connection conn, int routeId, String location) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET LOCATION='" + location + "' WHERE ID=" + routeId;
		executeUpdate(conn, createString);
	}

	public void addRoute(Connection conn,int routeId, String name, String color, int grade, String location) {
		String createString = "INSERT INTO " + this.tableName + 
				" value ('" + routeId + "','" + name + "','" + color +
				"','" + location + "'," + grade +");";
		try {
			executeUpdate(conn, createString);
		} catch (SQLException e) {
			System.out.println("Error in adding route");
		}
		IDCounter++;
	}

	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Add element
		try {
			addRoute(conn,5,"lunar landing","purple",10,"gnarnia");
			updateColor(conn,3,"red");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Connect to the DB and do some stuff
	 */
	
}
