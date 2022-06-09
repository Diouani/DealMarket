package com.parent.dealmarketadminsection.user;

import com.root.dealmarketshared.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

}
