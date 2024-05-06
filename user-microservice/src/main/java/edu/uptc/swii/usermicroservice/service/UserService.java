package edu.uptc.swii.usermicroservice.service;

import edu.uptc.swii.usermicroservice.entity.User;
import edu.uptc.swii.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserbyId(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        User userNew= userRepository.save(user);
        return userNew;
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }


}
