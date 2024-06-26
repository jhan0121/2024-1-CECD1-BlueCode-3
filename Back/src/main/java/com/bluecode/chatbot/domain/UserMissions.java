package com.bluecode.chatbot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class UserMissions {

    // table id
    @Id @GeneratedValue
    @Column(name = "user_mission_id")
    private Long userMissionId;

    // 대상 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    // 생성 날짜
    @CreationTimestamp
    private LocalDateTime creationDate;

    // 클리어 날짜
    @UpdateTimestamp
    private LocalDateTime clearDate;

    // 할당된 미션
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Missions mission;

    // 현재 달성 현황
    @Column(name = "current_count")
    private int currentCount;
}
