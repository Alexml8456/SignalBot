import java.sql.*;

public class Datasource {

    private static final String DB_NAME = "sbot";
    private static final String DB_PASSWORD = "1@wer57OreX78!";
    private static final String TABLE_QUOTESTORAGE = "quotestorage";

    private static final String TIMESTAMP = "timestamp";
    private static final String CURRENCY_PAIR = "currency_pair";
    private static final String LAST_PRICE = "last_price";
    private static final String LOWEST_ASK = "lowest_ask";
    private static final String HIGHEST_BID = "highest_bid";
    private static final String PERCENT_CHANGE = "percent_change";
    private static final String BASE_VOLUME = "base_volume";
    private static final String QUOTE_VOLUME = "quote_volume";
    private static final String DAY_HIGH = "day_high";
    private static final String DAY_LOW = "day_low";
    private static final String FROZEN = "frozen";


    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/quotes-local";

    public static final String INSERT_QUOTES = "INSERT INTO " + TABLE_QUOTESTORAGE +
            '(' + TIMESTAMP + ", " + CURRENCY_PAIR + ", " + LAST_PRICE + ", " + LOWEST_ASK + ", " + HIGHEST_BID + ", " +
            PERCENT_CHANGE + ", " + BASE_VOLUME + ", " + QUOTE_VOLUME + ", " + DAY_HIGH + ", " + DAY_LOW + ", " + FROZEN +
            ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Connection conn;
    private PreparedStatement insertIntoQuoteStorage;

    private static Datasource instance = new Datasource();

    private Datasource() {
    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertIntoQuoteStorage = conn.prepareStatement(INSERT_QUOTES);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(insertIntoQuoteStorage != null) {
                insertIntoQuoteStorage.close();
            }

            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs !=  null) {
                insertIntoSongs.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if(queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }


















    /*public void dbConnector() {
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
    }*/
}
