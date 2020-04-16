package ru.he.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.he.dto.RegDto;
//import ru.he.models.entities.Instrument;
import ru.he.models.enums.MetalGenre;
import ru.he.models.enums.roles.UserInvitation;
import ru.he.models.enums.roles.UserRole;
import ru.he.models.entities.User;
import ru.he.models.enums.State;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.SignUpService;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signUp(RegDto form) {
        String username = form.getUsername();
        String password = form.getPassword();
        String email = form.getEmail();
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        String metalGenre = form.getMetalGenre();
//        Instrument instrument = form.getInstrument();
        String instrumentType = form.getInstrumentType();
        String country = form.getCountry();
        String city = form.getCity();
        String about = form.getAbout();

        boolean isEmailAvailable = usersRepository.findByEmail(email).isEmpty();
        if (!isEmailAvailable) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        String confirmId = UUID.randomUUID().toString();

        User user = User.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .metalGenre(metalGenre)
                .instrumentType(instrumentType)
//                .instrument(instrument)
                .country(country)
                .city(city)
                .about(about)
                .state(State.UNCONFIRMED)
                .confirmId(confirmId)
                .userRole(UserRole.USER)
                .userInvitation(UserInvitation.NOT_INVITED)
                .build();

        usersRepository.save(user);

        return confirmId;
    }
}
