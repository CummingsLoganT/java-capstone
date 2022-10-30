package com.Hackbright.JavaCapstone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Definitions")
public class Definitions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
