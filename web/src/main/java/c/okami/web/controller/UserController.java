package c.okami.web.controller;

import c.okami.core.domain.User;
import c.okami.web.annotation.NeedLogin;
import c.okami.web.interceptor.OkamiInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by qqs on 2016/10/9.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @NeedLogin
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Integer id,Map map,HttpSession session){
        User user = (User) session.getAttribute(OkamiInterceptor.USER_KEY);
        System.out.println(user.getName());
        return "user/detail";
    }
}
