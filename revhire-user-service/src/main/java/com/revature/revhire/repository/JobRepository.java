package com.revature.revhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.revhire.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	@Query(value = "Select * from job where creator_id=:userID", nativeQuery = true)
	List<Job> findJobsByUserId(long userID);

}
