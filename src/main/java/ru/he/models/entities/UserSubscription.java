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
//@Table(name = "user_subscription")
//public class UserSubscription {
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
//    @JoinColumn(name = "subscription_id")
//    private User subscription;
//
//}
