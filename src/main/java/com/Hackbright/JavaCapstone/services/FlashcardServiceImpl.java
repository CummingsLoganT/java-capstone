package com.Hackbright.JavaCapstone.services;

import com.Hackbright.JavaCapstone.dtos.FlashcardDto;
import com.Hackbright.JavaCapstone.entities.Flashcards;
import com.Hackbright.JavaCapstone.entities.User;
import com.Hackbright.JavaCapstone.repositories.FlashcardRepository;
import com.Hackbright.JavaCapstone.repositories.LibraryRepository;
import com.Hackbright.JavaCapstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlashcardServiceImpl implements FlashcardService {
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    @Transactional
    public void addFlashcard(FlashcardDto flashcardDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Flashcards flashcards = new Flashcards(flashcardDto);
        userOptional.ifPresent(flashcards::setUser);
        flashcardRepository.saveAndFlush(flashcards);
    }

    @Override
    @Transactional
    public void deleteFlashcardById(Long flashcardId) {
        Optional<Flashcards> flashcardsOptional = flashcardRepository.findById(flashcardId);
        flashcardsOptional.ifPresent(flashcards -> flashcardRepository.delete(flashcards));
    }

    @Override
    @Transactional
    public void updateFlashcardFrontById(FlashcardDto flashcardDto) {
        Optional<Flashcards> flashcardsOptional = flashcardRepository.findById(flashcardDto.getId());
        flashcardsOptional.ifPresent(flashcards -> {
            flashcards.setFront(flashcardDto.getFront());
            flashcardRepository.saveAndFlush(flashcards);
        });
    }

    @Override
    @Transactional
    public void updateFlashcardBackById(FlashcardDto flashcardDto) {
        Optional<Flashcards> flashcardsOptional = flashcardRepository.findById(flashcardDto.getId());
        flashcardsOptional.ifPresent(flashcards -> {
            flashcards.setBack(flashcardDto.getBack());
            flashcardRepository.saveAndFlush(flashcards);
        });
    }

    @Override
    public List<FlashcardDto> getAllFlashcardsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Flashcards> flashcardsList = flashcardRepository.findAllByUserEquals(userOptional.get());
            return flashcardsList.stream().map(flashcards -> new FlashcardDto(flashcards)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<FlashcardDto> getFlashcardById(Long flashcardId){
        Optional<Flashcards> flashcardsOptional = flashcardRepository.findById(flashcardId);
        if (flashcardsOptional.isPresent()){
            return Optional.of(new FlashcardDto(flashcardsOptional.get()));
        }
        return Optional.empty();
    }

}
