package com.ssafy.ssarijileo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity @Builder
public class User {
//    @Id @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "user_id", length = 13)
//    @Type(type = "uuid-char")
//    private UUID userId;

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    private String email;

    private String nickname;

    private String image;

    private String token;

}
