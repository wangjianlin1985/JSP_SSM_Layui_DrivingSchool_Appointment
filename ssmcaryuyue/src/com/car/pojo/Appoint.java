// 
// 
// 

package com.car.pojo;

import java.io.Serializable;

public class Appoint implements Serializable
{
    private String appointId;
    private User user;
    private Teacher teacher;
    private String appointStartDate;
    private String appointEndDate;
    
    @Override
    public String toString() {
        return "Appoint{appointId='" + this.appointId + '\'' + ", user=" + this.user + ", teacher=" + this.teacher + ", appointStartDate='" + this.appointStartDate + '\'' + ", appointEndDate='" + this.appointEndDate + '\'' + '}';
    }
    
    public String getAppointId() {
        return this.appointId;
    }
    
    public void setAppointId(final String appointId) {
        this.appointId = appointId;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }
    
    public String getAppointStartDate() {
        return this.appointStartDate;
    }
    
    public void setAppointStartDate(final String appointStartDate) {
        this.appointStartDate = appointStartDate;
    }
    
    public String getAppointEndDate() {
        return this.appointEndDate;
    }
    
    public void setAppointEndDate(final String appointEndDate) {
        this.appointEndDate = appointEndDate;
    }
}
