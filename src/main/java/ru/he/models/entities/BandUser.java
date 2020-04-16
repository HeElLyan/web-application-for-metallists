//package ru.he.models.entities;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//@Entity
//@Table(name = "band_user")
//public class BandUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "band_id")
//    private Band band;
//
//    @Column(name = "description")
//    private String description;
//}
