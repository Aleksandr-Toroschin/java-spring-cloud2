package ru.toroschin.spring.dtos;

import lombok.Data;

@Data
public class NewUserDto {
    private String userName;
    private String password;
    private String email;
}
