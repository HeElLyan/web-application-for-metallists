//package ru.he.models.entities;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.he.models.enums.instruments.InstrumentType;
//import ru.he.models.enums.instruments.UnderType;
//
//import javax.persistence.*;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "instrument")
//public class Instrument {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(value = EnumType.STRING)
//    private InstrumentType instrumentType;
//
//    @Enumerated(value = EnumType.STRING)
//    private UnderType underType;
//
//    private String description;
//}
