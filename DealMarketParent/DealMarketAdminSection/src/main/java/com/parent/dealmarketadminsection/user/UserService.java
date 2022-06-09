package com.parent.dealmarketadminsection.user;

import com.root.dealmarketshared.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService  {


    @Autowired
    private UserRepository  userRepository;


    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }


}
