package com.revature.revhire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.revhire.model.Application;
import com.revature.revhire.model.Job;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	@Query(value = "Select * from application where user_id=:userID", nativeQuery = true)
	List<Application> findApplicationsByUserId(long userID);
	@Query(value = "Select * from application where job_id=:jobID", nativeQuery = true)
	List<Application> findApplicationsByJobId(long jobID);

}
