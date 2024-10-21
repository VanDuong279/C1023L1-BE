package com.example.projectc1023i1.respone;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRespone {
    private String message;
    private String username;
    private String oldPassword;
}
