package tqs.ua.AirQuality.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tqs.ua.AirQuality.model.AirQuality;
import tqs.ua.AirQuality.service.AirQualityService;

@RestController
@RequestMapping("/api")
public class AirQualityController {
    
    @Autowired
    private AirQualityService airQualityService;

    @GetMapping(path="/airquality")
    public AirQuality getAirQualityByCity(@RequestParam(value = "city") String city) throws IOException, InterruptedException {
        return airQualityService.getLatestByCity(city);
    }

    
}
