import marrero_ferrera_gcid_ulpgc.control.*;
import marrero_ferrera_gcid_ulpgc.model.*;

import java.time.Instant;

public class JsonTest {

    public static void main(String[] args) {
        Location location = new Location(28.01f, -15.58f, "Gran Canaria", "Risco Prieto");
        WeatherController controller = new WeatherController();

        Instant instant = Instant.parse("2023-12-06T18:00:00Z");
        controller.getAndPublishWeatherData(location, "3c3aea5ce433b076c2f83b0c608896d8", "prediction.Weather", instant);
    }
}

