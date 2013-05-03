package com.cloudfoundry.tothought;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudfoundry.tothought.entities.Post;
import com.cloudfoundry.tothought.entities.PostPart;
import com.cloudfoundry.tothought.entities.Stamp;

public class TestUtil {
	static String [] titles = {
			"This is a post about JPA",
			"This is a post about Spring",
			"This is a post about Hibernate",
			"This is a post about Java",
			"This is a post about PHP",
			"This is a secret post"
	};
	
	static String [] bodies = {
		"JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology  JPA is a great technology ",
		" Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework  Spring is a great framework ",
		" Hibernate is an awesome framework  Hibernate is an awesome framework  Hibernate is an awesome framework  Hibernate is an awesome framework  Hibernate is an awesome framework  Hibernate is an awesome framework  Hibernate is an awesome framework ",
		" Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language  Java is a strongly typed language ",
		" PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is...  PHP is... "
	};
	
	static String[] authors = {
		"Oliver Geirke",
		"Rod Johnson",
		"Kevin Bowersox",
		"Josh Long"
	};
	
	static List<String> titleList = Arrays.asList(titles);
	static List<String> bodyList = Arrays.asList(bodies);
	static List<String> authorList = Arrays.asList(authors);
	static List<List<String>> lists = new ArrayList<List<String>>();
	
	static{
		lists.add(titleList);
		lists.add(authorList);
		lists.add(bodyList);
	}
	
	
	public static Post createPost(){
		shuffle();
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle(titleList.get(0));
		
		Stamp stamp = new Stamp();
		stamp.setAuthor(authorList.get(0));
		post.setStamp(stamp);
		
		PostPart postPart = new PostPart();
		postPart.setBody(bodyList.get(0));
		
		postPart.setPost(post);
		post.setPostPart(postPart);

		return post;
	}
	
	public static void shuffle(){
		for(List<String> list:lists){
			Collections.shuffle(list);
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		for(int x = 0; x < 51; x++){
			Post post = TestUtil.createPost();
			em.persist(post);
			em.flush();
		}

		em.getTransaction().commit();
		System.out.println("Dummy Data Created");
	}
}
