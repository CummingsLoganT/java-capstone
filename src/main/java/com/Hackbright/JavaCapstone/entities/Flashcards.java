package com.Hackbright.JavaCapstone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Flashcards")
public class Flashcards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String front;

    @Column
    private String back;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

}
