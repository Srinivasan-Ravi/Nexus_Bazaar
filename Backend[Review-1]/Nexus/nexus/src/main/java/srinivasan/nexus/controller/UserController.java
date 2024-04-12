package srinivasan.nexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import srinivasan.nexus.exception.UserNotFoundException;
import srinivasan.nexus.model.User;
import srinivasan.nexus.repository.UserRepo;

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

     @GetMapping("/getuser/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/putuser/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepo.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/deleteuser/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "User with id "+id+" has been deleted successfully!!.";
    }

    @PatchMapping("/patchuser/{id}")
    public ResponseEntity<User> patchUser(@RequestBody User partialUser, @PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    if (partialUser.getName() != null) {
                        user.setName(partialUser.getName());
                    }
                    if (partialUser.getEmail() != null) {
                        user.setEmail(partialUser.getEmail());
                    }
                    if (partialUser.getPassword() != null) {
                        user.setPassword(partialUser.getPassword());
                    }
                    return ResponseEntity.ok(userRepo.save(user));
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
