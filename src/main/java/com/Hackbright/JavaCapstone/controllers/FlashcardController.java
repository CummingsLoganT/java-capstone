package com.Hackbright.JavaCapstone.controllers;

import com.Hackbright.JavaCapstone.dtos.FlashcardDto;
import com.Hackbright.JavaCapstone.services.FlashcardService;
import com.Hackbright.JavaCapstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/flashcards")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public void addFlashcard (@RequestBody FlashcardDto flashcardDto ,@PathVariable Long userId) {
        flashcardService.addFlashcard(flashcardDto , userId);
    }

}
