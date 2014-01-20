package com.cloudfoundry.tothought;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Author;
import com.cloudfoundry.tothought.repositories.AuthorRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository repository;
	
	@Test
	public void insertTest() {
		Author sponsor = new Author();
		sponsor.setFirstName("Kevin");
		sponsor.setLastName("Bowersox");
		
		Author sponsored = new Author();
		sponsored.setFirstName("Joe");
		sponsored.setLastName("Schmo");
		
		sponsored.setSponsor(sponsor);
		sponsor.getSponsoredAuthors().add(sponsored);
		
		repository.save(sponsored);
		
		Author dbSponsored = repository.findOne(sponsored.getAuthorId());
		assertNotNull(dbSponsored);
		assertNotNull(dbSponsored.getSponsor());
		
		Author dbSponsor = repository.findOne(sponsor.getAuthorId());
		assertNotNull(dbSponsor.getSponsoredAuthors().size() > 0);
	}

}
