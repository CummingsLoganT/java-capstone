package com.Hackbright.JavaCapstone.entities;

import com.Hackbright.JavaCapstone.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private Set<Library> librarySet = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private Set<Flashcards> flashcardsSet = new HashSet<>();

    public User(UserDto userDto) {
        if (userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }

}
