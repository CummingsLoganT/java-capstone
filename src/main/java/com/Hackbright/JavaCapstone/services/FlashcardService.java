package com.Hackbright.JavaCapstone.services;

import com.Hackbright.JavaCapstone.dtos.FlashcardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public interface FlashcardService {
    @Transactional
    void addFlashcard(FlashcardDto flashcardDto, Long userId);

    @Transactional
    void deleteFlashcardById(Long flashcardId);

    @Transactional
    void updateFlashcardFrontById(FlashcardDto flashcardDto);

    @Transactional
    void updateFlashcardBackById(FlashcardDto flashcardDto);

    List<FlashcardDto> getAllFlashcardsByUserId(Long userId);

    Optional<FlashcardDto> getFlashcardById(Long flashcardId);
}
