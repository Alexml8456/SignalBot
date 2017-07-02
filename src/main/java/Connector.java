import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import ws.wamp.jawampa.PubSubData;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.connection.IWampConnectorProvider;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Connector {

    Subscription eventPublication;
    Subscription eventSubscription;

    static final int eventInterval = 2000;
    int lastEventValue = 0;

    public void start() {
        IWampConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();
        WampClientBuilder builder = new WampClientBuilder();

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
                                    Employee employee = new Employee(GMTTime.getDateGMT(), s.arguments().get(0).toString().replace("\"", ""), s.arguments().get(1).toString().replace("\"", ""), s.arguments().get(2).toString().replace("\"", ""), s.arguments().get(3).toString().replace("\"", ""), s.arguments().get(4).toString().replace("\"", ""), s.arguments().get(5).toString().replace("\"", ""), s.arguments().get(6).toString().replace("\"", ""), s.arguments().get(7).asInt(), s.arguments().get(8).toString().replace("\"", ""), s.arguments().get(9).toString().replace("\"", ""));
                                    ObjectMapper mapper = new ObjectMapper();
                                    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                                    try {
                                        writer.writeValue(new FileWriter("/home/alexml/Downloads/employee.json", true), employee);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //System.out.println(s.arguments());
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

        // Publish an event regularly
        eventPublication = Schedulers.computation().createWorker().schedulePeriodically(new Action0() {
            @Override
            public void call() {
                client.publish("ticker " + lastEventValue);
                lastEventValue++;
            }
        }, eventInterval, eventInterval, TimeUnit.MILLISECONDS);

        waitUntilKeypressed();
        System.out.println("Stopping subscription");
        if (eventSubscription != null)
            eventSubscription.unsubscribe();

        waitUntilKeypressed();
        System.out.println("Stopping publication");
        eventPublication.unsubscribe();
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
    }

}
