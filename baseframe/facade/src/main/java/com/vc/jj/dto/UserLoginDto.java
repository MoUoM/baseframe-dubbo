package com.vc.jj.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {

    private  String phone;

    private String addr1;

    private String addr2;

    private String birthday;

    private String content;

    private String headImgUrl;

    private String isDriver;

    private String nickname;

    private String token;

    private String vip;

    private String easemobUsername;

    private String easemobPassword;

}
