import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static DBConnectionManager instance;
    private Connection connection;

    private final String jdbcUrl = "";
    private final String username = "";
    private final String password = "";

    private DBConnectionManager() throws SQLException {
        // sample snippet without any excception handling
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    public static synchronized DBConnectionManager getInstance() throws SQLException {
        if(instance == null || instance.getConnection().isClosed()){
            instance = new DBConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
