package com.example.datajpa.repository;

import com.example.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member member = Member.builder()
                .username("memberA")
                .build();

        Member savedMember = memberRepository.save(member);

        Member byId = memberRepository.findById(savedMember.getId()).get();

        assertThat(byId.getId()).isEqualTo(member.getId());
        assertThat(byId.getUsername()).isEqualTo(member.getUsername());
        assertThat(byId).isEqualTo(member);

    }
}