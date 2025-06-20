import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("First Log");
        Logger logger2 = Logger.getInstance();
        logger2.log("Second Log");

        if (logger1 == logger2) {
            System.out.println("Both logger instances are the same.");
        } else {
            System.out.println("Different logger instances exist.");
        }

        // Practical Exampple JDBC
        // Normal code sinppet when using a JDBC
        try {
            String url = "";
            String user = "";
            String pass = "";
            Connection normalConnection = DriverManager.getConnection(url, user, pass);
            Logger.getInstance().log("Connected with normal JDBC manually.");
            normalConnection.close();
        } catch (Exception e) {
        }

        // This comes handy when we have multiple DAO but we need to create connection
        // each time
        // Why am I writing the same thing again and again? This should be centralized -
        // the intution

        /*
         * When to use a singleton
         * Is this being repeated in multiple places?
         * Do I need only one instance of this across the app?
         * Do I want to centralize access and prevent duplication?
         */

        // when using the Singleton we are creating a seperate class that only do one
        // job connecting the DB
        // DBConnectionManager
        try {
            Connection conn = DBConnectionManager.getInstance().getConnection();
            Logger.getInstance().log("Connected using Singleton DBConnectionManager.");
            conn.close();
        } catch (Exception e) {
        }

    }
}