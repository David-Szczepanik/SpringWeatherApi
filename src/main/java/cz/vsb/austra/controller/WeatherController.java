package cz.vsb.austra.controller;

import cz.vsb.austra.City;
import cz.vsb.austra.dto.WeatherDto;
import cz.vsb.austra.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {
    @Autowired
    WeatherService service;

    @CrossOrigin
    @RequestMapping(value = "/weather/{city}", method = RequestMethod.GET)
    public WeatherDto getWeatherForCity(@PathVariable("city") String city){
        City cityEnum = City.valueOf(city.toUpperCase());
        return service.getWeatherForCity(cityEnum);
    }

    @CrossOrigin
    @RequestMapping(value = "weather-all", method = RequestMethod.GET)
    public WeatherDto[] getWeatherForAllCities(){
        return service.getWeatherForAllCities();
    }
}