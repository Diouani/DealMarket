package com.parent.dealmarketadminsection.user;


import com.root.dealmarketshared.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String ListAll(Model model){

       List<User> userList = userService.listAll();
       model.addAttribute("listUsers",userList);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(){
        return "user_form";
    }



}
