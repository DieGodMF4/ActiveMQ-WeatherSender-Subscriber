import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTest {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        String json = "{\"ss\":\"WeatherProvider\",\"ts\":1702039743,\"predTime\":1702123200,\"weather\":{\"ts\":\"2023-12-09T12:00:00Z\",\"weatherType\":\"Clear\",\"clouds\":1,\"temperature\":16.66,\"rain\":0.0,\"humidity\":58.0,\"wind\":3.53,\"location\":{\"latitude\":28.01,\"longitude\":-15.58,\"island\":\"Gran Canaria\",\"name\":\"Risco Prieto\"}},\"location\":{\"latitude\":28.01,\"longitude\":-15.58,\"island\":\"Gran Canaria\",\"name\":\"Risco Prieto\"}}\n";

        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        // Extract "ss"
        String ssValue = jsonObject.get("ss").getAsString();
        System.out.println("ss: " + ssValue);

        // Extract "ts"
        long tsValue = jsonObject.get("ts").getAsLong();
        System.out.println("ts: " + formatTimestamp(tsValue));
    }

    private static String formatTimestamp(long timestamp) {
        // Format timestamp as {yyyymmdd}
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date(timestamp * 1000L)); // Convert seconds to milliseconds
    }

}
