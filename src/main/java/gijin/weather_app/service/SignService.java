package gijin.weather_app.service;

import gijin.weather_app.domain.Member;
import gijin.weather_app.dto.MemberDto;
import gijin.weather_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Encoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class SignService
{
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    public boolean signUp(Member member)
    {
        System.out.println("asdadasdada");
        validatemember(member);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = localDateTime.format(formatter);
        member.setCreated(formattedDateTime);
        member.setUpdated(formattedDateTime);
        member.setPassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);
        return true;
    }
    public void validatemember(Member member)
    {
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(member1 ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


}
