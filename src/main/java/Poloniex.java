public class Poloniex {
    public static void main(String[] args) {
        Thread thread = new Thread("Start Poloniex subscription") {
            public void run() {
                new Connector().start();
            }
        };
        thread.start();
        System.out.println(thread.getName());

//        Thread ptest = new Thread("New Thread") {
//            public void run() {
//                System.out.println("run by: " + getName());
//            }
//        };
//
//        ptest.start();
//        System.out.println(thread.getName());
        //new Datasource().dbConnector();
    }
}