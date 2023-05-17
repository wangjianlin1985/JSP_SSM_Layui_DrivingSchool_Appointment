// 
// 
// 

package com.car.pojo;

import java.io.Serializable;

public class Teacher implements Serializable
{
    private String teacherId;
    private String teacherNumber;
    private String teacherName;
    private Integer teacherAge;
    private String teacherTelephone;
    private String teacherPhoto;
    private Car car;
    
    public String getTeacherNumber() {
        return this.teacherNumber;
    }
    
    public void setTeacherNumber(final String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
    
    public String getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(final String teacherId) {
        this.teacherId = teacherId;
    }
    
    public String getTeacherName() {
        return this.teacherName;
    }
    
    public void setTeacherName(final String teacherName) {
        this.teacherName = teacherName;
    }
    
    public Integer getTeacherAge() {
        return this.teacherAge;
    }
    
    public void setTeacherAge(final Integer teacherAge) {
        this.teacherAge = teacherAge;
    }
    
    public String getTeacherTelephone() {
        return this.teacherTelephone;
    }
    
    public void setTeacherTelephone(final String teacherTelephone) {
        this.teacherTelephone = teacherTelephone;
    }
    
    public String getTeacherPhoto() {
        return this.teacherPhoto;
    }
    
    public void setTeacherPhoto(final String teacherPhoto) {
        this.teacherPhoto = teacherPhoto;
    }
    
    public Car getCar() {
        return this.car;
    }
    
    public void setCar(final Car car) {
        this.car = car;
    }
}
