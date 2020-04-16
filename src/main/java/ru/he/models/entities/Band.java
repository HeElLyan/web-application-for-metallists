package ru.he.models.entities;


import lombok.*;
import ru.he.models.enums.MetalGenre;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "band")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "band_name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

    @Enumerated(value = EnumType.STRING)
    private MetalGenre metalGenre;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<User> participants;

//    @OneToOne(fetch = FetchType.EAGER)
//    private User user;
}
