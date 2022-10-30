package com.Hackbright.JavaCapstone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cases")
public class Cases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
