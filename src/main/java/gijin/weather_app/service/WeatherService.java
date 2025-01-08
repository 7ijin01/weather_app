package gijin.weather_app.service;

import gijin.weather_app.api.WeatherApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    public WeatherService(RestTemplate restTemplate,
                          @Value("${spring.weather.api.key}") String apiKey,
                          @Value("${spring.weather.api.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    private Map<String, Object> getWeatherData(String endpoint, String baseDate, String baseTime, int nx, int ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + endpoint)
                .queryParam("serviceKey", apiKey)
                .queryParam("numOfRows", 10)
                .queryParam("pageNo", 1)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();


        try {

            return restTemplate.getForObject(uri, Map.class);
            //return restTemplate.exchange(uri, HttpMethod.GET, getRequestEntity(), Map.class).getBody();
        } catch (Exception e) {
            throw new RuntimeException("Weather API 호출 실패: " + e.getMessage(), e);
        }
    }
    private HttpEntity<Void> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));  // JSON 형식 요청
        return new HttpEntity<>(headers);
    }

    public Map<String, Object> getUltraSrtNcst(String baseDate, String baseTime, int nx, int ny) {
        return getWeatherData("/getUltraSrtNcst", baseDate, baseTime, nx, ny);
    }

    public Map<String, Object> getUltraSrtFcst(String baseDate, String baseTime, int nx, int ny) {
        return getWeatherData("/getUltraSrtFcst", baseDate, baseTime, nx, ny);
    }

    public Map<String, Object> getVilageFcst(String baseDate, String baseTime, int nx, int ny) {
        return getWeatherData("/getVilageFcst", baseDate, baseTime, nx, ny);
    }
}

