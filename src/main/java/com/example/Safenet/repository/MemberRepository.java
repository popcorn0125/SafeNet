package com.example.Safenet.repository;
//작성일:24.11.12
//        작성자:안현준
//        내용:로그인,회원가입 구현(세션방식)
import com.example.Safenet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    // 필요한 커스텀 쿼리를 정의할 수 있습니다.
}
