package ru.he.dto;

import lombok.Data;
//import ru.he.models.entities.Instrument;
import ru.he.models.enums.MetalGenre;

@Data
public class RegDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String about;
    private String country;
    private String city;
    private String metalGenre;
    private String instrumentType;
//    private Instrument instrument;
}
