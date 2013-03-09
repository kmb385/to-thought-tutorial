package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
