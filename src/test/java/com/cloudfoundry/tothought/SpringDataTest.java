package com.cloudfoundry.tothought;

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
import com.cloudfoundry.tothought.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class SpringDataTest {

	@Autowired
	PostRepository postRepository;

	// @Test
	// public void findByTitleContaining() {
	// List<Post> posts = postRepository.findByTitleContaining("Java");
	//
	// for(Post post: posts){
	// System.out.println(post.getTitle());
	// }
	// }

	// @Test
	// public void findByAuthor() {
	// List<Post> posts = postRepository.findByStampAuthor("Kevin Bowersox");
	//
	// for(Post post: posts){
	// System.out.println(post.getStamp().getAuthor());
	// System.out.println(post.getTitle());
	// }
	// }

//	@Test
//	public void findByAuthors() {
//		List<Post> posts 
//		= postRepository.findByStampAuthorContainingOrStampAuthorContainingOrderByStampAuthorAsc("Kevin", "Rod");
//
//		for (Post post : posts) {
//			System.out.println(post.getTitle());
//			System.out.println(post.getStamp().getAuthor());
//		}
//	}
	
	@Test
	public void findByCommentDate() {
		List<Post> posts = postRepository.findByCommentsStampCreatedDateAfter(new Date(113,4,1));
		
		for(Post post: posts){
			System.out.println(post.getTitle());
			
			for(Comment comment:post.getComments()){
				System.out.println("Author: " + comment.getStamp().getAuthor());
				System.out.println("Date: " + comment.getStamp().getCreatedDate());
				System.out.println(comment.getBody());
			}
		}
	}
}
