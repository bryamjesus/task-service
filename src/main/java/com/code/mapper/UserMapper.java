package com.code.mapper;

import com.code.dto.request.UserRequest;
import com.code.dto.response.UserResponse;
import com.code.model.User;
import com.code.model.UserProfile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User userRequestToUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);

        UserProfile userProfile = new UserProfile();
        if (request.getFirstName() != null) {
            userProfile.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            userProfile.setLastName(request.getLastName());
        }

        if (request.getDni() != null) {
            userProfile.setDni(request.getDni());
        }

        userProfile.setUser(user);
        user.setUserProfile(userProfile);

        return user;
    }

    public UserResponse userToUserResponse(User user) {
        UserResponse response = modelMapper.map(user, UserResponse.class);

        response.setFirstName(user.getUserProfile().getFirstName());
        response.setLastName(user.getUserProfile().getLastName());
        response.setDni(user.getUserProfile().getDni());

        return response;
    }

    public List<User> userRequestListToUserList(List<UserRequest> requests) {
        return requests.stream()
                .map(this::userRequestToUser)
                .collect(Collectors.toList());
    }

    public List<UserResponse> userListToUserResponseList(List<User> users) {
        return users.stream()
                .map(this::userToUserResponse)
                .collect(Collectors.toList());
    }
}
