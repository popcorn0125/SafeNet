package com.example.Safenet.repository;

import com.example.Safenet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    // 필요한 커스텀 쿼리를 정의할 수 있습니다.
}
