package gijin.weather_app.repository;

import gijin.weather_app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>
{
    Optional<Member> findByUsername(String name);

}
