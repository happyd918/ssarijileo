package com.ssafy.ssarijileo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import net.bytebuddy.implementation.bind.annotation.Default;

@DynamicInsert
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class User {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID userId;

    private String socialId;

    private String status;
}
