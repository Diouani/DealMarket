package com.parent.dealmarketadminsection.user;


import com.root.dealmarketshared.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}

