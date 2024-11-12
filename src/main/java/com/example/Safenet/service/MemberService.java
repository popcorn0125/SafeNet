package com.example.Safenet.service;

import com.example.Safenet.dto.MemberDTO;
import com.example.Safenet.entity.*;
import com.example.Safenet.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String register(MemberDTO memberDTO) {
//         ID 중복 확인
        if (memberRepository.findById(memberDTO.getId()).isPresent()) {
            return null;
        }

//        // Province, City, Town은 선택된 값으로 설정하여 저장
//        Province province = new Province();
//        province.setProvinceId(memberDTO.getProvinceId());
//
//        City city = new City();
//        city.setCityId(memberDTO.getCityId());
//
//        Town town = new Town();
//        town.setTownId(memberDTO.getTownId());

        // 비밀번호 암호화 및 User 엔티티로 변환 후 저장
        Member user = Member.builder()
                .id(memberDTO.getId())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .name(memberDTO.getName())
                .phoneNumber(memberDTO.getPhoneNumber())
                .email(memberDTO.getEmail())
//                .province(province)
//                .city(city)
//                .town(town)
                .role(memberDTO.getRole().equals("시청 관리자") ? Role.시청_관리자 : Role.지자체_관리자)
                .signDate(LocalDateTime.now())
                .accountStatus(AccountStatus.대기)
                .build();

        log.info("맴버dto"+user);
        memberRepository.save(user);
        return user.getId();
    }

    // 로그인 처리
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<Member> userOpt = memberRepository.findById(memberDTO.getId());

        if (userOpt.isPresent()) {
            Member member = userOpt.get();
            if (passwordEncoder.matches(memberDTO.getPassword(), member.getPassword())) {
                return convertToDTO(member);
            }
        }
        return null;
    }

    private MemberDTO convertToDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .role(member.getRole().name())
//                .provinceId(member.getProvince().getProvinceId())
//                .cityId(member.getCity().getCityId())
//                .townId(member.getTown().getTownId())
                .build();
    }
}
