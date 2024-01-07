package cz.vsb.austra.connector;

import cz.vsb.austra.City;
import cz.vsb.austra.dto.WeatherApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class WeatherApiConnector {
    private static String baseUrl = "https://api.weatherapi.com/v1/";
    private static String urlParameters = "current.json?key=";
    private static String APIKey = "cf264b79fa0447e2867150406240701";
    private static String url = baseUrl + urlParameters + APIKey + "&q=";
    public WeatherApiDto getWeatherForCity(City city) {
        RestTemplate template = new RestTemplate();
        URI uri = null;
        try {
            uri = new URI(url + city.toString()); // + "&aqi=yes"); //city + air quality
            System.out.println("Final URL: " + uri);
        } catch (java.net.URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<WeatherApiDto> responseEntity = template.getForEntity(uri, WeatherApiDto.class);
        return responseEntity.getBody();
    }
}
