package com.example.demo.service.user_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.user.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.customer_service.CustomerServiceImpl;
import com.example.demo.utils.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ConvertToEntity convertToEntity;

    @Autowired
    private final ConvertToDTO convertToDTO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        String roleName = user.getRole().getName();
        List<GrantedAuthority> grantList = new ArrayList<>();
        grantList.add(new SimpleGrantedAuthority(roleName));

//        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), grantList);
        return CustomUserDetails.fromUser(user);
    }

    @Override
    public Integer save(UserDTO userDTO)
    {
        User user = convertToEntity.convertUserDTOToEntity(userDTO);
        userRepository.save(user);
        return user.getId();

    }

    @Override
    public Optional<UserDTO> findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(convertToDTO::convertUserToDTO);
    }
}
