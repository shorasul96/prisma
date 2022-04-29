package com.example.prisma.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rents")
public class RentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String borrower;
    private String book;
    @Column(name = "borrowed_from")
    private String borrowedFrom;
    @Column(name = "borrowed_to")
    private String borrowedTo;


}
