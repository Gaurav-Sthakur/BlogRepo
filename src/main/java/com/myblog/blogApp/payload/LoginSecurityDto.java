package com.myblog.blogApp.payload;

import lombok.Data;

@Data
public class LoginSecurityDto {

    private String usernameOrEmail;
    private String password;
}
