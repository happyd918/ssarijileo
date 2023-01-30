package com.ssafy.ssarijileo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class User {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    private String email;

    private String nickname;

    private String image;

    private String token;

    public void updateToken(String token) {
        this.token = token;
    }

    public void updateImage(String image) {
        this.image = image;
    }

}
