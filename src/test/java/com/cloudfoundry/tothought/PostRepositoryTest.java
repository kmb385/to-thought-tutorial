package com.cloudfoundry.tothought;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.cloudfoundry.tothought.entities.Comment;
import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.Stamp;
import com.cloudfoundry.tothought.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
//@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PostRepositoryTest {

	@Autowired
	PostRepository repository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	public void orderTest(){
		List<Comment> comments = new ArrayList<Comment>();
		Post post = new Post();
		post.setTitle("This is the title");
		
		for(int x = 0; x < 30; x++){
			Comment comment = new Comment();
			Stamp stamp = new Stamp();
			
			stamp.setAuthor("Kevin Bowersox");
			stamp.setCreatedDate(new Date(113, 01,x));
			
			comment.setBody(x + " Test ");
			comment.setStamp(stamp);
			comment.setPost(post);
			comments.add(comment);
		}
		
		post.setComments(comments);
		repository.save(post);
		
		entityManager.refresh(post);
		
		Post dbPost = repository.findOne(post.getPostId());
		List<Comment> dbcomments = dbPost.getComments();
		
		SimpleDateFormat format = new  SimpleDateFormat("MM/dd/yyyy");
		for(Comment dbComment: dbcomments){
			System.out.println(dbComment.getBody());
			System.out.println(format.format(dbComment.getStamp().getCreatedDate()));
		}
	}
	
}
