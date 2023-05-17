// 
// 
// 

package com.car.mapper;

import java.util.List;
import com.car.pojo.Page;
import com.car.pojo.User;

public interface UserMapper
{
    User getUserByUsername(String p0);
    
    String getMaxNumberByNow(String p0);
    
    void insertUser(User p0);
    
    List<User> getUserList(Page<User> p0);
    
    Integer getUserCount(Page<User> p0);
    
    User getUserByTelephone(String p0);
    
    User getUserById(String p0);
    
    void updateUser(User p0);
    
    void deleteById(String p0);
}
