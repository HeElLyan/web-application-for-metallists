//package ru.he.models.entities;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//@Entity
//@Table(name = "band_subscription")
//public class BandSubscription {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "band_id")
//    private Band Band;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subscription_id")
//    private User subscription;
//}
