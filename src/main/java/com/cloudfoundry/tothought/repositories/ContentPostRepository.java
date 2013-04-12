package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.ContentPost;

public interface ContentPostRepository extends JpaRepository<ContentPost, Integer> {

}
