// 
// 
// 

package com.car.interceptor;

import org.springframework.web.servlet.ModelAndView;
import com.car.pojo.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor
{
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String requestURI = request.getRequestURI();
        if (!requestURI.contains("/login") && !requestURI.contains("/register")) {
            final User user = (User)request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
                return false;
            }
        }
        return true;
    }
    
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
    }
    
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
    }
}
