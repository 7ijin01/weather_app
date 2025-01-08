package gijin.weather_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
        //XML 데이터를 처리하려는 경우 이 변환기를 추가해야 RestTemplate이 XML 데이터를 적절히 변환할 수 있습니다.
        return restTemplate;
    }
}
