package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.repository.UserRepository;
import com.app.Installateur_API.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User creatNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User modifyUser(User user) {
        User userUpdate = new User();
        userUpdate.setId(user.getId());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setUpdateAt(new Date());
        return userRepository.save(userUpdate);
    }
}
