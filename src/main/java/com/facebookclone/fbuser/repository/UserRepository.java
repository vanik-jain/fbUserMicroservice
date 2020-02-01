package com.facebookclone.fbuser.repository;

import com.facebookclone.fbuser.collections.User;
import com.facebookclone.fbuser.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String>
{
//    List<UserDTO>findAllById(HashSet<Long> userIds);
}
