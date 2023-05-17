// 
// 
// 

package com.car.service;

import com.car.pojo.Page;
import com.car.pojo.Appoint;

public interface AppointService
{
    boolean addAppoint(Appoint p0);
    
    Page<Appoint> getAppointList(Page<Appoint> p0);
    
    void deleteAppointById(String p0);
    
    Appoint getAppointById(String p0);
}
