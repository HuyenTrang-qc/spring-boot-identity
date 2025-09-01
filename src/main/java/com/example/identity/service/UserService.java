package com.example.identity.service;

import com.example.identity.dto.request.UserCreationRequest;
import com.example.identity.dto.request.UserUpdateRequest;
import com.example.identity.entity.User;
import com.example.identity.exception.AppException;
import com.example.identity.exception.ErrorCode;
import com.example.identity.mapper.UserMapper;
import com.example.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
     UserRepository userRopository;
     UserMapper userMapper;

    public User createUser(UserCreationRequest request){

        if(userRopository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRopository.save(user);
    }

    public List<User> getUsers(){
        return userRopository.findAll();
    }

    public User getUser(String id){
        return userRopository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, UserUpdateRequest request){
        User user = getUser(id);
        userMapper.updateUser(user,request);

        return userRopository.save(user);
    }
    public User updatePartialUser(String id, UserUpdateRequest request){
        User user = getUser(id);
        userMapper.updatePartialUser(request, user);

        return userRopository.save(user);
    }

    public void deleteUser(String id){
        userRopository.deleteById(id);
    }

}
