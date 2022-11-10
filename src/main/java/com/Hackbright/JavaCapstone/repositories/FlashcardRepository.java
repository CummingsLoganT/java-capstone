package com.Hackbright.JavaCapstone.repositories;

import com.Hackbright.JavaCapstone.entities.Flashcards;
import com.Hackbright.JavaCapstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcards , Long> {
    List<Flashcards> findAllByUserEquals(User user);
}
