import com.microsoft.azure.eventhubs.EventHubException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, EventHubException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("|--------------MENU--------------|");
        System.out.println("|1 -   SHOW INFO IN TERMINAL     |");
        System.out.println("|2 -     AZURE EVENTS HUB        |");
        System.out.println("|--------------------------------|");

        int choice = scanner.nextInt();
        Context context;

        switch (choice){
            case 1:
                context = new Context(new Console());
                context.executeInfo();
                break;
            case 2:
                context = new Context(new Event());
                context.executeInfo();
                break;
        }

    }
}
