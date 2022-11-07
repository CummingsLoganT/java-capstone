package com.Hackbright.JavaCapstone.controllers;

import com.Hackbright.JavaCapstone.dtos.LibraryDto;
import com.Hackbright.JavaCapstone.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/user/{userId}")
    public List<LibraryDto> getNotesByUser(@PathVariable Long userId) {
        return libraryService.getAllNotesByUserId(userId);
    }
    @GetMapping("/{index}")
    public Optional<LibraryDto> getNoteById(@PathVariable Long index) {
        return libraryService.getNoteById(index);
    }

    @PostMapping("/user/{userId}")
    public void addNote (@RequestBody LibraryDto libraryDto , @PathVariable Long userId) {
        libraryService.addNote(libraryDto , userId);
    }

    @DeleteMapping
    public void deleteNoteById(@PathVariable Long index) {
        libraryService.deleteNoteById(index);
    }

    @PutMapping
    public void updateNote(@RequestBody LibraryDto libraryDto) {
        libraryService.updateNoteById(libraryDto);
    }

}
