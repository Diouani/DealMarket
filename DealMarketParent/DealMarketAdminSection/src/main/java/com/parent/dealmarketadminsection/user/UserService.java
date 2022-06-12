package com.parent.dealmarketadminsection.user;

import com.parent.dealmarketadminsection.exeptions.*;
import com.root.dealmarketshared.test.Role;
import com.root.dealmarketshared.test.User;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }


    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }


    public void save(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if (isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }

        } else {
            encodePassword(user);
        }
        userRepository.save(user);
    }


    public boolean isEmailUnique(Long id, String email) {

        User userByEmail = userRepository.getUserByEmail(email);

        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            return false;
        } else {
            return Objects.equals(userByEmail.getId(), id);
        }


    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User get(Long id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {

            throw new UserNotFoundException("Could Not find any user with ID" + id);
        }

    }

    public void delete(Long id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);
        if (countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
        userRepository.deleteById(id);

    }
    public void updateUserEnabledStatus(Long id , boolean enabled){
        userRepository.updateEnableStatus(id,enabled);
    }

}
