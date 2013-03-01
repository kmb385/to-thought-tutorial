package com.cloudfoundry.tothought;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Comment;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;
import com.cloudfoundry.tothought.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Transactional
public class PostRepositoryTest {

	@Autowired
	PostRepository repository;
	
	@Test
	public void test() {
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("First Post");
		
		repository.save(post);
		
		Post dbpost = repository.findOne(post.getPostId());
		assertNotNull(dbpost);
		System.out.println(dbpost.getTitle());
	}
	
	@Test
	public void insertTest(){
		Comment comment = new Comment();
		Comment comment2 = new Comment();
		final String author = "Kevin Bowersox";
		final String body1 = "This is a test";
		final String author2 = "John Doe";
		final String body2 = "This is another test";
		
		comment.setAuthor(author);
		comment.setBody(body1);
		comment2.setAuthor(author2);
		comment2.setBody(body2);
		
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("First Post");
		
		post.getComments().add(comment);
		post.getComments().add(comment2);
		
		PostPart postPart = new PostPart();
		String body = "Hello";
		postPart.setBody(body);
		
		post.setPostPart(postPart);
		
		repository.save(post);
		
		Post dbpost = repository.findOne(post.getPostId());
		assertNotNull(dbpost);
		assertNotNull(dbpost.getPostPart());
		assertNotNull(dbpost.getComments());
		assertEquals(2, dbpost.getComments().size());
		
		List<Comment> comments = dbpost.getComments();
		
		assertTrue(comments.contains(comment));
		assertTrue(comments.contains(comment2));
		System.out.println(dbpost.getTitle());

	}

}
