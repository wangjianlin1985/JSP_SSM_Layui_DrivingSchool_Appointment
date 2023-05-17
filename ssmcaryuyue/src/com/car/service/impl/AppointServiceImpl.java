// 
// 
// 

package com.car.service.impl;

import java.util.List;
import com.car.pojo.Page;
import com.car.pojo.User;
import java.text.ParseException;
import com.car.utils.DateUtils;
import java.util.UUID;
import com.car.pojo.Appoint;
import org.springframework.beans.factory.annotation.Value;
import com.car.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.mapper.AppointMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.car.service.AppointService;

@Service
@Transactional
public class AppointServiceImpl implements AppointService
{
    @Autowired
    private AppointMapper appointMapper;
    @Autowired
    private UserMapper userMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    
    @Override
    public boolean addAppoint(final Appoint appoint) {
        final Appoint appoint2 = this.appointMapper.getByTime(appoint);
        if (appoint2 != null) {
            return false;
        }
        appoint.setAppointId(UUID.randomUUID().toString());
        this.appointMapper.addAppoint(appoint);
        try {
            final Integer hour = DateUtils.getHour(appoint.getAppointStartDate(), appoint.getAppointEndDate());
            final User user = this.userMapper.getUserById(appoint.getUser().getUserId());
            user.setUserTime(user.getUserTime() - hour);
            this.userMapper.updateUser(user);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    @Override
    public Page<Appoint> getAppointList(final Page<Appoint> page) {
        Integer currentPage = page.getCurrentPage();
        if (currentPage == null) {
            currentPage = 1;
        }
        final Integer currentCount = this.CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        final int index = (currentPage - 1) * currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        final List<Appoint> appointList = this.appointMapper.getAppointList(page);
        final Integer totalCount = this.appointMapper.getAppointCount(page);
        page.setTotalCount(totalCount);
        page.setList(appointList);
        final int totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public void deleteAppointById(final String appointId) {
        this.appointMapper.deleteAppointById(appointId);
    }
    
    @Override
    public Appoint getAppointById(final String appointId) {
        return this.appointMapper.getAppointById(appointId);
    }
}
