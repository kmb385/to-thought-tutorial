package com.cloudfoundry.tothought;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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
public class CriteriaApiTest {

	@PersistenceContext
	EntityManager em;

	 @Test
	 public void selectPostTest() {
	 CriteriaBuilder cb = em.getCriteriaBuilder();
	 CriteriaQuery<Post> query = cb.createQuery(Post.class);
	 Root<Post> postRoot = query.from(Post.class);
	
	 query.select(postRoot).where(cb.equal(postRoot.get("stamp").get("author"),
	 "Kevin Bowersox"));
	 TypedQuery<Post> qry = em.createQuery(query);
	
	 List<Post> posts = qry.getResultList();
	
	 for (Post post : posts) {
	 System.out.println(post.getStamp().getAuthor());
	 System.out.println(post.getTitle());
	 }
	 }

	 @Test
	 public void selectPostJPQL() {
	 TypedQuery<Post> query = em.createQuery(
	 "select p.stamp.author, p.title from Post " +
	 "p where p.stamp.author = 'Kevin Bowersox'", Post.class);
	
	 List<Post> posts = query.getResultList();
	
	 for (Post post : posts) {
	 System.out.println(post.getStamp().getAuthor());
	 System.out.println(post.getTitle());
	 }
	
	 }

	 @Test
	 public void joinApiTest() {
	 CriteriaBuilder cb = em.getCriteriaBuilder();
	 CriteriaQuery<Object[]> cQuery = cb.createQuery(Object[].class);
	 Root<Post> post = cQuery.from(Post.class);
	 Join<Post, PostPart> postPart = post.join("postPart");
	
	 cQuery.multiselect(post.get("stamp").get("author"),
	 postPart.get("body"));
	
	 Query qry = em.createQuery(cQuery);
	 List<Object[]> results = qry.getResultList();
	
	 for(Object[] result: results){
	 System.out.println(result[0]);
	 System.out.println(result[1]);
	 }
	 }

	 @Test
	 public void joinJpqlTest() {
	 Query query = em.createQuery("select p.stamp.author, " +
	 "pp.body from Post p join p.postPart pp");
	 List<Object[]> results = query.getResultList();
	
	 for(Object[] result: results){
	 System.out.println(result[0]);
	 System.out.println(result[1]);
	 }
	 }

	 @Test
	 public void aggregrateTest() {
	 CriteriaBuilder cb = em.getCriteriaBuilder();
	 CriteriaQuery<Object[]> cQuery = cb.createQuery(Object[].class);
	
	 Root<Post> post = cQuery.from(Post.class);
	
	 cQuery.multiselect(post.get("stamp").get("author"),
	 cb.count(post)).groupBy(post.get("stamp").get("author"));
	
	 Query qry = em.createQuery(cQuery);
	
	 List<Object[]> results = qry.getResultList();
	
	 for(Object[] result:results){
	 System.out.println(result[0]);
	 System.out.println(result[1]);
	 }
	 }

	@Test
	public void aggregateJpql() {
		Query query = em.createQuery("select p.stamp.author, " + "count(p) from Post p group by p.stamp.author");

		List<Object[]> results = query.getResultList();

		for (Object[] result : results) {
			System.out.println(result[0]);
			System.out.println(result[1]);
		}
	}
}
