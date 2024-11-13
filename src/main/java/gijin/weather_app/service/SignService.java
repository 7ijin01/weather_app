package gijin.weather_app.service;

import gijin.weather_app.domain.Member;
import gijin.weather_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SignService
{
    private final MemberRepository memberRepository;

    public boolean signUp(Member member)
    {
        validatemember(member);
        memberRepository.save(member);
        return true;
    }
    public void validatemember(Member member)
    {
//        repository.findByName(member.getName())
//                .ifPresent(m->
//                {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(member1 ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
