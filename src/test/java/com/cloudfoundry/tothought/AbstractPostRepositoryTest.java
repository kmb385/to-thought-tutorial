package com.cloudfoundry.tothought;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.AbstractPost;
import com.cloudfoundry.tothought.entities.ContentPost;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;
import com.cloudfoundry.tothought.entities.Series;
import com.cloudfoundry.tothought.repositories.AbstractPostRepository;
import com.cloudfoundry.tothought.repositories.ContentPostRepository;
import com.cloudfoundry.tothought.repositories.PostRepository;
import com.cloudfoundry.tothought.repositories.SeriesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AbstractPostRepositoryTest {
	
	@Autowired
	SeriesRepository sRepository;
	
	@Autowired
	AbstractPostRepository repository;
	
	@Autowired
	PostRepository pRepository;
	
	@Autowired
	ContentPostRepository cRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	public void insertPost() {
		Series series = sRepository.findOne(1);
		Post post = new Post();
		final String title = "This is the regular post";
		post.setTitle(title);
		
		PostPart postPart = new PostPart();
		postPart.setBody("this is a test");
		
		post.setSeries(series);
		
		post.setPostPart(postPart);
		postPart.setPost(post);
		
		repository.save(post);
		
		Post dbPost = pRepository.findOne(post.getPostId());
		assertNotNull(dbPost);
		assertEquals(title, dbPost.getTitle());
	}
	
	@Test
	public void insertContentPost() {
		ContentPost post = new ContentPost();
		final String contentUrl = "http://tothought.cloudfoundry.com";
		post.setContentUrl(contentUrl);
		post.setTitle("This is a content post");
		
		repository.save(post);
		
		ContentPost cPost = cRepository.findOne(post.getPostId());
		assertNotNull(cPost);
		assertEquals(contentUrl, cPost.getContentUrl());
	}
	
	@Test
	public void findAllTest() {
		List<AbstractPost> posts = repository.findAll();
		System.out.println("Look HERE ---------------");
		assertNotNull(posts);
	}
}
