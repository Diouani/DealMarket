package com.root.dealmarketshared.entity;


import lombok.*;

import javax.persistence.*;
import java.util.*;


// rochdi said no lombok because it's hard to debug

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {



    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128 , nullable = false , unique = true)
    private String email;
    @Column(length = 128 , nullable = false)
    private String password;
    @Column( name = "first_name" ,length = 50 , nullable = false)
    private String firstName;
    @Column( name = "last_name" , length = 50 , nullable = false)
    private String lastName;
    @Column(length = 120)
    private String photos;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public void addRole(Role role){
        roles.add(role);
    }
}
