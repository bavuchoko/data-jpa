package com.example.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {


    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);


        Member member1 = Member.generateMember("member1", 10, teamA);
        Member member2 = Member.generateMember("member2", 12, teamA);
        Member member3 = Member.generateMember("member3", 13, teamB);
        Member member4 = Member.generateMember("member4", 14, teamB);


        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        members.forEach(e ->{
            System.out.println("member = : " + e);
            System.out.println("-> member.team = : " + e.getTeam());
        } );

        }
}