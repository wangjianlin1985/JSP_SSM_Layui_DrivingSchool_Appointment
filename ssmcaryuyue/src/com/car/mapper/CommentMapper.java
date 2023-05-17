// 
// 
// 

package com.car.mapper;

import java.util.List;
import com.car.pojo.Page;
import com.car.pojo.Comment;

public interface CommentMapper
{
    void addComment(Comment p0);
    
    List<Comment> getCommentList(Page<Comment> p0);
    
    Integer getCommentCount(Page<Comment> p0);
    
    void deleteCommentById(String p0);
    
    Comment getById(String p0);
    
    void updateCommentArticle(Comment p0);
}
