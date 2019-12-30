package fr.nextoo.micro.user.service.impl;

import fr.nextoo.micro.common.dto.user.UserDto;
import fr.nextoo.micro.user.dto.transformer.UserTransformer;
import fr.nextoo.micro.user.persistence.repository.UserRepository;
import fr.nextoo.micro.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    @Autowired
    public UserService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    @Override
    public Collection<UserDto> getAll() {
        return userRepository.findAll().stream().map(userTransformer::transformToDto).collect(Collectors.toList());
    }

}
