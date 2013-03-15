package com.cloudfoundry.tothought;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Comment;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.Stamp;
import com.cloudfoundry.tothought.repositories.CommentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
//@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class CommentRepositoryTest {
	
	@Autowired
	CommentRepository repository;
	
	@Test
	public void insertTest() {
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("This is a tutorial on One to Many Relationships");

		final String body = "This is a small comment";
		
		final String author = "Kevin Bowersox";
		String email = "kmb385@gmail.com";
		Date created = new Date();
		
		Stamp stamp = new Stamp();
		stamp.setAuthor(author);
		stamp.setCreatedDate(created);
		stamp.setEmail(email);

		Comment comment = new Comment();
		
		comment.setStamp(stamp);
		comment.setBody(body);
		comment.setPost(post);
		
		repository.save(comment);
		
		Comment dbComment = repository.findOne(comment.getCommentId());
		assertNotNull(dbComment);
		assertNotNull(dbComment.getPost());
		assertEquals(body, dbComment.getBody());
		assertEquals(author, dbComment.getStamp().getAuthor());
	}

}
