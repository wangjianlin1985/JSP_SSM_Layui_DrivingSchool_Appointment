// 
// 
// 

package com.car.pojo;

import java.io.Serializable;

public class Car implements Serializable
{
    private String carId;
    private String carName;
    private Integer carNum;
    private Integer carFlag;
    
    public Integer getCarFlag() {
        return this.carFlag;
    }
    
    public void setCarFlag(final Integer carFlag) {
        this.carFlag = carFlag;
    }
    
    @Override
    public String toString() {
        return "Car{carId='" + this.carId + '\'' + ", carName='" + this.carName + '\'' + ", carNum=" + this.carNum + '}';
    }
    
    public String getCarId() {
        return this.carId;
    }
    
    public void setCarId(final String carId) {
        this.carId = carId;
    }
    
    public String getCarName() {
        return this.carName;
    }
    
    public void setCarName(final String carName) {
        this.carName = carName;
    }
    
    public Integer getCarNum() {
        return this.carNum;
    }
    
    public void setCarNum(final Integer carNum) {
        this.carNum = carNum;
    }
}
