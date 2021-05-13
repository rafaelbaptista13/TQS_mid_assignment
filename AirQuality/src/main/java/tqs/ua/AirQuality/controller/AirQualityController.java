package tqs.ua.AirQuality.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.service.AirQualityService;
import tqs.ua.AirQuality.service.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AirQualityController {
    
    @Autowired
    private AirQualityService airQualityService;

    private static final Logger logger = LoggerFactory.getLogger(AirQualityController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/airquality")
    public AirQuality getAirQualityByCity(@RequestParam(value = "city") String city) throws IOException, InterruptedException {
        logger.info("LOGGER: GET /airquality?city={}", city);
        return airQualityService.getLatestByCity(city);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/airquality/coords")
    public AirQuality getAirQualityByLatLonDay(@RequestParam(value = "lat") String lat,
                                          @RequestParam(value = "lng") String lng, 
                                          @RequestParam(value = "from") String from) throws IOException, InterruptedException {
        logger.info("LOGGER: GET /airquality/coords?lat={}&lng={}&from={}", lat, lng, from);
        return airQualityService.getByCoordsAndDay(lat, lng, from);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/airquality/cache")
    public Cache getCacheStatistics() {
        logger.info("LOGGER: GET /airquality/cache");
        return airQualityService.getCacheStatistics();
    }
}
