package tqs.ua.AirQuality.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.repository.AmbeeRepository;
import tqs.ua.AirQuality.repository.BreezometerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AirQualityService {
    
    private final Cache cache = new Cache(5 * 60L);

    private static final Logger logger = LoggerFactory.getLogger(AirQualityService.class);

    @Autowired
    private AmbeeRepository externalApi;

    @Autowired
    private BreezometerRepository externalApi2;

    public AirQuality getLatestByCity(String city) throws IOException, InterruptedException {
        logger.info("LOGGER: AirQualityService getLatestByCity {}", city);
        AirQuality result = cache.getRequest(city);
        if (result == null) {
            result = this.externalApi.getLatestByCity(city);
            if (result == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Request not valid.  Please check api token and city");
            }
        }
        
        cache.saveRequest(city, result);
        return result;
    }

    public AirQuality getByCoordsAndDay(String lat, String lng, String from) throws IOException, InterruptedException {
        logger.info("LOGGER: AirQualityService getByCoordsAndDay lat={} lng={} from={}", lat, lng, from);
        AirQuality result = cache.getRequest(lat, lng, from);
        if (result == null) {
            result = this.externalApi.getByCoordsAndDays(lat, lng, from);
            if (result == null) {
                result = this.externalApi2.getByCoordsAndDays(lat, lng, from);
                if (result == null ) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Request not valid. Invalid api token or invalid latitude or invalid longitude values or invalid date");
                }
            }
        }
        cache.saveRequest(lat, lng, from, result);
        return result;
    }

    public Cache getCacheStatistics() {
        logger.info("LOGGER: AirQualityService getCacheStatistics");
        return cache;
    }

}
