// 
// 
// 

package com.car.mapper;

import java.util.List;
import com.car.pojo.Page;
import com.car.pojo.Appoint;

public interface AppointMapper
{
    Appoint getByTime(Appoint p0);
    
    void addAppoint(Appoint p0);
    
    List<Appoint> getAppointList(Page<Appoint> p0);
    
    Integer getAppointCount(Page<Appoint> p0);
    
    void deleteAppointById(String p0);
    
    Appoint getAppointById(String p0);
}
