package com.spring.jobapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jobapp.model.Job;

@Repository
public interface IJobRepo extends JpaRepository<Job, Long> {
	
	@Query(value = "from Job WHERE title LIKE %:title%")
	List<Job> searchByTitleJob(@Param("title") String title);
	
	@Query(value = "from Job WHERE jobRole LIKE %:role%")
	List<Job> searchByJobRole(@Param("role") String jobRole);
}
