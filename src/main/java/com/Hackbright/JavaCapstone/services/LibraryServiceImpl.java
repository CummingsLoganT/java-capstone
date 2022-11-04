package com.Hackbright.JavaCapstone.services;

import com.Hackbright.JavaCapstone.dtos.LibraryDto;
import com.Hackbright.JavaCapstone.entities.Library;
import com.Hackbright.JavaCapstone.entities.User;
import com.Hackbright.JavaCapstone.repositories.LibraryRepository;
import com.Hackbright.JavaCapstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public void addLibrary(LibraryDto libraryDto , Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        Library library = new Library(libraryDto);
        userOptional.ifPresent(library::setUser);
        libraryRepository.saveAndFlush(library);

    }

    @Transactional
    public void deleteLibraryById(Long libraryId) {
        Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
        libraryOptional.ifPresent(library -> libraryRepository.delete(library));
    }

    @Transactional
    public void updateLibraryById(LibraryDto libraryDto) {

        Optional<Library> libraryOptional = libraryRepository.findById(libraryDto.getId());
        libraryOptional.ifPresent(library -> {
            library.setIndex(libraryDto.getIndex());
            libraryRepository.saveAndFlush(library);
        });

    }

//    public List<LibraryDto> getAllLibrariesByUserId(Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            List<Library> libraryList = libraryRepository.findAllByUserEquals(userOptional.get());
//            return libraryList.stream().map(library -> new LibraryDto(library)).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }

    public Optional<LibraryDto> getLibraryById(Long libraryId) {
        Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
        if (libraryOptional.isPresent()) {
            return Optional.of(new LibraryDto(libraryOptional.get()));
        }

        return Optional.empty();
    }

}
