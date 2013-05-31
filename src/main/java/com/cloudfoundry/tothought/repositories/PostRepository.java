package com.cloudfoundry.tothought.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByTitleContaining(String title);
	
	public List<Post> findByStampAuthor(String author);

	public List<Post> findByStampAuthorContainingOrStampAuthorContainingOrderByStampAuthorAsc(String author, String author1);
	
	public List<Post> findByCommentsStampCreatedDateAfter(Date date);
}
