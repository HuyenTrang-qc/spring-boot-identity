package com.example.identity.mapper;

import com.example.identity.dto.request.UserCreationRequest;
import com.example.identity.dto.request.UserUpdateRequest;
import com.example.identity.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePartialUser(UserUpdateRequest request, @MappingTarget User user);
}
