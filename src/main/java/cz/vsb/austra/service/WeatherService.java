package cz.vsb.austra.service;

import cz.vsb.austra.City;
import cz.vsb.austra.connector.WeatherApiConnector;
import cz.vsb.austra.dto.WeatherApiDto;
import cz.vsb.austra.dto.WeatherDto;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class WeatherService {
    public WeatherDto getWeatherForCity(City cityEnum){
        WeatherApiConnector connector = new WeatherApiConnector();
        WeatherApiDto connectorWeatherForCity = connector.getWeatherForCity(cityEnum);
        WeatherDto weatherDto = transformDto(connectorWeatherForCity);
        return weatherDto;
    }

    private static WeatherDto transformDto(WeatherApiDto weatherApiDto) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setLocation(weatherApiDto.getLocation().getName());
        weatherDto.setWeather_description(weatherApiDto.getCurrent().getCondition().getText());
        weatherDto.setRel_humidity(weatherApiDto.getCurrent().getHumidity());
        weatherDto.setTimestamp(weatherApiDto.getCurrent().getLast_updated());
        weatherDto.setTemp_celsius(weatherApiDto.getCurrent().getTemp_c());
        weatherDto.setWind_direction(weatherApiDto.getCurrent().getWind_dir());
        weatherDto.setWindSpeed_mps(Math.round(weatherApiDto.getCurrent().getWind_kph()/3.6));
        return weatherDto;
    }

    public WeatherDto[] getWeatherForAllCities() {
        return Arrays.stream(City.values())
            .map(this::getWeatherForCity)
            .toArray(WeatherDto[]::new);
    }
}
