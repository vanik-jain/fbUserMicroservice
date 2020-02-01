package com.facebookclone.fbuser.service;


import com.facebookclone.fbuser.collections.BusinessUser;
import com.facebookclone.fbuser.collections.User;
import com.facebookclone.fbuser.dto.BusinessUserDTO;
import com.facebookclone.fbuser.dto.FriendRequestDTO;
import com.facebookclone.fbuser.dto.SearchDTO;
import com.facebookclone.fbuser.dto.UserDTO;
import com.facebookclone.fbuser.repository.BusinessUserRepository;
import com.facebookclone.fbuser.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessUserRepository businessUserRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public UserDTO saveUser(User user) {

        User user1 = userRepository.save(user);
        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(user1, userDTO);
        SearchDTO searchDTO = new SearchDTO();
        BeanUtils.copyProperties(user1, searchDTO);

        try {
            kafkaTemplate.send("kafka3", (new ObjectMapper()).writeValueAsString(searchDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return userDTO;
    }

    @Override
    public BusinessUserDTO saveBusinessUser(BusinessUser businessUser) {

        BusinessUser businessUser1 = businessUserRepository.save(businessUser);
        BusinessUserDTO businessUserDTO = new BusinessUserDTO();
        BeanUtils.copyProperties(businessUser1, businessUserDTO);

        return businessUserDTO;
    }

    @Override
    public void sendFriendRequest(FriendRequestDTO friendRequestDTO) {

        Optional<User> optionalUser = userRepository.findById(friendRequestDTO.getUserId());
        Optional<User> optionalFriend = userRepository.findById(friendRequestDTO.getFriendId());
        User user;
        User friend;

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            user = optionalUser.get();
            friend = optionalFriend.get();
            if (user.getDisplayType().equals("public")) {
                user.getFriendIds().add(friendRequestDTO.getFriendId());
                friend.getFriendIds().add(friendRequestDTO.getUserId());
            } else {
                user.getPendingFriendIds().add(friendRequestDTO.getFriendId());
            }


        }

    }

    @Override
    public void acceptFriendRequest(FriendRequestDTO friendRequestDTO) {
        Optional<User> optionalUser = userRepository.findById(friendRequestDTO.getUserId());
        Optional<User> optionalFriend = userRepository.findById(friendRequestDTO.getFriendId());
        User user;
        User friend;
        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            user = optionalUser.get();
            friend = optionalFriend.get();

            if (user.getDisplayType().equals("private")) {
                if (friendRequestDTO.getUserId() != null) {
                    user.getFriendIds().add(friendRequestDTO.getFriendId());
                    friend.getFriendIds().add(friendRequestDTO.getUserId());
                }
            }

        }

    }

    @Override
    public List<User> getFriendList(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        HashSet<String> friendIDs = user.getFriendIds();
        List<User> userList = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAllById(friendIDs);
        iterable.forEach(userList::add);
        return userList;
    }

    @Override
    public User findUserById(String userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    public BusinessUser findBusinessUserById(String BusinessUserId) {
        return null;
    }

    @Override
    public List<User> getMutualFriends(FriendRequestDTO friendRequestDTO) {

        Optional<User> optionalUser = userRepository.findById(friendRequestDTO.getUserId());
        Optional<User> optionalFriend = userRepository.findById(friendRequestDTO.getFriendId());
        User user = new User();
        User friend = new User();
        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            user = optionalUser.get();
            friend = optionalFriend.get();
        }

        HashSet<String> userListIds = user.getFriendIds();
        HashSet<String> friendListIds = friend.getFriendIds();
        userListIds.retainAll(friendListIds);


        List<User> userList = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAllById(userListIds);
        iterable.forEach(userList::add);
        return userList;

    }


}
