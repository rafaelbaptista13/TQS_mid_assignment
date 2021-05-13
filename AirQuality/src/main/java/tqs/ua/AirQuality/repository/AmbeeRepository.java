package tqs.ua.AirQuality.repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;  
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tqs.ua.AirQuality.model.AirQuality;

@Repository
public class AmbeeRepository {
    private static final String BASE_URL = "https://api.ambeedata.com/";
    private static final String API_TOKEN = "CWgX79CEL51baUCqzSAbu1zmhEUPc5px7NkvT9Zk";
    
    private static final Logger logger = LoggerFactory.getLogger(AmbeeRepository.class);

    public AirQuality getLatestByCity(String city) throws IOException, InterruptedException {
        logger.info("LOGGER: AmbeeRepository getLatestByCity {}",city);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "latest/by-city?city=" + city))
                .header("x-api-key", API_TOKEN)
                .header("Content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("LOGGER: AmbeeRepository getLatestByCity {}. Response: {}", city, response);
        if (response.statusCode() == 200) {
            try {
                JSONObject response_json1 = new JSONObject(response.body());
                JSONArray response_json2 = response_json1.getJSONArray("stations");
                JSONObject response_json = response_json2.getJSONObject(0);
                AirQuality result = new AirQuality();
                result.setNO2(Double.parseDouble(response_json.getString("NO2")));
                result.setPM10(Double.parseDouble(response_json.getString("PM10")));
                result.setPM25(Double.parseDouble(response_json.getString("PM25")));
                result.setCO(Double.parseDouble(response_json.getString("CO")));
                result.setSO2(Double.parseDouble(response_json.getString("SO2")));
                result.setOZONE(Double.parseDouble(response_json.getString("OZONE")));
                result.setAQI(Integer.parseInt(response_json.getString("AQI")));
                result.setCity(response_json.getString("city"));
                result.setPostalCode(response_json.getString("postalCode"));
                result.setCountryCode(response_json.getString("countryCode"));
                response_json = response_json.getJSONObject("aqiInfo");
                result.setPollutant(response_json.getString("pollutant"));
                result.setConcentration(Double.parseDouble(response_json.getString("concentration")));
                result.setCategory(response_json.getString("category"));
                
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
                logger.info("LOGGER: AmbeeRepository getLatestByCity {}. Error: {}", city, e);
                return null;
            } 
        }
        logger.info("LOGGER: AmbeeRepository getLatestByCity {}. Response Status Code != 200", city);
        return null;
    }

    public AirQuality getByCoordsAndDays(String lat, String lng, String from) throws IOException, InterruptedException {
        logger.info("LOGGER: AmbeeRepository getByCoordsAndDay lat={} lng={} from={}", lat, lng, from);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "history/by-lat-lng?lat=" + lat + "&lng=" + lng + "&from=" + from + "%2000:00:00&to=" + from + "%2002:00:00"))
                .header("x-api-key", API_TOKEN)
                .header("Content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("LOGGER: AmbeeRepository getByCoordsAndDay lat={} lng={} from={}. Response: {}", lat, lng, from, response);
        if (response.statusCode() == 200) {
            try {
                JSONObject response_json1 = new JSONObject(response.body());
                JSONArray response_json2 = response_json1.getJSONArray("data");
                if (response_json2.length() < 1) return null;
                JSONObject response_json = response_json2.getJSONObject(0);
                AirQuality result = new AirQuality();
                result.setNO2(Double.parseDouble(response_json.getString("NO2")));
                result.setPM10(Double.parseDouble(response_json.getString("PM10")));
                result.setPM25(Double.parseDouble(response_json.getString("PM25")));
                result.setCO(Double.parseDouble(response_json.getString("CO")));
                result.setSO2(Double.parseDouble(response_json.getString("SO2")));
                result.setOZONE(Double.parseDouble(response_json.getString("OZONE")));
                result.setAQI(Integer.parseInt(response_json.getString("AQI")));
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
                logger.info("LOGGER: AmbeeRepository getByCoordsAndDay lat={} lng={} from={}. Error: {}", lat, lng, from, e);
                return null;
            } 
        }
        logger.info("LOGGER: AmbeeRepository getByCoordsAndDay lat={} lng={} from={}. Response Status Code != 200", lat, lng, from);
        return null;
    }

}
