package fr.nextoo.micro.user.service;

import fr.nextoo.micro.common.dto.user.UserDto;

import java.util.Collection;

public interface IUserService {

    Collection<UserDto> getAll();

}
