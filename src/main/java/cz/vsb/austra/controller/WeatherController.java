package cz.vsb.austra.controller;

import cz.vsb.austra.City;
import cz.vsb.austra.connector.WeatherApiConnector;
import cz.vsb.austra.dto.WeatherApiDto;
import cz.vsb.austra.dto.WeatherDto;
import cz.vsb.austra.service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @RequestMapping("/weather")
    String getWeather(){
        return "Pocasi pro vsechna mesta";
    }

    @RequestMapping("/weather/{city}")
    WeatherDto getWeatherForCity(@PathVariable("city") String city){
        City cityEnum = City.valueOf(city.toUpperCase());
        WeatherService weatherService = new WeatherService();
        return weatherService.getWeatherForCity(cityEnum);
    }
    @RequestMapping("weather-all")
    WeatherDto[] getWeatherForAllCities(){
        WeatherService weatherService = new WeatherService();
        return weatherService.getWeatherForAllCities();
    }
}
