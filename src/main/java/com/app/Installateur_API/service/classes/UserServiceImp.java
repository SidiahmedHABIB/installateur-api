package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.entity.page.PageUser;
import com.app.Installateur_API.repository.UserRepository;
import com.app.Installateur_API.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public User creatNewUser(User user) {
        return userRepository.save(user);

    }

    @Override
    public PageUser getPageAllUser(int page, int size) {
        PageUser p= new PageUser();
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        p.setUsers(userPage.getContent());
        p.setTotalPages(userPage.getTotalPages());
        return p;
    }



    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User loginUser(String email,String password) {
        return userRepository.findByEmailAndPassword(email,password).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User modifyUser(User user) {
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User modifyProfile(Long id, MultipartFile image) throws IOException {
        ImageData imageData = storageService.uploadImage(image);
        User profileUpdate = getUserById(id);
        profileUpdate.setImageUser(imageData);
        profileUpdate.setUpdateAt(new Date());
        return userRepository.save(profileUpdate);
    }
}
