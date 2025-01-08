package gijin.weather_app.service;

import gijin.weather_app.domain.Member;
import gijin.weather_app.dto.MemberDetails;
import gijin.weather_app.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MemberDetailsService implements UserDetailsService
{
    private final MemberRepository memberRepository;

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Member> member= memberRepository.findByUsername(username);
        if(member.get()==null)
        {
            return null;
        }
        return new MemberDetails(member.get());
    }
}
