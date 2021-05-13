package tqs.ua.AirQuality.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.service.Cache;

class CacheTest {
    
    private Cache cache;

    @BeforeEach
    private void init() {
        cache = new Cache(2);

        assertThat(cache.getNumberOfRequests()).isZero();
        assertThat(cache.getNumberOfHits()).isZero();
        assertThat(cache.getNumberOfMisses()).isZero();
    }

    @Test
    void whenRequestDoesNotExist_thenIncrementMisses() {
        assertThat(cache.getRequest("invalidrequest")).isNull();
        assertThat(cache.getNumberOfRequests()).isEqualTo(1);
        assertThat(cache.getNumberOfHits()).isZero();
        assertThat(cache.getNumberOfMisses()).isEqualTo(1);
    }

    @Test
    void whenRequestExists_thenReturnsRequestAndIncrementsHits() {
        AirQuality response = getResponse();
        cache.saveRequest("Aveiro", response);
        assertThat(cache.getRequest("Aveiro")).isEqualTo(response);

        assertThat(cache.getNumberOfRequests()).isEqualTo(1);
        assertThat(cache.getNumberOfHits()).isEqualTo(1);
        assertThat(cache.getNumberOfMisses()).isZero();
    }

    @Test
    void whenRequestExpired_thenReturnsNullAndIncrementsMisses() throws InterruptedException {
        AirQuality response = getResponse();
        cache.saveRequest("Aveiro", response);

        TimeUnit.SECONDS.sleep(3);

        assertThat(cache.getRequest("Aveiro")).isNull();
        
        assertThat(cache.getNumberOfRequests()).isEqualTo(1);
        assertThat(cache.getNumberOfHits()).isZero();
        assertThat(cache.getNumberOfMisses()).isEqualTo(1);
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
