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
//    @NotNull
    private String password;
    @Email(message = "{errors.incorrect.email}")
    @NotNull(message = "{errors.null.email}")
    private String email;
//    @NotNull
//    @Size(min=1, max=16)
//    @Min(value = 0, message = "{errors.incorrect.age}")
    private String firstName;
//    @NotNull
//    @Size(min=1, max=16)
    private String lastName;
    private String about;
//    @NotNull
//    @Size(min = 2, max = 100)
    private String country;
//    @NotNull
//    @Size(min = 3, max = 50)
    private String city;
//    @NotNull(message = "{errors.null.age}")
    private String metalGenre;
//    @NotNull(message = "{errors.null.age}")
    private String instrumentType;
//    private Instrument instrument;
}
