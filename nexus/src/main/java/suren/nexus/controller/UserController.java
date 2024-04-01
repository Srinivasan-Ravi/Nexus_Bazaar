package suren.nexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import suren.nexus.model.User;
import suren.nexus.repository.UserRepo;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController
{
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public User newUser(@RequestBody User newUser)
    {
        return userRepo.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }
}
