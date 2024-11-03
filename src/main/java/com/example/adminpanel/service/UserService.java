package com.example.adminpanel.service;

import com.example.adminpanel.entity.Role;
import com.example.adminpanel.entity.User;
import com.example.adminpanel.repository.RoleRepository;
import com.example.adminpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }
}
