package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.PostPart;

public interface PostPartRepository extends JpaRepository<PostPart, Integer> {

}
