import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Datasource {

    private static final String DB_NAME = "sbot";
    private static final String DB_PASSWORD = "1@wer57OreX78!";
    private static final String TABLE_QUOTESTORAGE = "quotestorage";

    private static final String TIMESTAMP = "timestamp";
    private static final String CURRENCY_PAIR = "currency_pair";

    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/quotes-local";

    public static final String INSERT_QUOTES = "INSERT INTO " + TABLE_QUOTESTORAGE +
            '(' + TIMESTAMP + ", " + CURRENCY_PAIR + ") VALUES(?, ?)";

    public void dbConnector() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager
                    .getConnection(CONNECTION_STRING,
                            DB_NAME, DB_PASSWORD);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();

            String sql = "INSERT INTO quotestorage (timestamp,currency_pair,last_price,lowest_ask,highest_bid,percent_change,base_volume,quote_volume,day_high,day_low,frozen)" +
                    "VALUES (1499282552767, 'BTC_LTC', 0.01998305, 0.01998305,0.01997858,0.0088083,32544.83575948,1591514.17944638,0.02165049,0.0191,false)";

//            String sql = "CREATE TABLE if NOT EXISTS quotestorage " +
//                    "(id SERIAL PRIMARY KEY, " +
//                    " TIMESTAMP BIGINT, " +
//                    " currency_pair VARCHAR(255), " +
//                    " last_price DECIMAL, " +
//                    " lowest_ask DECIMAL, " +
//                    " highest_bid DECIMAL, " +
//                    " percent_change DECIMAL, " +
//                    " base_volume DECIMAL, " +
//                    " quote_volume DECIMAL, " +
//                    " day_high DECIMAL, " +
//                    " day_low DECIMAL, " +
//                    " frozen BOOL)";

            stmt.executeUpdate(sql);


            System.out.println("Created table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
