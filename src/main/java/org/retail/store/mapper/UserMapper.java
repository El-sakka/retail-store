package org.retail.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.retail.store.model.dto.UserDto;
import org.retail.store.model.entity.User;
import org.retail.store.model.request.UserRequest;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);

    UserRequest toUserRequest(User user);
    UserDto toUserDto(User user);
}
