package guru.springframework.services;


import java.util.ArrayList;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import guru.springframework.domain.UserDto;
import guru.springframework.domain.UserEntity;
import guru.springframework.domain.UsersRepository;

import org.modelmapper.convention.MatchingStrategies;
@Service
public class UsersServiceImpl implements UsersService {
    //BCryptPasswordEncoder bCryptPasswordEncoder;
    UsersRepository usersRepository;
    @Autowired
    public UsersServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
            UsersRepository usersRepository) {
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(userEntity, UserDto.class);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, // Email verification status
                true, true,
                true, new ArrayList<>());
    }
}
