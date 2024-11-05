package com.example.projectc1023i1.respone;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInforRespone {
    private String fullName;

    private String address;

    private Date birthday;

    private String numberphone;

    private String username;

    private String email;

    private String imgUrl;
    private Boolean gender;
}
