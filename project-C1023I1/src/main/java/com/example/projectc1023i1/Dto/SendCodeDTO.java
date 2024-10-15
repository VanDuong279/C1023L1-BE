package com.example.projectc1023i1.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendCodeDTO {
    @JsonProperty("code")
    private String code;

    @JsonProperty("email")
    private String email;

}
