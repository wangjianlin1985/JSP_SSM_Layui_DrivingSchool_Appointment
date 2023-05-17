// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.car.pojo.Car;
import java.util.List;
import org.springframework.ui.Model;
import com.car.pojo.Teacher;
import com.car.pojo.Page;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/teacher/" })
public class TeacherController
{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CarService carService;
    
    @RequestMapping({ "teacherList.html" })
    public String teacherList(final Page<Teacher> page, final Model model) {
        final Page<Teacher> p = this.teacherService.getTeacherList(page);
        model.addAttribute("page", (Object)p);
        return "/teacher/teacherList";
    }
    
    @RequestMapping({ "toAdd.html" })
    public String toAdd(final Model model) {
        final List<Car> carList = this.carService.getCarListNotTeacher();
        model.addAttribute("carList", (Object)carList);
        return "/teacher/addTeacher";
    }
    
    @RequestMapping({ "addTeacher.html" })
    public String addTeacher(final Teacher teacher) {
        this.teacherService.addTeacher(teacher);
        return "redirect:/api/teacher/teacherList.html";
    }
    
    @RequestMapping({ "getTeacher/{teacherId}.html" })
    public String getTeacher(@PathVariable final String teacherId, final Model model) {
        final Teacher teacher = this.teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", (Object)teacher);
        final List<Car> carList = this.carService.getCarListNotTeacher();
        model.addAttribute("carList", (Object)carList);
        return "/teacher/editTeacher";
    }
    
    @RequestMapping({ "updateTeacher.html" })
    public String updateTeacher(final Teacher teacher, @RequestParam("oldCar") final String oldCar) {
        if (!oldCar.equals(teacher.getCar().getCarId())) {
            final Car car = this.carService.getCarById(oldCar);
            car.setCarFlag(1);
            this.carService.updateCar(car);
        }
        this.teacherService.updateTeacher(teacher);
        return "redirect:/api/teacher/teacherList.html";
    }
    
    @RequestMapping({ "deleteTeacher{teacherId}.html" })
    public String deleteTeacher(@PathVariable final String teacherId, final String[] teacherIds) {
        if (teacherIds == null) {
            this.teacherService.deleteById(teacherId);
        }
        else {
            for (final String id : teacherIds) {
                this.teacherService.deleteById(id);
            }
        }
        return "redirect:/api/teacher/teacherList.html";
    }
}
