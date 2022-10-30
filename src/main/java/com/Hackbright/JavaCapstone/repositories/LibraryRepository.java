package com.Hackbright.JavaCapstone.repositories;

import com.Hackbright.JavaCapstone.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library , Long> {
}
