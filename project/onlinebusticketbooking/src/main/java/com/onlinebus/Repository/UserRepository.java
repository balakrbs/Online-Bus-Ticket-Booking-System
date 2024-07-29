package com.onlinebus.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.onlinebus.Model.Users;

@Repository
public interface UserRepository extends  MongoRepository<Users, String>{
	
	Optional<Users> findByUsername(String username);
	
	Optional<Users> findByEmail(String email);

}
