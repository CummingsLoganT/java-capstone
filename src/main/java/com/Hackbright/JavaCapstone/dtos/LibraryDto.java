package com.Hackbright.JavaCapstone.dtos;

import com.Hackbright.JavaCapstone.entities.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDto implements Serializable {

    private Long id;
    private String index;
    private UserDto userDto;

    public LibraryDto(Library library) {
        if (library.getId() != null) {
            this.id = library.getId();
        }
        if (library.getIndex() != null) {
            this.index = library.getIndex();
        }
    }
}
