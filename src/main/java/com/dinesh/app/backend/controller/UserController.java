package com.dinesh.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.app.backend.exception.UserNotFoundException;
import com.dinesh.app.backend.model.User;
import com.dinesh.app.backend.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public User newUser(@RequestBody User newUser) {
        System.out.println(newUser);
        return userRepository.save(newUser);
    }

    @GetMapping("/allUsers")
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found!!!"));
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " is not found!!!");
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully";
    }

    @PutMapping("updateUser/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) throws UserNotFoundException {
        return userRepository.findById(id)
                .map((param) -> {
                    param.setFirstName(user.getFirstName());
                    param.setLastName(user.getLastName());
                    param.setEmail(user.getEmail());

                    return userRepository.save(param);
                }).orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found!!!"));
    }

}
