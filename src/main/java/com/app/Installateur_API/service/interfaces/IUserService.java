package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.LoginResponse;
import com.app.Installateur_API.entity.page.PageUser;
import com.app.Installateur_API.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IUserService {
    public boolean creatNewUser(User user);
    public PageUser getPageAllUser(int page, int size);
    public List<User> getAllUser();
    public User getUserById(Long id);
    public User loginUser(String email );
    public boolean deleteUser(Long id);
    public User modifyUser(User user);
    public User modifyProfile(Long id, MultipartFile image)throws IOException;
}
