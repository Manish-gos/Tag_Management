package com.ub.tag.TagManagement.dao;
import com.ub.tag.TagManagement.Model.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
