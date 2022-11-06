package com.Hackbright.JavaCapstone.entities;

import com.Hackbright.JavaCapstone.dtos.LibraryDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Library")
public class Library {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String index;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    public Library(LibraryDto libraryDto) {
        if (libraryDto.getIndex() != null) {
            this.index = libraryDto.getIndex();
        }
    }

}

