package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class UserTransformer implements DTOTransform<Users, UserDTO> {

    @Override
    public UserDTO toDTO(Users domainBase) {
        return new UserDTO(domainBase.getFirstName(),domainBase.getMiddleName(),domainBase.getLastName(),domainBase.getEmail(),domainBase.isActive());
    }

    public List<UserDTO> toDTO(List<Users> users){
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Users fromDTO(UserDTO baseDTO) {
        Users user = new Users();
        user.setFirstName(baseDTO.getFirstName());
        user.setMiddleName(baseDTO.getMiddleName());
        user.setLastName(baseDTO.getLastName());
        user.setEmail(baseDTO.getEmail());
        user.setPassword(baseDTO.getPassword());
        return user;
    }
}
