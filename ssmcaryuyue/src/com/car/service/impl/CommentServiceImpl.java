// 
// 
// 

package com.car.service.impl;

import java.util.List;
import com.car.pojo.Page;
import com.car.utils.DateUtils;
import java.util.UUID;
import com.car.pojo.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.mapper.CommentMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.car.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private CommentMapper commentMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;
    
    @Override
    public void addComment(final Comment comment) {
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentTime(DateUtils.newDate("yyyy-MM-dd"));
        this.commentMapper.addComment(comment);
    }
    
    @Override
    public Page<Comment> getCommentList(final Page<Comment> page) {
        Integer currentPage = page.getCurrentPage();
        if (currentPage == null) {
            currentPage = 1;
        }
        final Integer currentCount = this.CURRENT_COUNT;
        page.setCurrentPage(currentPage);
        final int index = (currentPage - 1) * currentCount;
        page.setIndex(index);
        page.setCurrentCount(currentCount);
        final List<Comment> commentList = this.commentMapper.getCommentList(page);
        final Integer totalCount = this.commentMapper.getCommentCount(page);
        page.setTotalCount(totalCount);
        page.setList(commentList);
        final int totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public void deleteCommentById(final String commentId) {
        this.commentMapper.deleteCommentById(commentId);
    }
    
    @Override
    public Comment getById(final String commentId) {
        return this.commentMapper.getById(commentId);
    }
    
    @Override
    public void updateCommentArticle(final Comment comment) {
        this.commentMapper.updateCommentArticle(comment);
    }
}
