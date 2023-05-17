// 
// 
// 

package com.car.pojo;

import java.io.Serializable;

public class User implements Serializable
{
    private String userId;
    private String username;
    private String password;
    private String userName;
    private Integer userGender;
    private Integer userAge;
    private String userTelephone;
    private String userNumber;
    private Integer userTime;
    private Integer userState;
    private Integer userRole;
    
    @Override
    public String toString() {
        return "User{userId='" + this.userId + '\'' + ", username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", userName='" + this.userName + '\'' + ", userGender=" + this.userGender + ", userAge=" + this.userAge + ", userTelephone='" + this.userTelephone + '\'' + ", userNumber='" + this.userNumber + '\'' + ", userTime=" + this.userTime + ", userState=" + this.userState + ", userRole=" + this.userRole + '}';
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public Integer getUserGender() {
        return this.userGender;
    }
    
    public void setUserGender(final Integer userGender) {
        this.userGender = userGender;
    }
    
    public Integer getUserAge() {
        return this.userAge;
    }
    
    public void setUserAge(final Integer userAge) {
        this.userAge = userAge;
    }
    
    public String getUserTelephone() {
        return this.userTelephone;
    }
    
    public void setUserTelephone(final String userTelephone) {
        this.userTelephone = userTelephone;
    }
    
    public String getUserNumber() {
        return this.userNumber;
    }
    
    public void setUserNumber(final String userNumber) {
        this.userNumber = userNumber;
    }
    
    public Integer getUserTime() {
        return this.userTime;
    }
    
    public void setUserTime(final Integer userTime) {
        this.userTime = userTime;
    }
    
    public Integer getUserState() {
        return this.userState;
    }
    
    public void setUserState(final Integer userState) {
        this.userState = userState;
    }
    
    public Integer getUserRole() {
        return this.userRole;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public void setUserRole(final Integer userRole) {
        this.userRole = userRole;
    }
}
