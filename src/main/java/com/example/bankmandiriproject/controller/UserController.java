package com.example.bankmandiriproject.controller;

import com.example.bankmandiriproject.model.User;
import com.example.bankmandiriproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //get ALL user
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUser();
    }

    //create user
    @PostMapping
    public User createUser(@Valid @RequestBody User newUser) {
        return userService.create(newUser);
    }

    //get user by Id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return userService.findById(userId);
    }

    //update user
    @PutMapping("/update")
    public User saveUser(@RequestBody User user){
        return userService.update(user);
    }

    //delete user
    @DeleteMapping
    public void deleteUserById(@RequestParam String id){
        userService.deleteUser(id);
    }

}
