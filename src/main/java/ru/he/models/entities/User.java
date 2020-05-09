package ru.he.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.he.dto.RegDto;
import ru.he.models.enums.InvitationState;
import ru.he.models.enums.MetalGenre;
import ru.he.models.enums.instruments.InstrumentType;
import ru.he.models.enums.roles.UserInvitation;
import ru.he.models.enums.roles.UserRole;
import ru.he.models.enums.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "about")
    private String about;
    @Column(name = "url_avatar")
    private String urlAvatar;
    @Column(name = "url_background_image")
    private String urlBackgroundImage;

//    @Enumerated(value = EnumType.STRING)
    //с metalgenre не работает
    @Column(name = "metalGenre")
    private String metalGenre;

//    @Enumerated(value = EnumType.STRING)
    @Column(name = "instrumentType")
    private String instrumentType;

    @Column(name = "underType")
    private String underType;

//    @ManyToMany
//    private Instrument instrument;

    //for mail
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name = "confirm_id")
    private String confirmId;

    //admin or user
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Enumerated(value = EnumType.STRING)
    private UserInvitation userInvitation;

    @Enumerated(value = EnumType.STRING)
    private InvitationState invitationState;

    @JoinColumn(name = "band_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Band band;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    List<Token> tokens;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    List<FileInfo> fileInfos;

}
