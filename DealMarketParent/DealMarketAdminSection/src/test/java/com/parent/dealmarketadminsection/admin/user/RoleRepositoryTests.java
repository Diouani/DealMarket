package com.parent.dealmarketadminsection.admin.user;


import com.parent.dealmarketadminsection.user.*;
import com.root.dealmarketshared.entity.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.annotation.*;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;


    @Test
    void testCreateRole(){
        Role role = new Role();
        role.setName("Admin");
        role.setDescription("manage everything");
        Role createdRole = roleRepository.save(role);
        assertThat(createdRole.getId()).isPositive();
    }

    @Test
    void testCreateAllRoles(){
        Role rolesSalesperson = new Role("Salesperson","manage product price , customers , shipping , orders and sales report  ");
        Role roleEditor = new Role("Editor","manage categories , brands , products , articles and  menus");
        Role roleShipper = new Role("Shipper","view products , view orders and update order status ");
        Role roleAssistant = new Role("Assistant","manage questions and reviews");

        roleRepository.saveAll(List.of(rolesSalesperson,roleEditor,roleShipper,roleAssistant));


    }
}
