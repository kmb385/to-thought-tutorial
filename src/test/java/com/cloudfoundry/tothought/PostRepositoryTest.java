package com.cloudfoundry.tothought;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Comment;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;
import com.cloudfoundry.tothought.entities.Stamp;
import com.cloudfoundry.tothought.entities.Tag;
import com.cloudfoundry.tothought.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
//@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PostRepositoryTest {

	@Autowired
	PostRepository repository;
	
	@Test
	public void test() {
		String tagName = "Java";
		String tagName2 = "Spring";
		
		Tag tag1 = new Tag();
		Tag tag2 = new Tag();
		
		tag1.setName(tagName);
		tag2.setName(tagName2);
		
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("First Post");
		
		post.getTags().add(tag1);
		post.getTags().add(tag2);
		
		repository.save(post);
		
		Post dbpost = repository.findOne(post.getPostId());
		assertNotNull(dbpost);
		System.out.println(dbpost.getTitle());
		
		List<Tag> tags = dbpost.getTags();
		
		assertTrue(tags.size() > 1);
		assertTrue(tags.contains(tag1));
		assertTrue(tags.contains(tag2));
	}
	
	@Test
	public void insertTest(){
		Comment comment = new Comment();
		Comment comment2 = new Comment();
		final String body1 = "This is a test";
		final String body2 = "This is another test";
		
		String author = "Kevin";
		String email = "kmb385@gmail.com";
		Date created = new Date();
		
		Stamp stamp = new Stamp();
		stamp.setAuthor(author);
		stamp.setCreatedDate(created);
		stamp.setEmail(email);
		
		comment.setBody(body1);
		comment2.setBody(body2);
		
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("First Post");
		
		post.setStamp(stamp);
		
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
		
		assertEquals(stamp.getAuthor(), dbpost.getStamp().getAuthor());
	}

}
