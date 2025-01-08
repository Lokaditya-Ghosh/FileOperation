import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) {
        String urlString = "https://api.open-meteo.com/v1/forecast?latitude=35.6895&longitude=139.6917&hourly=temperature_2m"; // Example API

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder inline = new StringBuilder();
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonObject = new JSONObject(inline.toString());
                System.out.println("Weather Data: " + jsonObject.getJSONObject("hourly"));
            } else {
                System.out.println("Failed to fetch API data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
