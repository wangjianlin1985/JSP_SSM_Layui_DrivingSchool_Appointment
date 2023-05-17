// 
// 
// 

package com.car.service;

import com.car.pojo.Page;
import com.car.pojo.User;

public interface UserService
{
    User getUserByUsername(String p0);
    
    void insertUser(User p0);
    
    Page<User> getUserList(Page<User> p0);
    
    User getByUserTelephone(String p0);
    
    User getUserById(String p0);
    
    void updateUser(User p0);
    
    void deleteById(String p0);
}
