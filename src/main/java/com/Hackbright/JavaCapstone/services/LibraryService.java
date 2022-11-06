package com.Hackbright.JavaCapstone.services;

import com.Hackbright.JavaCapstone.dtos.LibraryDto;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface LibraryService{
    List<LibraryDto> getAllNotesByUserId(Long userId);
    @Transactional
    void addNote(LibraryDto libraryDto , Long userId);

    @Transactional
    void deleteNoteById(Long index);

    @Transactional
    void updateNoteById(LibraryDto libraryDto);

    Optional<LibraryDto> getNoteById(Long index);

}
