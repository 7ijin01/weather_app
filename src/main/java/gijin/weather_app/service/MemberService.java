package gijin.weather_app.service;

import gijin.weather_app.domain.Member;
import gijin.weather_app.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(int id) {
        Member member = memberRepository.findById(id).get();
        if (member != null)
            return member;
        else
            return null;
    }
    public Member getMemberByUsername(String username) {
        Member member = memberRepository.findByUsername(username).get();
        if (member != null)
            return member;
        else
            return null;
    }
}
