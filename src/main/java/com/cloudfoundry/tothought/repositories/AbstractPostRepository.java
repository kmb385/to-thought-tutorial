package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.AbstractPost;

public interface AbstractPostRepository extends JpaRepository<AbstractPost, Integer> {

}
