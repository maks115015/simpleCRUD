package com.spr.controller;

import com.spr.LoggerWrapper;
import com.spr.exception.UserNotFound;
import com.spr.model.User;
import com.spr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping(value = "/")
public class UserController extends WebMvcConfigurerAdapter {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index","/"},method= RequestMethod.GET)
    public String userListPage(WebRequest webRequest,Model model) {
        int pageNumber=1;
        if((webRequest.getParameter("page")!=null))
            pageNumber=Integer.parseInt(webRequest.getParameter("page"));
        LOG.info("userList findAll");
        Page<User> page = userService.findAll(new PageRequest(pageNumber - 1, 10));
        List<Integer>list=new ArrayList<>();
        for(int i=1; i<page.getTotalPages()+1;i++){
            list.add(i);
        }
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        return "index";
    }

    @RequestMapping(value="user", method= RequestMethod.POST)
    public String findByName(@RequestParam String searchName, final RedirectAttributes redirectAttributes,Model model) throws UserNotFound
    {
        LOG.info("find" + searchName);
        List<User> users = userService.getByName(searchName);
        if (users.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Wrong name");
            return "redirect:index";
        }
        else{
            model.addAttribute("users",users);
            return "user";
        }
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String newUserPage(Model model) {
        model.addAttribute("user",new User());
        return "edit";
    }

    @RequestMapping(value={"/edit","/user"}, method=RequestMethod.GET)
    public String editUserPage(HttpServletRequest request, Model model) {
        LOG.info("find" + getId(request));
        User user = userService.findById(getId(request));
        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping(value = {"/edit","/user/edit"},method=RequestMethod.POST)
    public String updateOrCreate(@Valid User user,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes, Model model) {
        String message;
        if (result.hasErrors()) {
            return "edit";
        }

        if (user.isNew()) {
            LOG.info("create " + user);
            userService.create(user);
            message= "New user "+ user.getName()+" was successfully created.";
        } else {
            try {
                LOG.info("update" + user);
                userService.update(user);
                 message= "New user "+ user.getName()+" was successfully updated.";
            } catch (UserNotFound userNotFound) {
                 message = "User not found.";
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/index";
    }



    @RequestMapping(value={"/delete","/user/delete"}, method=RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,
                                   final RedirectAttributes redirectAttributes) {
        String message;
        User user = null;
        LOG.info("delete " + getId(request));
        try {
            user = userService.delete(getId(request));
            message = "The user "+ user.getName()+" was successfully deleted.";
        } catch (UserNotFound userNotFound) {
            message = "User not found";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/index";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
