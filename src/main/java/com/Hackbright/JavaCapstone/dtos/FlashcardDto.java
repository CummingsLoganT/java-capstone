package com.Hackbright.JavaCapstone.dtos;

import com.Hackbright.JavaCapstone.entities.Flashcards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardDto implements Serializable {

    private Long id;
    private String front;
    private String back;
    private UserDto userDto;

    public FlashcardDto(Flashcards flashcards) {
        if (flashcards.getId() != null) {
            this.id = flashcards.getId();
        }
        if (flashcards.getFront() != null) {
            this.front = flashcards.getFront();
        }
        if (flashcards.getBack() != null) {
            this.back = flashcards.getBack();
        }
    }
}
