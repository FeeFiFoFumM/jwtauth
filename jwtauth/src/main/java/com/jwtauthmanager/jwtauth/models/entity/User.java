package com.jwtauthmanager.jwtauth.models.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    public User() {
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "username" , unique = true,nullable = false)
    private String username;

    @Column(name = "password" , unique = false ,nullable = false)
    private String password;

    @Column(name = "email" , unique = true,nullable = false)
    private String email;
}
