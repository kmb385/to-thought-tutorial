package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
