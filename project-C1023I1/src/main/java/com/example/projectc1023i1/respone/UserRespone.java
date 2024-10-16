package com.example.projectc1023i1.respone;

import com.example.projectc1023i1.Dto.UserDTO;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespone {
    private UserDTO userDTO;
    private String token;
    private String message;
}