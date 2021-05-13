package tqs.ua.AirQuality.repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.stereotype.Repository;
import tqs.ua.AirQuality.model.AirQuality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class BreezometerRepository {
    private static final String BASE_URL = "https://api.breezometer.com/air-quality/v2/";
    private static final String API_TOKEN = "0b5c0133f4fd40308ae8ff51e4f5f49e";

    private static final Logger logger = LoggerFactory.getLogger(BreezometerRepository.class);

    public AirQuality getByCoordsAndDays(String lat, String lng, String from) throws IOException, InterruptedException {
        logger.info("LOGGER: BreezometerRepository getByCoordsAndDay lat={} lng={} from={}", lat, lng, from);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "historical/hourly?lat=" + lat + "&lon=" + lng + "&datetime=" + from + "&key=" + API_TOKEN + "&features=breezometer_aqi,local_aqi,pollutants_concentrations,pollutants_aqi_information"))
                .header("Content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("LOGGER: BreezometerRepository getByCoordsAndDay lat={} lng={} from={}. Response: {}", lat, lng, from, response);
        if (response.statusCode() == 200) {
            try {
                JSONObject response_json = new JSONObject(response.body());
                JSONObject data = response_json.getJSONObject("data");
                JSONObject indexes_baqi = data.getJSONObject("indexes").getJSONObject("baqi");
                JSONObject pollutants = data.getJSONObject("pollutants");
                
                AirQuality result = new AirQuality();
                String concentration = "concentration";
                String value = "value";
                result.setNO2(Double.parseDouble(pollutants.getJSONObject("no2").getJSONObject(concentration).getString(value)));
                result.setPM10(Double.parseDouble(pollutants.getJSONObject("pm10").getJSONObject(concentration).getString(value)));
                result.setPM25(Double.parseDouble(pollutants.getJSONObject("pm25").getJSONObject(concentration).getString(value)));
                result.setCO(Double.parseDouble(pollutants.getJSONObject("co").getJSONObject(concentration).getString(value)) / 1000);
                result.setSO2(Double.parseDouble(pollutants.getJSONObject("so2").getJSONObject(concentration).getString(value)));
                result.setOZONE(Double.parseDouble(pollutants.getJSONObject("o3").getJSONObject(concentration).getString(value)));
                result.setAQI(Integer.parseInt(indexes_baqi.getString("aqi")));
                
                return result;
            } catch (JSONException e) {
                logger.info("LOGGER: BreezometerRepository getByCoordsAndDay lat={} lng={} from={}. Error: {}", lat, lng, from, e);
                return null;
            } 
        }
        logger.info("LOGGER: BreezometerRepository getByCoordsAndDay lat={} lng={} from={}. Response Status Code != 200", lat, lng, from);
        return null;
    }

}
