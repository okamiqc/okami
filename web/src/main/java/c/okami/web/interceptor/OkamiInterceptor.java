package c.okami.web.interceptor;

import c.okami.core.domain.User;
import c.okami.web.annotation.NeedLogin;
import c.okami.web.annotation.Select;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by qqs on 2016/10/9.
 */
public class OkamiInterceptor implements HandlerInterceptor {

    public final static String USER_KEY = "login_user_info";
    public final static String SELECT = "select";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Boolean flag = true;
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            if (handlerMethod.getMethod().isAnnotationPresent(NeedLogin.class)) {
                User user = (User) session.getAttribute(USER_KEY);
                if(user==null){
                    flag = false;
                    httpServletResponse.sendRedirect("/login");
                }
            }
            if (handlerMethod.getMethod().isAnnotationPresent(Select.class)) {
                Select select = handlerMethod.getMethod().getAnnotation(Select.class);
                if (select != null) {
                    httpServletRequest.setAttribute(SELECT, select.value());
                }
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
