package com.parent.dealmarketadminsection.user;


import com.root.dealmarketshared.test.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}

