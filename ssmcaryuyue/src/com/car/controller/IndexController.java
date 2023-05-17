// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController
{
    @RequestMapping({ "/index.html" })
    public String index() {
        return "redirect:/api/teacher/teacherList.html";
    }
}
