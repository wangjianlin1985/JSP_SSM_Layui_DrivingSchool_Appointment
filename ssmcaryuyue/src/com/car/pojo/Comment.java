// 
// 
// 

package com.car.pojo;

import java.io.Serializable;

public class Comment implements Serializable
{
    private String commentId;
    private String commentArticle;
    private User user;
    private Teacher teacher;
    private String commentTime;
    
    public String getCommentId() {
        return this.commentId;
    }
    
    public void setCommentId(final String commentId) {
        this.commentId = commentId;
    }
    
    public String getCommentArticle() {
        return this.commentArticle;
    }
    
    public void setCommentArticle(final String commentArticle) {
        this.commentArticle = commentArticle;
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
    
    public String getCommentTime() {
        return this.commentTime;
    }
    
    public void setCommentTime(final String commentTime) {
        this.commentTime = commentTime;
    }
}
