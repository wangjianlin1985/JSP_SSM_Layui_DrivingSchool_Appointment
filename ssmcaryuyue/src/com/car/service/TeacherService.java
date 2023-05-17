// 
// 
// 

package com.car.service;

import com.car.pojo.Teacher;
import com.car.pojo.Page;

public interface TeacherService
{
    Page<Teacher> getTeacherList(Page<Teacher> p0);
    
    void addTeacher(Teacher p0);
    
    Teacher getTeacherById(String p0);
    
    void updateTeacher(Teacher p0);
    
    void deleteById(String p0);
}
