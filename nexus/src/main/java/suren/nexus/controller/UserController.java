package suren.nexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful"+"["+user.getName()+","+user.getId()+"]");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}
}

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }
}
