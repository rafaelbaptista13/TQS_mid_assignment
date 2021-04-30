package tqs.ua.AirQuality.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.repository.AmbeeRepository;

@Service
public class AirQualityService {
    
    @Autowired
    private AmbeeRepository externalApi;

    public AirQuality getLatestByCity(String city) throws IOException, InterruptedException {
        AirQuality result = this.externalApi.getLatestByCity(city);

        if (result == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Request not valid.  Please check api token and city_id");
        }
        return result;
    };

}
