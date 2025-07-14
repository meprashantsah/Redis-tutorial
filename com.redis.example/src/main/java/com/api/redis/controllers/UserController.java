package com.api.redis.controllers;

import com.api.redis.dao.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    // get single user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return userDao.get(userId);
    }

    // find all
    @GetMapping
    public List<User> getAll(){
        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        return values.stream().map(value ->(User) value).collect(Collectors.toList());
    }

    //delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userDao.delete(userId);
    }

    // update user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody User updatedUser) {
        // Set the userId explicitly to make sure the correct key is used
        updatedUser.setUserId(userId);
        return userDao.update(updatedUser);
    }


}
