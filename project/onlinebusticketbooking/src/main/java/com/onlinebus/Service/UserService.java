package com.onlinebus.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebus.Model.Users;
import com.onlinebus.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void save(Users user) {
        userRepo.save(user);
    }

    public Optional<Users> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public String getEmailByUsername(String username) {
        Optional<Users> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get().getEmail();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Optional<Users> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void updateUser(Users user) {
        userRepo.save(user);
    }
}
