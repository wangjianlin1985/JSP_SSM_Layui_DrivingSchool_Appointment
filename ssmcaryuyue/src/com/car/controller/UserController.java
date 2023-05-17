// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.PathVariable;
import com.car.pojo.Page;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import com.car.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/user/" })
public class UserController
{
    @Autowired
    private UserService userService;
    
    @RequestMapping({ "login.html" })
    public String login(final User user, final HttpSession session, final Model model) {
        final User u = this.userService.getUserByUsername(user.getUsername());
        if (u == null) {
            model.addAttribute("error", (Object)"\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef!");
            return "login";
        }
        if (!user.getPassword().equals(u.getPassword())) {
            model.addAttribute("error", (Object)"\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef!");
            return "login";
        }
        session.setAttribute("user", (Object)u);
        return "redirect:/index.html";
    }
    
    @RequestMapping({ "register.html" })
    public String register(final User user, final Model model) {
        User u = this.userService.getUserByUsername(user.getUsername());
        if (u != null) {
            model.addAttribute("error", (Object)"\u7528\u6237\u5df2\u5b58\u5728\uff01");
            return "register";
        }
        u = this.userService.getByUserTelephone(user.getUserTelephone());
        if (u != null) {
            model.addAttribute("error", (Object)"\u624b\u673a\u53f7\u5df2\u88ab\u6ce8\u518c\uff01");
            return "register";
        }
        this.userService.insertUser(user);
        return "login";
    }
    
    @RequestMapping({ "logout.html" })
    public String logout(final HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }
    
    @RequestMapping({ "userList.html" })
    public String userList(final Page<User> page, final Model model) {
        final Page<User> p = this.userService.getUserList(page);
        model.addAttribute("page", (Object)p);
        return "/user/userList";
    }
    
    @RequestMapping({ "addUser.html" })
    public String addUser(final User user, final Model model) {
        User u = this.userService.getUserByUsername(user.getUsername());
        if (u != null) {
            model.addAttribute("error", (Object)"\u7528\u6237\u5df2\u5b58\u5728\uff01");
            return "/user/addUser";
        }
        u = this.userService.getByUserTelephone(user.getUserTelephone());
        if (u != null) {
            model.addAttribute("error", (Object)"\u624b\u673a\u53f7\u5df2\u88ab\u6ce8\u518c\uff01");
            return "/user/addUser";
        }
        this.userService.insertUser(user);
        return "redirect:/api/user/userList.html";
    }
    
    @RequestMapping({ "getUser/{userId}.html" })
    public String getUser(@PathVariable final String userId, final Model model) {
        final User user = this.userService.getUserById(userId);
        model.addAttribute("user", (Object)user);
        return "/user/editUser";
    }
    
    @RequestMapping({ "updateUser.html" })
    public String updateUser(final User user) {
        this.userService.updateUser(user);
        return "redirect:/api/user/userList.html";
    }
    
    @RequestMapping({ "toRecharge/{userId}.html" })
    public String toRecharge(@PathVariable final String userId, final Model model) {
        final User user = this.userService.getUserById(userId);
        model.addAttribute("user", (Object)user);
        return "/user/rechargeUser";
    }
    
    @RequestMapping({ "deleteUser{userId}.html" })
    public String deleteTeacher(@PathVariable final String userId, final String[] userIds) {
        if (userIds == null) {
            this.userService.deleteById(userId);
        }
        else {
            for (final String id : userIds) {
                this.userService.deleteById(id);
            }
        }
        return "redirect:/api/user/userList.html";
    }
    
    @RequestMapping({ "recharge.html" })
    public String recharge(final User user) {
        final User u = this.userService.getUserById(user.getUserId());
        u.setUserTime(u.getUserTime() + user.getUserTime());
        this.userService.updateUser(u);
        return "redirect:/api/user/userList.html";
    }
    
    @RequestMapping({ "adopt/{userId}.html" })
    public String adopt(@PathVariable final String userId) {
        final User u = this.userService.getUserById(userId);
        u.setUserState(2);
        this.userService.updateUser(u);
        return "redirect:/api/user/userList.html";
    }
    
    @RequestMapping({ "myInfo.html" })
    public String myInfo(final Model model, final HttpSession session) {
        final User user = (User)session.getAttribute("user");
        model.addAttribute("user", (Object)user);
        return "/user/myInfo";
    }
    
    @RequestMapping({ "updateMyInfo.html" })
    public String updateMyInfo(final User user, final HttpSession session) {
        this.userService.updateUser(user);
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }
}
