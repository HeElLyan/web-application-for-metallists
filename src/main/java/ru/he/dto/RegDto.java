package ru.he.dto;

import lombok.Data;
//import ru.he.models.entities.Instrument;
import ru.he.models.enums.MetalGenre;

import javax.validation.constraints.*;

@Data
public class RegDto {
//    @NotNull
//    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private String username;

    private String password;
    @Email
    @NotNull
    private String email;
//    @Size(min=1, max=16)
    private String firstName;

    private String lastName;

    private String about;

    private String country;

    private String city;

    private String metalGenre;

    private String instrumentType;
//    private Instrument instrument;
}
