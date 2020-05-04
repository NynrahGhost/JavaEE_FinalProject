package com.github.NynrahGhost.finalProject.database.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.NynrahGhost.finalProject.database.entities.PermissionEntity;
import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.repositories.UserRepository;
import com.github.NynrahGhost.finalProject.dto.UserDto;
import com.github.NynrahGhost.finalProject.exceptions.UserAlreadyExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        return User.builder()
            .username(username)
            .password(user.getPassword())
            .authorities(mapAuthorities(user.getPermissions()))
            .build();
    }
    
    public UserEntity registerNewUserAccount(UserDto userDto) 
  	      throws UserAlreadyExistException {
  	         
  	        if (emailExist(userDto.getEmail())) {  
  	            throw new UserAlreadyExistException(
  	              "There is an account with that email address: "
  	              +  userDto.getEmail());
  	        }
  	        
  	        User us;
  	        

  	        UserEntity user = new UserEntity();
  	        user.setEmail(userDto.getEmail());
  	        user.setUsername(userDto.getUsername());
  	        user.setPassword(userDto.getPassword());
  	        //user.setPermissions(Permission.VIEW_MEMBER);
  	        return user;
    }
    
    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private static List<GrantedAuthority> mapAuthorities(final List<PermissionEntity> permissions) {
        return permissions.stream()
            .map(PermissionEntity::getPermission)
            .map(Enum::name)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toUnmodifiableList());
    }
}