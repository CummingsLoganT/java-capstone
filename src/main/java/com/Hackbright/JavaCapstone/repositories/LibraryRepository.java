package com.Hackbright.JavaCapstone.repositories;

import com.Hackbright.JavaCapstone.entities.Library;
import com.Hackbright.JavaCapstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library , Long> {
    List<Library> findAllByUserEquals(User user);
}
