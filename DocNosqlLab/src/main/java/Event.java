import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Event implements DataShowing {
    private static final String URL = "https://data.cityofnewyork.us/resource/h9gi-nx95.json";

    public void showInfo() throws IOException, EventHubException {

        final ConnectionStringBuilder connStr = new ConnectionStringBuilder()
                .setNamespaceName("nosqllabs")
                .setEventHubName("labdoc")
                .setSasKeyName("Endpoint=sb://nosqllabs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=OwogqyuWpryc8KfSu+g2pz01rNutLIg3lpo8O14Z9s0=")
                .setSasKey("OwogqyuWpryc8KfSu+g2pz01rNutLIg3lpo8O14Z9s0=");

        final Gson gson = new GsonBuilder().create();
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        final EventHubClient ehClient = EventHubClient.createSync(connStr.toString(), executorService);

        try {
            URL data = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) data.openConnection();
            int responseCode = con.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();


            JSONArray jsonArray = new JSONArray(response.toString());

            showData(jsonArray, gson, ehClient);

            System.out.println(Instant.now() + ": Send Complete...");
            System.out.println("Press Enter to stop.");
            System.in.read();
        } finally {
            ehClient.closeSync();
            executorService.shutdown();
        }
    }

    public void showData(JSONArray jsonArray, Gson gson, EventHubClient ehClient) throws EventHubException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            System.out.println("Document: " + i);
            JSONObject payload = jsonObject;
            byte[] payloadBytes = gson.toJson(payload).getBytes(Charset.defaultCharset());
            EventData sendEvent = EventData.create(payloadBytes);

            ehClient.sendSync(sendEvent);
        }
    }
}
