package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    public User creatNewUser(User user);
    public List<User> getAllUser();
    public User getUserById(Long id);
    public User loginUser(String email ,String password);
    public void deleteUser(Long id);
    public User modifyUser(User user);
    public User modifyProfile(User user, MultipartFile image)throws IOException;
}
