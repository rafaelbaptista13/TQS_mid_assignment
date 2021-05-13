package tqs.ua.AirQuality.controller;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.service.AirQualityService;

@WebMvcTest(AirQualityController.class)
class AirQualityControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService service;

    @Test
    public void whenGetAirQualityByCity_thenReturnResult() throws Exception {
        AirQuality response = getResponse();

        given(service.getLatestByCity("Aveiro")).willReturn(response);

        mvc.perform(get("/api/airquality?city=Aveiro").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aqi", is(response.getAQI())))
                .andExpect(jsonPath("$.co", is(response.getCO())))
                .andExpect(jsonPath("$.category", is(response.getCategory())))
                .andExpect(jsonPath("$.concentration", is(response.getConcentration())))
                .andExpect(jsonPath("$.no2", is(response.getNO2())))
                .andExpect(jsonPath("$.ozone", is(response.getOZONE())))
                .andExpect(jsonPath("$.pm10", is(response.getPM10())))
                .andExpect(jsonPath("$.pm25", is(response.getPM25())))
                .andExpect(jsonPath("$.pollutant", is(response.getPollutant())))
                .andExpect(jsonPath("$.so2", is(response.getSO2())));
        verify(service, VerificationModeFactory.times(1)).getLatestByCity("Aveiro");
        reset(service);
    }

    @Test
    public void whenGetAirQualityByCoordsAndDay_thenReturnResult() throws Exception {
        AirQuality response = getResponse();

        given(service.getByCoordsAndDay("12", "73", "2021-05-01")).willReturn(response);

        mvc.perform(get("/api/airquality/coords?lat=12&lng=73&from=2021-05-01").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aqi", is(response.getAQI())))
                .andExpect(jsonPath("$.co", is(response.getCO())))
                .andExpect(jsonPath("$.category", is(response.getCategory())))
                .andExpect(jsonPath("$.concentration", is(response.getConcentration())))
                .andExpect(jsonPath("$.no2", is(response.getNO2())))
                .andExpect(jsonPath("$.ozone", is(response.getOZONE())))
                .andExpect(jsonPath("$.pm10", is(response.getPM10())))
                .andExpect(jsonPath("$.pm25", is(response.getPM25())))
                .andExpect(jsonPath("$.pollutant", is(response.getPollutant())))
                .andExpect(jsonPath("$.so2", is(response.getSO2())));
        verify(service, VerificationModeFactory.times(1)).getByCoordsAndDay("12", "73", "2021-05-01");
        reset(service);
    }

    private AirQuality getResponse() {
        AirQuality response = new AirQuality();
        response.setAQI(5);
        response.setCO(0.2);
        response.setCategory("Good");
        response.setConcentration(3.301);
        response.setNO2(4.192);
        response.setOZONE(3.409);
        response.setPM10(0.25);
        response.setPM25(0.15);
        response.setPollutant("SO2");
        response.setSO2(3.301);
        return response;
    }

}
