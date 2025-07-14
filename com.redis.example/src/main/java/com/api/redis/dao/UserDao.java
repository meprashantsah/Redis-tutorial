package com.api.redis.dao;

import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String KEY = "USER1234";

    //save user
    public User save(User user){
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        return  user;
    }

    //get user
    public User get(String userId){
        return (User) redisTemplate.opsForHash().get(KEY,userId);
    }

    //find all
    public Map<Object,Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }

    // delete
    public void delete(String userId){
        redisTemplate.opsForHash().delete(KEY,userId);
    }

    // update user
    public User update(User user) {
        // Simply overwrite existing entry with same key (userId)
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }

}
