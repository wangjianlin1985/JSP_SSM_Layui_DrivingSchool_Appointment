// 
// 
// 

package com.car.service;

import java.util.List;
import com.car.pojo.Page;
import com.car.pojo.Car;

public interface CarService
{
    void addCar(Car p0);
    
    Page<Car> getCarList(Page<Car> p0);
    
    Car getCarById(String p0);
    
    void updateCar(Car p0);
    
    void deleteCarById(String p0);
    
    List<Car> getCarListNotTeacher();
    
    Car getByName(String p0);
}
