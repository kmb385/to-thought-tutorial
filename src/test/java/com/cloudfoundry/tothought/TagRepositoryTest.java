package com.cloudfoundry.tothought;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.Tag;
import com.cloudfoundry.tothought.repositories.TagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@Transactional
public class TagRepositoryTest {

	@Autowired
	TagRepository repository;
	
	@Test
	public void insertTest() {
		final String tagName = "Java";
		
		Post post1 = new Post();
		Post post2 = new Post();

		post1.setPostDate(new Date());
		post2.setPostDate(new Date());
		post1.setTitle("Java 101");
		post2.setTitle("Spring 101");
		
		Tag tag = new Tag();
		tag.setName(tagName);
		
		tag.getPosts().add(post1);
		tag.getPosts().add(post2);
		
		repository.save(tag);
		
		Tag dbTag = repository.findOne(tag.getTagId());
		assertNotNull(dbTag);
		assertEquals(tagName, dbTag.getName());
	
		List<Post> posts = dbTag.getPosts();
		assertTrue(posts.size() > 1);
		assertTrue(posts.contains(post1));
		assertTrue(posts.contains(post2));
	
	}

}
