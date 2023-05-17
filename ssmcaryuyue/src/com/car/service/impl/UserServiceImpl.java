// 
// 
// 

package com.car.service.impl;

import java.util.List;
import com.car.pojo.Page;
import com.car.utils.DateUtils;
import java.util.UUID;
import com.car.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.car.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    
    @Override
    public User getUserByUsername(final String username) {
        return this.userMapper.getUserByUsername(username);
    }
    
    @Override
    public void insertUser(final User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setUserState(1);
        user.setUserRole(1);
        user.setUserTime(20);
        final String now = DateUtils.newDate("yyyyMMdd");
        final String maxNumber = this.userMapper.getMaxNumberByNow(now);
        if (maxNumber == null) {
            user.setUserNumber(String.valueOf(now) + "001");
        }
        else {
            final int num = Integer.parseInt(maxNumber.substring(8));
            user.setUserNumber(String.valueOf(now) + (num + 1));
        }
        this.userMapper.insertUser(user);
    }
    
    @Override
    public Page<User> getUserList(final Page<User> page) {
        Integer currentPage = page.getCurrentPage();
        if (currentPage == null) {
            currentPage = 1;
        }
        final Integer currentCount = this.CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        final int index = (currentPage - 1) * currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        final List<User> userList = this.userMapper.getUserList(page);
        final Integer totalCount = this.userMapper.getUserCount(page);
        page.setTotalCount(totalCount);
        page.setList(userList);
        final int totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public User getByUserTelephone(final String userTelephone) {
        return this.userMapper.getUserByTelephone(userTelephone);
    }
    
    @Override
    public User getUserById(final String userId) {
        final User user = this.userMapper.getUserById(userId);
        return user;
    }
    
    @Override
    public void updateUser(final User user) {
        this.userMapper.updateUser(user);
    }
    
    @Override
    public void deleteById(final String id) {
        this.userMapper.deleteById(id);
    }
}
