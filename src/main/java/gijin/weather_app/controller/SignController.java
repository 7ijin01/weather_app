package gijin.weather_app.controller;

import gijin.weather_app.domain.Member;
import gijin.weather_app.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    @PostMapping("/api/auth/signup")
    public String signUp(@RequestBody Member member) {
        if (signService.signUp(member)) {
            return "/home";
        }
        return "/home/login";
    }
}
