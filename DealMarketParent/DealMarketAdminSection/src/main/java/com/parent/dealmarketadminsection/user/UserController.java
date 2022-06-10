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



    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id,
                             Model model,
                             RedirectAttributes redirectAttributes) {



        try {
            userService.delete(id);

            redirectAttributes.addFlashAttribute("messageSuccess",
                    "The user ID " + id + " has been deleted successfully");
        } catch (UserNotFoundException ex) {


            redirectAttributes.addFlashAttribute("messageError", ex.getMessage());
        }

        return "redirect:/users";
    }


    @GetMapping("/users/{id}/enabled/{status}")
    public String updateUserEnabledStatus(@PathVariable("id") Long id,
                                          @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {


        userService.updateUserEnabledStatus(id, enabled);


        String status = enabled ? "enabled" : "disabled";


        String message = "The user ID " + id + " has been " + status;


        if(message.contains("enabled")) {
            redirectAttributes.addFlashAttribute("messageSuccess", message);
        }else {
            redirectAttributes.addFlashAttribute("messageError", message);
        }


        return "redirect:/users";
    }




}
