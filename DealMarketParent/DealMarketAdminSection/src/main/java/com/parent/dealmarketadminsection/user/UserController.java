package com.parent.dealmarketadminsection.user;


import com.parent.dealmarketadminsection.exeptions.*;
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
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The User has been saved successfully.");
        return "redirect:/users";

    }


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
        List<Role> listRoles = userService.listRoles();
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User");
            model.addAttribute("listRoles", listRoles);

            return "user_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }


    }


}
