package com.Hackbright.JavaCapstone.controllers;

import com.Hackbright.JavaCapstone.services.FlashcardService;
import com.Hackbright.JavaCapstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/flashcards")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;
    @Autowired
    private UserService userService;

//    public List<String> addFlashcard(@RequestBody FlashcardDto flashcardDto){
//    }
}
