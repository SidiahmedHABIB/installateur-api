package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.User;

import java.util.List;

public interface IUserService {
    public User creatNewUser(User user);
    public List<User> getAllUser();
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public void deleteUser(Long id);
    public User modifyUser(User user);
}
