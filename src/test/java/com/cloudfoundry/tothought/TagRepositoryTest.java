package com.cloudfoundry.tothought;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Comment;
import com.cloudfoundry.tothought.repositories.CommentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Transactional

public class TagRepositoryTest {
	
	@Autowired
	CommentRepository repository;
	
	@Test
	public void insertTest() {
		final String author = "Kevin Bowersox";
		final String body = "This is a small comment";
		Comment comment = new Comment();

		comment.setAuthor(author);
		comment.setBody(body);
		
		repository.save(comment);
		
		Comment dbComment = repository.findOne(comment.getCommentId());
		assertNotNull(dbComment);
		assertEquals(author, dbComment.getAuthor());
		assertEquals(body, dbComment.getBody());
	}

}
