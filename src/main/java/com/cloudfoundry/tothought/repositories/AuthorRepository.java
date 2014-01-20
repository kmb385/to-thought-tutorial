package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
