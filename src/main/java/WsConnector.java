import com.neovisionaries.ws.client.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WsConnector {

    public void connect() throws IOException, WebSocketException {
        WebSocketFactory factory = new WebSocketFactory();
        factory.setConnectionTimeout(10000);
        ProxySettings settings = factory.getProxySettings();
        settings.setServer("ssl://1.4.152.149");
        settings.setPort(8080);
        WebSocket ws = factory.createSocket("wss://api2.poloniex.com:443");
        FileWriter fw = new FileWriter("/home/alexml/Downloads/test.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket ws, String message) throws IOException {
                //DataConverter dataConverter = new DataConverter(GMTTime.getDateGMT(),message);
                //PoloniexMapping poloniexMapping = new PoloniexMapping(GMTTime.getDateGMT(), message);
                //ObjectMapper mapper = new ObjectMapper();
                //String json = mapper.writeValueAsString(dataConverter.getCurrencyId());
                //System.out.println(poloniexMapping.getCurrencyId());
                //System.out.println(json);
                //System.out.println(dataConverter.getCurrencyId());
                System.out.println(message);
                out.println(message);
            }
        })
                .connect()
                        //.sendText("{\"command\" : \"subscribe\", \"channel\" : 1001}")
                //.sendText("{\"command\" : \"subscribe\", \"channel\" : 1002}");
                //.sendText("{\"command\" : \"subscribe\", \"channel\" : 1003}");
                //.sendText("{\"command\" : \"subscribe\", \"channel\" : \"BTC_ETH\"}")
                .sendText("{\"command\" : \"subscribe\", \"channel\" : \"BTC_LTC\"}")
                .sendText("{\"command\" : \"subscribe\", \"channel\" : \"USDT_BTC\"}");
    }
}