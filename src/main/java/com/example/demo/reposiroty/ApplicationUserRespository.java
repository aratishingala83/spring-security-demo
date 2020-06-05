package com.example.demo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BaelUser;

@Repository
public interface ApplicationUserRespository extends JpaRepository<BaelUser, String>  {
	
	BaelUser findAllByUsername(String userName);

}
