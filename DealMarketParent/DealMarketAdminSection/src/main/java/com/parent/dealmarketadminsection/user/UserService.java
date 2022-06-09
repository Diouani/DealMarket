package com.parent.dealmarketadminsection.user;

import com.root.dealmarketshared.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }


    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }


    public void save(User user) {
        userRepository.save(user);
    }


    public boolean isEmailUnique(String email) {

        User user = userRepository.getUserByEmail(email);

        return user == null;

    }
}
