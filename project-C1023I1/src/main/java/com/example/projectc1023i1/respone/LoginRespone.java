package com.example.projectc1023i1.respone;

import com.example.projectc1023i1.Dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespone {
    @JsonProperty("token")
    private String token;

    private UserDTO userDTO;

    @JsonProperty("message")
    private String message;
}