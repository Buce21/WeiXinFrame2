package me.test.weixin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/2/18.
 */
@Controller
@Scope("prototype")
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/loginRedirect")
    public ModelAndView loginRedirect() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
