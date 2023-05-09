package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.entity.page.PageUser;
import com.app.Installateur_API.repository.AppRoleRepository;
import com.app.Installateur_API.repository.AppUserRepository;
import com.app.Installateur_API.repository.UserRepository;
import com.app.Installateur_API.service.interfaces.IUserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public String creatNewUser(User user) {
        if(appUserRepository.existsByEmail(user.getEmail())){
            return  "Error: email is already taken!";
        }
        AppRole role = appRoleRepository.findById(1l).get();
        List<AppRole> roleList =new ArrayList<>();
        roleList.add(role);
        AppUser newUser = new AppUser();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setAppRoles(roleList);
        appUserRepository.save(newUser);
        userRepository.save(user);
        return  "Technician registered successfully!";

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
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public LoginResponse loginUser(String email, String password) {
         User user = userRepository.findByEmailAndPassword(email,password).orElse(null);
         if(user!=null){
             return new LoginResponse("true",user);
         }
         else {
             return new LoginResponse("false",user);
         }

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
