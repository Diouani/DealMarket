package com.parent.dealmarketadminsection.user;


import com.root.dealmarketshared.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String listAll(Model model) {

        List<User> userList = userService.listAll();
        model.addAttribute("listUsers", userList);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {


        List<Role> listRoles = userService.listRoles();


        User user = new User();
        user.setEnabled(true);


        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New User");

        return "user_form";
    }


    @PostMapping("/save")
    public String saveUser(User user , RedirectAttributes redirectAttributes) {
        System.out.println(user);
        userService.save(user);
        redirectAttributes.addFlashAttribute("message" , "The User has been saved successfully.");
        return "redirect:/users";
    }


}
