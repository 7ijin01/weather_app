package gijin.weather_app.controller;


import gijin.weather_app.domain.Member;
import gijin.weather_app.jwt.JWTUtil;
import gijin.weather_app.service.MemberService;
import gijin.weather_app.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    public WeatherController(WeatherService weatherService, JWTUtil jwtUtil, MemberService memberService) {
        this.weatherService = weatherService;
        this.jwtUtil = jwtUtil;
        this.memberService = memberService;
    }

    @GetMapping("/api/weather/ultraSrtNcst")
    public Map<String, Object> getUltraSrtNcst(@RequestHeader("Authorization") String token,
                                               @RequestParam String baseDate,
                                               @RequestParam String baseTime,
                                               @RequestParam int nx,
                                               @RequestParam int ny) {
        validateAuthorization(token);
        return weatherService.getUltraSrtNcst(baseDate, baseTime, nx, ny);
    }

    @GetMapping("/api/weather/ultraSrtFcst")
    public Map<String, Object> getUltraSrtFcst(@RequestHeader("Authorization") String token,
                                               @RequestParam String baseDate,
                                               @RequestParam String baseTime,
                                               @RequestParam int nx,
                                               @RequestParam int ny) {
        validateAuthorization(token);
        return weatherService.getUltraSrtFcst(baseDate, baseTime, nx, ny);
    }

    @GetMapping("/api/weather/vilageFcst")
    public Map<String, Object> getVilageFcst(@RequestHeader("Authorization") String token,
                                             @RequestParam String baseDate,
                                             @RequestParam String baseTime,
                                             @RequestParam int nx,
                                             @RequestParam int ny) {
        validateAuthorization(token);

        return weatherService.getVilageFcst(baseDate, baseTime, nx, ny);
    }

    private void validateAuthorization(String token) {
        String username = jwtUtil.getUsername(token);
        if (username == null || memberService.getMemberByUsername(username) == null) {
            throw new RuntimeException("유효하지 않은 토큰 또는 회원 정보");
        }
    }
}

