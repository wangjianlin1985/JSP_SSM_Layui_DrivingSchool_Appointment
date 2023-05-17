// 
// 
// 

package com.car.service.impl;

import com.car.pojo.Car;
import java.util.UUID;
import java.util.List;
import com.car.pojo.Teacher;
import com.car.pojo.Page;
import org.springframework.beans.factory.annotation.Value;
import com.car.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.mapper.TeacherMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.car.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService
{
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CarMapper carMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    
    @Override
    public Page<Teacher> getTeacherList(final Page<Teacher> page) {
        Integer currentPage = page.getCurrentPage();
        if (currentPage == null) {
            currentPage = 1;
        }
        final Integer currentCount = this.CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        final int index = (currentPage - 1) * currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        final List<Teacher> teacherList = this.teacherMapper.getTeacherList(page);
        final Integer totalCount = this.teacherMapper.getTeacherCount(page);
        page.setTotalCount(totalCount);
        page.setList(teacherList);
        final int totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public void addTeacher(final Teacher teacher) {
        teacher.setTeacherId(UUID.randomUUID().toString());
        this.teacherMapper.addTeacher(teacher);
        final String carId = teacher.getCar().getCarId();
        final Car car = this.carMapper.getCarById(carId);
        car.setCarFlag(2);
        this.carMapper.updateCar(car);
    }
    
    @Override
    public Teacher getTeacherById(final String teacherId) {
        return this.teacherMapper.getTeacherById(teacherId);
    }
    
    @Override
    public void updateTeacher(final Teacher teacher) {
        this.teacherMapper.updateTeacher(teacher);
        final String carId = teacher.getCar().getCarId();
        final Car car = this.carMapper.getCarById(carId);
        car.setCarFlag(2);
        this.carMapper.updateCar(car);
    }
    
    @Override
    public void deleteById(final String id) {
        final Teacher teacher = this.teacherMapper.getTeacherById(id);
        this.teacherMapper.deleteById(id);
        final String carId = teacher.getCar().getCarId();
        final Car car = this.carMapper.getCarById(carId);
        car.setCarFlag(1);
        this.carMapper.updateCar(car);
    }
}
