package com.cloudfoundry.tothought;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class JpqlTest {

	@PersistenceContext
	EntityManager em;

	@Test
	public void test() {
		TypedQuery<Post> query = em.createQuery("select p from Post p", Post.class);
		List<Post> posts = query.getResultList();

		for (Post p : posts) {
			System.out.println(p.getTitle());
		}
	}

	@Test
	public void compositeTest() {
		TypedQuery<PostPart> query = em.createQuery("select p.postPart from Post p", PostPart.class);
		List<PostPart> postParts = query.getResultList();

		for (PostPart pp : postParts) {
			System.out.println(pp.getBody());
		}
	}

	@Test
	public void filterTest() {
		System.out.println("Please provide a name");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		
		TypedQuery<Post> query = em.createQuery("" +
				"select p from Post p " +
				"where p.title not like '%secret%' and " +
				"p.stamp.author like :name", Post.class);
		query.setParameter("name", "%" + name + "%");
		List<Post> posts = query.getResultList();
		
		for(Post p:posts){
			System.out.println(p.getTitle());
		}
	}

}
