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

import tqs.ua.AirQuality.model.AirQuality;

@Repository
public class AmbeeRepository {
    private static final String BASE_URL = "https://api.ambeedata.com/";
    private static final String API_TOKEN = "P95szepBYB1GshVNamWn29MTHdivsXhj6eqjMr18";

    public AirQuality getLatestByCity(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "latest/by-city?city=" + city))
                .header("x-api-key", API_TOKEN)
                .header("Content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
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
                result.setSO2(Double.parseDouble(response_json.getString("OZONE")));
                result.setAQI(Integer.parseInt(response_json.getString("AQI")));
                response_json = response_json.getJSONObject("aqiInfo");
                result.setPollutant(response_json.getString("pollutant"));
                result.setConcentration(Double.parseDouble(response_json.getString("concentration")));
                result.setCategory(response_json.getString("category"));

                return result;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } 
        }
        return null;
    }
}
