package tqs.ua.AirQuality.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tqs.ua.AirQuality.AirQualityApplication;
import tqs.ua.AirQuality.model.AirQuality;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AirQualityApplication.class)
@AutoConfigureMockMvc
public class AirQualityControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetAirQualityByCity_thenReturnResult() throws Exception {
        AirQuality response = new AirQuality();
        response.setCity("Aveiro");
        response.setPostalCode("3800-002");
        response.setCountryCode("PT");

        mvc.perform(get("/api/airquality?city=Aveiro").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is(response.getCity())))
                .andExpect(jsonPath("$.postalCode", is(response.getPostalCode())))
                .andExpect(jsonPath("$.countryCode", is(response.getCountryCode())));
    }

    @Test
    public void whenGetAirQualityByCoordsAndDay_thenReturnResult() throws Exception {
        AirQuality response = new AirQuality();
        response.setAQI(73);
        response.setNO2(0.69);
        response.setOZONE(34.16);
        response.setPM10(4.79);
        response.setPM25(3.36);
        response.setSO2(0.04);

        mvc.perform(get("/api/airquality/coords?lat=40.64&lng=8.65&from=2021-05-10").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aqi", is(response.getAQI())))
                .andExpect(jsonPath("$.no2", is(response.getNO2())))
                .andExpect(jsonPath("$.ozone", is(response.getOZONE())))
                .andExpect(jsonPath("$.pm10", is(response.getPM10())))
                .andExpect(jsonPath("$.pm25", is(response.getPM25())))
                .andExpect(jsonPath("$.so2", is(response.getSO2())));
    }

    @Test
    public void whenGetAirQualityWithInvalidCityName_thenIsBadRequest() throws Exception {
        mvc.perform(get("/api/airquality?city=-InvalidCityName-")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenGetAirQualityWithInvalidLatitudeAndInvalidLongitude_thenIsBadRequest() throws Exception {
        mvc.perform(get("/api/airquality/coords?lat=Norte&lng=Norte&from=2021-05-10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetAirQualityWithInvalidDate_thenIsBadRequest() throws Exception {
        mvc.perform(get("/api/airquality/coords?lat=40&lng=8&from=2070-05-10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenGetAirQualityByCityNameWithEmptyValues_thenIsBadRequest() throws Exception {
        mvc.perform(get("/api/airquality")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenGetAirQualityByCoordsAndDayWithEmptyValues_thenIsBadRequest() throws Exception {
        mvc.perform(get("/api/airquality/coords")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetCacheStatistics_thenReturnStatistics() throws Exception {
        mvc.perform(get("/api/airquality/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("numberOfRequests").isNumber())
                .andExpect(jsonPath("numberOfHits").isNumber())
                .andExpect(jsonPath("numberOfMisses").isNumber());
    }

}
