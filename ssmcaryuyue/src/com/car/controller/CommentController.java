// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import com.car.utils.Result;
import org.apache.commons.lang3.StringUtils;
import com.car.pojo.Page;
import com.car.pojo.User;
import javax.servlet.http.HttpSession;
import com.car.pojo.Comment;
import com.car.pojo.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import com.car.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/comment/" })
public class CommentController
{
    @Autowired
    private CommentService commentService;
    @Autowired
    private TeacherService teacherService;
    
    @RequestMapping({ "toAddComment/{teacherId}.html" })
    public String toAddComment(@PathVariable final String teacherId, final Model model) {
        final Teacher teacher = this.teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", (Object)teacher);
        return "/comment/addComment";
    }
    
    @RequestMapping({ "addComment.html" })
    public String addComment(final Comment comment, final HttpSession session) {
        final User user = (User)session.getAttribute("user");
        comment.setUser(user);
        this.commentService.addComment(comment);
        return "redirect:/api/comment/commentList.html";
    }
    
    @RequestMapping({ "commentList.html" })
    public String commentList(final Page<Comment> page, final Model model, final HttpSession session, final String teacherId) {
        if (StringUtils.isNoneBlank(new CharSequence[] { teacherId })) {
            page.getParams().put("teacherId", teacherId);
        }
        final Page<Comment> p = this.commentService.getCommentList(page);
        model.addAttribute("page", (Object)p);
        return "/comment/commentList";
    }
    
    @RequestMapping({ "deleteComment{commentId}.html" })
    public String deleteComment(@PathVariable final String commentId, final String[] commentIds) {
        if (commentIds == null) {
            this.commentService.deleteCommentById(commentId);
        }
        else {
            for (final String id : commentIds) {
                this.commentService.deleteCommentById(id);
            }
        }
        return "redirect:/api/comment/commentList.html";
    }
    
    @RequestMapping({ "toEditComment/{commentId}.html" })
    public String toEdit(@PathVariable final String commentId, final Model model) {
        final Comment comment = this.commentService.getById(commentId);
        model.addAttribute("comment", (Object)comment);
        return "/comment/editComment";
    }
    
    @RequestMapping({ "editComment.html" })
    public String editComment(final Comment comment) {
        this.commentService.updateCommentArticle(comment);
        return "redirect:/api/comment/commentList.html";
    }
    
    @RequestMapping({ "readComment.action" })
    @ResponseBody
    public Result readComment(final Comment comment) {
        final Comment c = this.commentService.getById(comment.getCommentId());
        if (c == null || StringUtils.isBlank((CharSequence)c.getCommentArticle())) {
            return Result.build(400, "\u8bc4\u8bba\u4e3a\u7a7a");
        }
        return Result.build(200, c.getCommentArticle());
    }
}
