package me.test.weixin.controller;

import me.test.weixin.pojo.Users;
import me.test.weixin.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
@Controller
@Scope("prototype")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/getLogin",method = RequestMethod.POST)
    public ModelAndView loginRedirect(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("email") String username, @RequestParam("password") String password) {

        List<Users> user = usersService.findUser(username);
        if (null != user) {
            if (password.equals(user.get(0).getPassworld())) {
                request.setAttribute("state","ok");
            }
        }

        if ("ok".equals(request.getAttribute("state"))) {
            return new ModelAndView("redirect:/html/index.html");
        } else {
            return new ModelAndView("redirect:/html/login.html");
        }

    }
}
