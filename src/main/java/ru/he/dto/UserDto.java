package ru.he.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.he.models.entities.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String about;
    private String country;
    private String city;
    private String urlAvatar;
    private String confirmId;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .about(user.getAbout())
                .country(user.getCountry())
                .city(user.getCity())
                .urlAvatar(user.getUrlAvatar())
                .confirmId(user.getConfirmId())
                .build();
    }
}
