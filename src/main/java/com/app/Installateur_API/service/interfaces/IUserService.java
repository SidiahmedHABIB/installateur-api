package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.PageUser;
import com.app.Installateur_API.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    public User creatNewUser(User user);
    public PageUser getPageAllUser(int page, int size);
    public User getUserById(Long id);
    public User loginUser(String email ,String password);
    public void deleteUser(Long id);
    public User modifyUser(User user);
    public User modifyProfile(Long id, MultipartFile image)throws IOException;
}
