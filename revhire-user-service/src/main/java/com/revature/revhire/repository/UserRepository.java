package com.revature.revhire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.revhire.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "Select * from user where lower(email)=:email", nativeQuery = true)
	Optional<User> authenticateUser( @Param("email") String email);

}
