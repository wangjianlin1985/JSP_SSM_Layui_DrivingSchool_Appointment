// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import com.car.pojo.Car;
import com.car.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/car/" })
public class CarController
{
    @Autowired
    private CarService carService;
    
    @RequestMapping({ "carList.html" })
    public String carList(final Page<Car> page, final Model model) {
        final Page<Car> p = this.carService.getCarList(page);
        model.addAttribute("page", (Object)p);
        return "/car/carList";
    }
    
    @RequestMapping({ "addCar.html" })
    public String addCar(final Car car, final Model model) {
        final Car c = this.carService.getByName(car.getCarName());
        if (c != null) {
            model.addAttribute("error", (Object)"\u8f66\u724c\u53f7\u5df2\u5b58\u5728\uff01");
            return "/car/addCar";
        }
        this.carService.addCar(car);
        return "redirect:/api/car/carList.html";
    }
    
    @RequestMapping({ "getCar/{carId}.html" })
    public String getCarById(@PathVariable final String carId, final Model model) {
        final Car car = this.carService.getCarById(carId);
        model.addAttribute("car", (Object)car);
        return "/car/editCar";
    }
    
    @RequestMapping({ "editCar.html" })
    public String editCar(final Car car) {
        this.carService.updateCar(car);
        return "redirect:/api/car/carList.html";
    }
    
    @RequestMapping({ "deleteCar{carId}.html" })
    public String deleteCar(@PathVariable final String carId, final String[] carIds) {
        if (carIds == null) {
            this.carService.deleteCarById(carId);
        }
        else {
            for (final String id : carIds) {
                this.carService.deleteCarById(id);
            }
        }
        return "redirect:/api/car/carList.html";
    }
}
