package tqs.ua.AirQuality.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.ua.AirQuality.model.AirQuality;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class BreezometerRepositoryTest {
    
    @InjectMocks
    private BreezometerRepository repository;

    @Test
    void whenGetAirQualityByCoordsAndDay_thenReturnCorrectResults() throws IOException, InterruptedException {
        assertThat(repository.getByCoordsAndDays("40", "40", "2021-05-10")).isInstanceOf(AirQuality.class);
    }

    @Test
    void whenGetAirQualityByCoordsAndDayInvalid_thenReturnCorrectResults() throws IOException, InterruptedException {
        assertThat(repository.getByCoordsAndDays("40", "40", "2070-05-10")).isNull();
    }

}
