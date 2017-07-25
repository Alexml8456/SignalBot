public class Connector {
/*    Subscription eventPublication;
    Subscription eventSubscription;

    static final int eventInterval = 2000;
    int lastEventValue = 0;

    public void start() {
        IWampConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();
        WampClientBuilder builder = new WampClientBuilder();
        Datasource datasource = new Datasource();
        datasource.openConnection();

        // Build client
        final WampClient client;
        try {
            builder.withConnectorProvider(connectorProvider)
                    .withUri("wss://api.poloniex.com")
                    .withRealm("realm1")
                    .withInfiniteReconnects()
                    .withReconnectInterval(5, TimeUnit.SECONDS);
            client = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        client.statusChanged().subscribe(new Action1<WampClient.State>() {
            @Override
            public void call(WampClient.State t1) {
                System.out.println("Session status changed to " + t1);

                if (t1 instanceof WampClient.ConnectedState) {

                    eventSubscription = client.makeSubscription("ticker")
                            .subscribe(new Action1<PubSubData>() {
                                @Override
                                public void call(PubSubData s) {
                                    //PoloniexMapping poloniexMapping = new PoloniexMapping(GMTTime.getDateGMT(), s.arguments());
                                    ObjectMapper mapper = new ObjectMapper();
                                    try {
                                        String json = mapper.writeValueAsString(poloniexMapping);
                                        System.out.println(json);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //datasource.insertQuotes(poloniexMapping);
                                }

                            });
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable t) {
                System.out.println("Session ended with error " + t);
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("Session ended normally");
            }
        });
        client.open();

        waitUntilKeypressed();
        System.out.println("Stopping subscription");
        if (eventSubscription != null)
            eventSubscription.unsubscribe();

        waitUntilKeypressed();
        System.out.println("Stopping publication");
        eventPublication.unsubscribe();
        datasource.closeConnection();
    }

    private void waitUntilKeypressed() {
        try {
            System.in.read();
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
