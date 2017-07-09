import java.sql.*;

public class Datasource {

    private Connection conn;

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_NAME = "sbot";
    private static final String DB_PASSWORD = "1@wer57OreX78!";
    private static final String TABLE_QUOTESTORAGE = "quotestorage";

    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/quotes-local";

    String INSERT_QUOTES = "INSERT INTO " + TABLE_QUOTESTORAGE
            + "(timestamp,currency_pair,last_price,lowest_ask,highest_bid,percent_change,base_volume,quote_volume,day_high,day_low,frozen) VALUES"
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    PreparedStatement preparedStatementInsert = null;

    public void insertQuotes(PoloniexMapping pm) {

        try {
            preparedStatementInsert = conn.prepareStatement(INSERT_QUOTES);
            preparedStatementInsert.setLong(1, pm.getTimeStamp());
            preparedStatementInsert.setString(2, pm.getCurrencyPair());
            preparedStatementInsert.setBigDecimal(3, pm.getLast());
            preparedStatementInsert.setBigDecimal(4, pm.getLowestAsk());
            preparedStatementInsert.setBigDecimal(5, pm.getHighestBid());
            preparedStatementInsert.setBigDecimal(6, pm.getPercentChange());
            preparedStatementInsert.setBigDecimal(7, pm.getBaseVolume());
            preparedStatementInsert.setBigDecimal(8, pm.getQuoteVolume());
            preparedStatementInsert.setBigDecimal(9, pm.getDayHigh());
            preparedStatementInsert.setBigDecimal(10, pm.getDayLow());
            preparedStatementInsert.setBoolean(11, pm.isFrozen());
            int affectedRows = preparedStatementInsert.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("Quotes insert failed");
            }

        } catch (Exception e) {
            System.out.println("Insert quotes exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        }
    }

    public boolean openConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connecting database " + DB_NAME);
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, DB_NAME, DB_PASSWORD);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
        System.out.println("Connected database successfully...");
        return true;

    }

    public void closeConnection() {
        try {

            if (preparedStatementInsert != null) {
                preparedStatementInsert.close();
            }

            if (conn != null) {
                conn.close();
                System.out.println("Connection to database successfully closed");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }

   /* public void dbConnector() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DB_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager
                    .getConnection(CONNECTION_STRING,
                            DB_NAME, DB_PASSWORD);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();

//            String sql = "INSERT INTO quotestorage (timestamp,currency_pair,last_price,lowest_ask,highest_bid,percent_change,base_volume,quote_volume,day_high,day_low,frozen)" +
//                    "VALUES (1499282552767, 'BTC_LTC', 0.01998305, 0.01998305,0.01997858,0.0088083,32544.83575948,1591514.17944638,0.02165049,0.0191,false)";

            String sql = "CREATE TABLE if NOT EXISTS quotestorage " +
                    "(id SERIAL, " +
                    " TIMESTAMP BIGINT, " +
                    " currency_pair VARCHAR(255), " +
                    " last_price DECIMAL, " +
                    " lowest_ask DECIMAL, " +
                    " highest_bid DECIMAL, " +
                    " percent_change DECIMAL, " +
                    " base_volume DECIMAL, " +
                    " quote_volume DECIMAL, " +
                    " day_high DECIMAL, " +
                    " day_low DECIMAL, " +
                    " frozen BOOL, " +
                    " PRIMARY KEY(id))";

            //String sql = "create index on quotestorage(timestamp,currency_pair,last_price)";
            //String sql = "analyze quotestorage";
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
    }*/
}
