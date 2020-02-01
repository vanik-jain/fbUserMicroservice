package com.facebookclone.fbuser.repository;

import com.facebookclone.fbuser.collections.BusinessUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUserRepository extends MongoRepository<BusinessUser,String>
{

}
