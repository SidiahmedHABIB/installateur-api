package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.ImageData;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.repository.UserRepository;
import com.app.Installateur_API.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
    public List<User> getAllUser() {
        return userRepository.findAll();
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
        User userUpdate = new User();
        userUpdate.setId(user.getId());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setImageUser(user.getImageUser());
        userUpdate.setCreatAt(user.getCreatAt());
        userUpdate.setUpdateAt(new Date());
        return userRepository.save(userUpdate);
    }

    @Override
    public User modifyProfile(User user, MultipartFile image) throws IOException {
        ImageData imageData = storageService.uploadImage(image);
        User profileUpdate = new User();
        profileUpdate.setId(user.getId());
        profileUpdate.setFirstName(user.getFirstName());
        profileUpdate.setLastName(user.getLastName());
        profileUpdate.setEmail(user.getEmail());
        profileUpdate.setPassword(user.getPassword());
        profileUpdate.setImageUser(imageData);
        profileUpdate.setCreatAt(user.getCreatAt());
        profileUpdate.setUpdateAt(new Date());
        return userRepository.save(profileUpdate);
    }
}
