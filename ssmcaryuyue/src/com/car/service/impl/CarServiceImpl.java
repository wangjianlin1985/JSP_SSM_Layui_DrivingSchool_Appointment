// 
// 
// 

package com.car.service.impl;

import java.util.List;
import com.car.pojo.Page;
import java.util.UUID;
import com.car.pojo.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.mapper.CarMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.car.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService
{
    @Autowired
    private CarMapper carMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    
    @Override
    public void addCar(final Car car) {
        car.setCarId(UUID.randomUUID().toString());
        this.carMapper.addCar(car);
    }
    
    @Override
    public Page<Car> getCarList(final Page<Car> page) {
        Integer currentPage = page.getCurrentPage();
        if (currentPage == null) {
            currentPage = 1;
        }
        final Integer currentCount = this.CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        final int index = (currentPage - 1) * currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        final List<Car> carList = this.carMapper.getCarList(page);
        final Integer totalCount = this.carMapper.getCarCount(page);
        page.setTotalCount(totalCount);
        page.setList(carList);
        final int totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public Car getCarById(final String carId) {
        return this.carMapper.getCarById(carId);
    }
    
    @Override
    public void updateCar(final Car car) {
        this.carMapper.updateCar(car);
    }
    
    @Override
    public void deleteCarById(final String carId) {
        this.carMapper.deleteCarById(carId);
    }
    
    @Override
    public List<Car> getCarListNotTeacher() {
        return this.carMapper.getCarListNotTeacher();
    }
    
    @Override
    public Car getByName(final String carName) {
        return this.carMapper.getByName(carName);
    }
}
