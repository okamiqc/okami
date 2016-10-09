package c.okami.web.controller;

import c.okami.core.domain.User;
import c.okami.web.interceptor.OkamiInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by qqs on 2016/10/9.
 */
@Controller
public class MainController {
    @RequestMapping("login")
    public String login(){
        return "main/login";
    }
    @RequestMapping("/doLogin")
    public String doLogin(HttpSession session){
        User user = User.build(User.class).eq("id",1).one();
        session.setAttribute(OkamiInterceptor.USER_KEY,user);
        return "main/login";
    }
}
