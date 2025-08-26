package SchoolManagementApp.ExternalApiAdd.WeatherApi;

import SchoolManagementApp.Component.AppCache;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

//    @Value("${weather_api}")
//    private String apikey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;


    public weatherDto  getWeather(String city){
        String finalAPI=appCache.APP_CACHE.get("weatherApi").replace("<city>",city);
        ResponseEntity<weatherDto> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, weatherDto.class);

        weatherDto body = response.getBody();
        return body;
    }
}
