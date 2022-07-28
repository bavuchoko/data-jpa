package com.example.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})  //연관관계 필드는 tostring에 넣지 말자.
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public static Member generateMember(String username, int age, Team team) {
        Member member = new Member();
        member.username = username;
        member.age = age;
        member.changeTeam(team);
        return member;

    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
