package com.parent.dealmarketadminsection.admin.user;


import com.parent.dealmarketadminsection.user.*;
import com.root.dealmarketshared.entity.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.annotation.*;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    void testCreateUserWithOneRole() {

        Role roleAdmin = entityManager.find(Role.class, 3L);
        User userYoussef = new User("diouani.youssef@gmail.com", "youssef123", "Youssef", "Diouani");
        userYoussef.addRole(roleAdmin);

        User savedUser = userRepository.save(userYoussef);

        assertThat(savedUser.getId()).isPositive();
    }


    @Test
    void testCreateNewUserWithTwoRoles() {
        User userYasser = new User("yasser.jamaleddine@gmail.com", "yasser123", "Yasser", "JamalEddine");
        Role roleEditor = new Role(1L);
        Role roleAssistant = new Role(2L);
        userYasser.addRole(roleEditor);
        userYasser.addRole(roleAssistant);

        User savedUser = userRepository.save(userYasser);
        assertThat(savedUser.getId()).isPositive();
    }

    @Test
    void testListAllUsers(){
       Iterable<User> userList =  userRepository.findAll();

       //lambda method reference
       userList.forEach(System.out::println);

    }

    @Test
    void testGetUserById(){
       User thisUser = userRepository.findById(1L).get();
        System.out.println(thisUser.getEmail());
       assertThat(thisUser).isNotNull();
    }

    @Test
    void testUpdateUserDetails(){
        User thisUser = userRepository.findById(1L).get();
        thisUser.setEnabled(true);
        thisUser.setEmail("diouani.youssef@updated.com");
        userRepository.save(thisUser);
    }

    @Test
    void testUpdateUserRoles(){
        User userYasser = userRepository.findById(2L).get();
        Role roleEditor = new Role(5L);
        Role roleSalesperson= new Role(3L);
        userYasser.getRoles().remove(roleEditor);
        userYasser.addRole(roleSalesperson);

        userRepository.save(userYasser);

    }

    @Test
    void testDeleteUser(){
        User userYasser = userRepository.findById(5L).get();
        userRepository.delete(userYasser);

    }


    @Test
    void testGetUserByEmail(){
        String email ="diouani.youssef@gmail.com";
     User user =   userRepository.getUserByEmail(email);
        assertThat(user).isNotNull();


    }

    @Test
    void testCountById(){
        Long id = 1L;
        Long countById = userRepository.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }
    @Test
    void testDisableUser(){
        Long id = 1L;
        userRepository.updateEnableStatus(id,false);
    }

    @Test
    void testEnableUser(){
        Long id = 1L;
        userRepository.updateEnableStatus(id,true);

    }



}
