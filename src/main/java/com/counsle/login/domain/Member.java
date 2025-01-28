package com.nuriapp.login.domain;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String id;
    private String password;
    private String tel;
    private String userName; // 이름
    private Date registerDate; // 가입날짜
    private String authNumber; // 인증번호
    private String authTempTime; // 인증시간

}
