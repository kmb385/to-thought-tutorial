package com.cloudfoundry.tothought;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class JpqlTest2 {

	@PersistenceContext
	EntityManager em;

	@Test
	public void testConstructor() {
		System.out.println("Please enter an author name:");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		List<Teaser> teasers = em.createNamedQuery("Post.teaser.constructor", Teaser.class)
				.setParameter("name","%"+ name +"%").getResultList();

		for (Teaser t : teasers) {
			System.out.println("Title: " + t.getTitle());
			System.out.println("Author: " + t.getAuthor());
			System.out.println("Body: " + t.getTeaserText());
		}

	}

	public void testJoin() {
		List<Object[]> teasers = em.createNamedQuery("Post.teaser").getResultList();

		for (Object[] t : teasers) {
			System.out.println("Title: " + t[0]);
			System.out.println("Author: " + t[1]);
			System.out.println("Body: " + t[2]);
		}

	}

	public void basicTest() {
		List<Post> posts = em.createNamedQuery("Post.findPosts", Post.class).getResultList();

		for (Post p : posts) {
			System.out.println(p.getTitle());
		}
	}

}
