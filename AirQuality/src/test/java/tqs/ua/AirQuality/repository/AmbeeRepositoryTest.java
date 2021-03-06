package tqs.ua.AirQuality.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import tqs.ua.AirQuality.model.AirQuality;

@ExtendWith(MockitoExtension.class)
class AmbeeRepositoryTest {

    @InjectMocks
    private AmbeeRepository repository;

    @Test
    void whenGetAirQuality_thenReturnCorrectResults() throws IOException, InterruptedException {
        assertThat(repository.getLatestByCity("Aveiro")).isInstanceOf(AirQuality.class);
    }

    @Test
    void whenGetAirQualityInvalidCityName_thenReturnNull() throws IOException, InterruptedException {
        assertThat(repository.getLatestByCity("-invalidcityname-")).isNull();
    }

    @Test
    void whenGetAirQualityByCoordsAndDay_thenReturnCorrectResults() throws IOException, InterruptedException {
        assertThat(repository.getByCoordsAndDays("12", "73", "2021-05-10")).isInstanceOf(AirQuality.class);
    }

    @Test
    void whenGetAirQualityByCoordsAndDayInvalid_thenReturnCorrectResults() throws IOException, InterruptedException {
        assertThat(repository.getByCoordsAndDays("12", "73", "2070-05-10")).isNull();
    }
}
