import org.json.JSONArray;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Console implements DataShowing {
    private static  final String URL = "https://data.cityofnewyork.us/resource/h9gi-nx95.json";

    public void showInfo() {

        try {
            URL data = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) data.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            JSONArray jsonArray;

            int count = 1;
            int startRaw = 1;
            int limit = 100;
            int endRaw = 0;

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                    if (count == 100) {
                        jsonArray = new JSONArray(response.toString() + ']');
                        System.out.println("LENGTH: " + jsonArray.length());
                        endRaw = endRaw+count;
                        startRaw = startRaw+count;
                        count = 0;
                    }
                    count += 1;
                }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
