package com.example.bankmandiriproject.service;

import com.example.bankmandiriproject.model.User;
import com.example.bankmandiriproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findById(String userId){
        return userRepository.findById(userId).get();
    }

    public User create(User userAccount) {
        User updateUser = userRepository.findUserByUsername(userAccount.getUserUsername());
        if(updateUser!= null && updateUser.getUserUsername().equals(userAccount.getUserUsername())){
            updateUser.setUserFirstName(userAccount.getUserFirstName());
            updateUser.setUserLastName(userAccount.getUserLastName());
            updateUser.setUserPassword(userAccount.getUserPassword());
            updateUser.setUserEmail(userAccount.getUserEmail());
        } else if(updateUser==null) {
            updateUser = new User();
            updateUser.setUserFirstName(userAccount.getUserFirstName());
            updateUser.setUserLastName(userAccount.getUserLastName());
            updateUser.setUserPassword(userAccount.getUserPassword());
            updateUser.setUserEmail(userAccount.getUserEmail());
            updateUser.setUserDob(userAccount.getUserDob());
            updateUser.setUserUsername(userAccount.getUserUsername());
        }

        return userRepository.save(updateUser);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
