import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Poloniex {
    public static void main(String[] args) {
//        Thread thread = new Thread("Thread Connector") {
//            public void run() {
//                new Connector().start();
//            }
//        };
//        thread.start();
//        System.out.println(thread.getName());

//        Thread ptest = new Thread("New Thread") {
//            public void run() {
//                System.out.println("run by: " + getName());
//            }
//        };
//
//        ptest.start();
//        System.out.println(thread.getName());
        new DBConnector().dbConnector();
    }
}