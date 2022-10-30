package com.Hackbright.JavaCapstone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Theories")
public class Theories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
