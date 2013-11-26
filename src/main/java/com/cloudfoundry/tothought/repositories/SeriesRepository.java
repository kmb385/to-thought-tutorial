package com.cloudfoundry.tothought.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudfoundry.tothought.entities.Series;

public interface SeriesRepository extends JpaRepository<Series, Integer> {

}
