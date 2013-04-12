package com.cloudfoundry.tothought;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.ContentPost;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;
import com.cloudfoundry.tothought.repositories.ContentPostRepository;
import com.cloudfoundry.tothought.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AbstractPostRepositoryTest {

	@Autowired
	PostRepository pRepository;
	
	@Autowired
	ContentPostRepository cRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	public void insertPost() {
		Post post = new Post();
		final String title = "This is the regular post";
		post.setTitle(title);
		
		PostPart postPart = new PostPart();
		postPart.setBody("this is a test");
		
		post.setPostPart(postPart);
		postPart.setPost(post);
		
		pRepository.save(post);
		
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
		
		cRepository.save(post);
		
		ContentPost cPost = cRepository.findOne(post.getPostId());
		assertNotNull(cPost);
		assertEquals(contentUrl, cPost.getContentUrl());
		
		
		

	}
}
