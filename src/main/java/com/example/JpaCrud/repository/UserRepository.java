package com.example.JpaCrud.repository;
import com.example.JpaCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
	 
}
