import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Route {
	private int IDCounter = 0;
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "david_data";

	/** The name of the table we are testing with */
	private final String tableName = "Routes";

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) { stmt.close(); }
		}
	}

	public void updateGrade(Connection conn, int routeId, int grade) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET GRADE=" + grade + " WHERE ID=" + routeId;
		this.executeUpdate(conn, createString);
	}

	public void updateColor(Connection conn, int routeId, String color) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET COLOR ='" + color + "' WHERE ID=" + routeId;
		this.executeUpdate(conn, createString);
	}

	public void updateLocation(Connection conn, int routeId, String location) throws SQLException{
		String createString = "UPDATE " + this.tableName + " SET LOCATION='" + location + "' WHERE ID=" + routeId;
		this.executeUpdate(conn, createString);
	}

	public void addRoute(Connection conn,int routeId, String name, String color, int grade, String location) {
		String createString = "INSERT INTO " + this.tableName + 
				" value ('" + routeId + "','" + name + "','" + color +
				"','" + location + "'," + grade +");";
		try {
			this.executeUpdate(conn, createString);
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
	public static void main(String[] args) {
		Route app = new Route();
		app.run();
	}
}
