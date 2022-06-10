package com.parent.dealmarketadminsection.user;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.repository.query.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;


    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(Long id, String email) {
       return userService.isEmailUnique(id ,email) ? "OK"  : "Duplicated";
    }
}
