package tqs.ua.AirQuality.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.repository.AmbeeRepository;
import tqs.ua.AirQuality.repository.BreezometerRepository;

@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {

    @Mock
    private AmbeeRepository repository1;

    @Mock
    private BreezometerRepository repository2;

    @InjectMocks
    private AirQualityService service;

    @Test
    public void whenGetAirQuality_thenReturnCorrectResults() throws Exception {
        AirQuality response = getResponse();
        when(repository1.getLatestByCity("Aveiro")).thenReturn(response);
        assertThat(service.getLatestByCity("Aveiro")).isEqualTo(response);
        reset(repository1);
    }

    @Test
    public void whenGetAirQualityByDayAmbee_thenReturnCorrectResults() throws Exception {
        AirQuality response = getResponse();
        when(repository1.getByCoordsAndDays("12", "73", "2021-05-01")).thenReturn(response);
        assertThat(service.getByCoordsAndDay("12", "73", "2021-05-01")).isEqualTo(response);
        reset(repository1);
    }

    @Test
    public void whenGetAirQualityByDayBreezometer_thenReturnCorrectResults() throws Exception {
        AirQuality response = getResponse();
        when(repository1.getByCoordsAndDays("12", "73", "2021-05-01")).thenReturn(null);
        when(repository2.getByCoordsAndDays("12", "73", "2021-05-01")).thenReturn(response);
        assertThat(service.getByCoordsAndDay("12", "73", "2021-05-01")).isEqualTo(response);
        reset(repository1);
        reset(repository2);
    }

    private AirQuality getResponse() {
        AirQuality response = new AirQuality();
        response.setAQI(5);
        response.setCO(0.20208333333333334);
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