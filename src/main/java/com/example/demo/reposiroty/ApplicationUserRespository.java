package com.example.demo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AppUser;

@Repository
public interface ApplicationUserRespository extends JpaRepository<AppUser, String>  {
	
	AppUser findAllByUsername(String userName);

}
