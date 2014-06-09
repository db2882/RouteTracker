import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class DatabaseConnector {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private String userName;

	/** The password for the MySQL account (or empty for anonymous) */
	private String password;

	/** The name of the computer running MySQL */
	private String serverName; 

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private String dbName;

	/** The name of the table we are testing with */
	private String tableName;

	DatabaseConnector(String servName, String pswrd, String usrName, String database){
		serverName = servName;
		password = pswrd;
		userName = usrName;
		dbName = database;
	}

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

	public ArrayList<String> getLocations(Connection conn) throws SQLException{
		ArrayList<String> locations = new ArrayList<String>();

		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
		  locations.add(rs.getString(3));
		}
		return locations;
	}

	public static void main(String[] args) {
		DatabaseConnector connect = new DatabaseConnector("localhost","","root","david_data");
		try {
			Connection conn = connect.getConnection();
			ArrayList<String> loc = connect.getLocations(conn);
			System.out.println(loc);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
