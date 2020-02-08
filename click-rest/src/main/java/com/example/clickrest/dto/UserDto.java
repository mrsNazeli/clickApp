package com.example.clickrest.dto;

import com.example.sprest.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private UserType userType;
}
