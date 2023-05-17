// 
// 
// 

package com.car.service;

import com.car.pojo.Page;
import com.car.pojo.Comment;

public interface CommentService
{
    void addComment(Comment p0);
    
    Page<Comment> getCommentList(Page<Comment> p0);
    
    void deleteCommentById(String p0);
    
    Comment getById(String p0);
    
    void updateCommentArticle(Comment p0);
}
