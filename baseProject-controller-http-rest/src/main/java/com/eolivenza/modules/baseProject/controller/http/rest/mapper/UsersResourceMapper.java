package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.UserResource;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UsersResourceMapper implements ResourceMapper<User, UserResource> {

    @Override
    public User toFirstType(UserResource object) {
        return new User()
                .setEmail(object.email)
                .setName(object.firstName)
                .setSurname(object.lastName)
                .setPassword(object.password);
    }

    @Override
    public UserResource toSecondType(User object) {
        return new UserResource(
            object.getName(),
            object.getSurname(),
            object.getEmail(),
            object.getPassword());
    }
}
