// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import com.car.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import com.car.pojo.Page;
import java.text.ParseException;
import com.car.utils.DateUtils;
import com.car.pojo.User;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpSession;
import com.car.pojo.Appoint;
import com.car.pojo.Teacher;
import org.springframework.ui.Model;
import com.car.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.service.AppointService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/appoint/" })
public class AppointController
{
    @Autowired
    private AppointService appointService;
    @Autowired
    private TeacherService teacherService;
    
    @RequestMapping({ "toAppoint.html" })
    public String toAppoint(final String teacherId, final Model model) {
        final Teacher teacher = this.teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", (Object)teacher);
        return "/appoint/addAppoint";
    }
    
    @RequestMapping({ "addAppoint.html" })
    public String addApoint(final Appoint appoint, final Model model, final HttpSession session) throws ParseException {
        final String startDate = appoint.getAppointStartDate();
        final Teacher teacher = this.teacherService.getTeacherById(appoint.getTeacher().getTeacherId());
        if (StringUtils.isBlank(startDate)) {
            model.addAttribute("teacher", (Object)teacher);
            model.addAttribute("error", (Object)"\u8bf7\u9009\u62e9\u9884\u7ea6\u65f6\u95f4!");
            return "/appoint/addAppoint";
        }
        final String[] split = startDate.split(" - ");
        appoint.setAppointStartDate(split[0]);
        appoint.setAppointEndDate(split[1]);
        final User user = (User)session.getAttribute("user");
        final Integer hour = DateUtils.getHour(appoint.getAppointStartDate(), appoint.getAppointEndDate());
        if (user.getUserTime() < hour) {
            model.addAttribute("teacher", (Object)teacher);
            model.addAttribute("error", (Object)"\u60a8\u7684\u5b66\u65f6\u4e0d\u8db3\uff0c\u8bf7\u53ca\u65f6\u5145\u503c\uff01");
            return "/appoint/addAppoint";
        }
        appoint.setUser(user);
        final boolean flag = this.appointService.addAppoint(appoint);
        if (!flag) {
            model.addAttribute("teacher", (Object)teacher);
            model.addAttribute("error", (Object)"\u6559\u7ec3\u8be5\u65f6\u95f4\u6bb5\u5df2\u6709\u9884\u7ea6!");
            return "/appoint/addAppoint";
        }
        return "redirect:/api/appoint/appointList.html";
    }
    
    @RequestMapping({ "appointList.html" })
    public String appointList(final Page<Appoint> page, final Model model, final HttpSession session) {
        final User user = (User)session.getAttribute("user");
        if (user.getUserRole() == 1) {
            page.getParams().put("userId", user.getUserId());
        }
        final Page<Appoint> p = this.appointService.getAppointList(page);
        model.addAttribute("page", (Object)p);
        return "/appoint/appointList";
    }
    
    @RequestMapping({ "deleteAppoint{appointId}.html" })
    public String deleteAppoint(@PathVariable final String appointId, final String[] appointIds) {
        if (appointIds == null) {
            this.appointService.deleteAppointById(appointId);
        }
        else {
            for (final String id : appointIds) {
                this.appointService.deleteAppointById(id);
            }
        }
        return "redirect:/api/appoint/appointList.html";
    }
    
    @RequestMapping({ "checkTime.action" })
    @ResponseBody
    public Result checkTime(final Appoint appoint) throws ParseException {
        final Appoint a = this.appointService.getAppointById(appoint.getAppointId());
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String now = DateUtils.newDate("yyyy-MM-dd HH:mm:ss");
        if (sdf.parse(now).compareTo(sdf.parse(a.getAppointEndDate())) < 0) {
            return Result.build(400, "\u672c\u6b21\u7ec3\u8f66\u8fd8\u6ca1\u7ed3\u675f\uff01");
        }
        return Result.ok();
    }
}
