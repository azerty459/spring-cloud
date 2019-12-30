package fr.nextoo.micro.common.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private long id;
    private String username;
    private String password;

}
