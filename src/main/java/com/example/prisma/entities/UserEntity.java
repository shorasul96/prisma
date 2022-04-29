package com.example.prisma.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "member_since")
    private String memberSince;
    @Column(name = "member_till")
    private String memberTill;
    private String gender;




}
