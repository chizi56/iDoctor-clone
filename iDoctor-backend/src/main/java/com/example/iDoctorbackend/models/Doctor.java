package com.example.iDoctorbackend.models;

import lombok.*;
import sun.security.krb5.internal.ccache.Tag;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "second_name", nullable = true)
    private String secondName;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "clinic_name")
    private String clinic_name;

    @Column
    private int expirienceYearAmount;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "specialization", nullable = true)
    private String specialization;

    @Column(name = "number", nullable = true)
    private String number;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "registrations",
            joinColumns =  @JoinColumn(name = "doctor_id") ,
            inverseJoinColumns =  @JoinColumn(name = "user_id") )
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getDoctors().add(this);
    }


}
