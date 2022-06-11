package com.parent.dealmarketadminsection.user;

import com.root.dealmarketshared.entity.test.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    @Query("select u from  User u WHERE u.email = :email")
     User getUserByEmail(@Param("email") String email);

    Long countById(Long id);

    @Query("update User u SET u.enabled = ?2 WHERE u.id = ?1")
    @Modifying
    void updateEnableStatus(Long id , boolean enabled);

}
