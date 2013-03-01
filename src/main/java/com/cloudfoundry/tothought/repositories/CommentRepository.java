package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
