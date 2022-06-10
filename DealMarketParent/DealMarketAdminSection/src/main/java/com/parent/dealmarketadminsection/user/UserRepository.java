package com.parent.dealmarketadminsection.user;

import com.root.dealmarketshared.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    @Query("select u from  User u WHERE u.email = :email")
     User getUserByEmail(@Param("email") String email);

    Long countById(Long id);

}
