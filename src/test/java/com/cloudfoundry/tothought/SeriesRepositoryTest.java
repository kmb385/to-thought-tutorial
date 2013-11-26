package com.cloudfoundry.tothought;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cloudfoundry.tothought.entities.Series;
import com.cloudfoundry.tothought.repositories.SeriesRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class SeriesRepositoryTest {

	@Autowired
	SeriesRepository repository;
	
	@Test
	public void findOneTest() {
		Series series = repository.findOne(1);
		assertTrue(series.getPosts().size() > 0);
	}

}
