package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.application.users.AddUserCommand;
import com.eolivenza.modules.baseProject.application.users.ValidateUserCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.UsersResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.UserResource;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(value = "User")
@RestController
public class UsersController {

    private final UsersResourceMapper usersResourceMapper;
    private final QueryHandler<Class<String>, Optional<User>> getUserQueryHandler;
    private final QueryHandler<Class<Void>, List<User>> getAllUsersQueryHandler;
    private final CommandHandler<AddUserCommand> addUserCommandCommandHandler;
    private final CommandHandler<ValidateUserCommand> validateUserCommandCommandHandler;


    @Autowired
    public UsersController(
            QueryHandler<Class<String>, Optional<User>> getUserQueryHandler,
            UsersResourceMapper usersResourceMapper,
            CommandHandler<AddUserCommand> addUserCommandCommandHandler,
            CommandHandler<ValidateUserCommand> validateUserCommandCommandHandler,
            QueryHandler<Class<Void>, List<User>> getAllUsersQueryHandler) {
        this.getUserQueryHandler = getUserQueryHandler;
        this.usersResourceMapper = usersResourceMapper;
        this.addUserCommandCommandHandler = addUserCommandCommandHandler;
        this.validateUserCommandCommandHandler = validateUserCommandCommandHandler;
        this.getAllUsersQueryHandler = getAllUsersQueryHandler;
    }

    /**
     * Adds a new user
     *
     * @param userResource new user Resource
     */
    @ApiOperation(value = "Adds a new user to the system")
    @PostMapping(path = "/users")
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void addUser(
            @RequestBody UserResource userResource) {
        User  user = usersResourceMapper.toFirstType(userResource);
        AddUserCommand command = new AddUserCommand(user.getEmail(), user.getPassword(), user.getName(), user.getSurname());
        addUserCommandCommandHandler.accept(command);
    }

    @ApiOperation(value = "Validate an user to enter into the system")
    @PostMapping(path = "/users/authenticate")
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void validateUser(
            @RequestBody UserResource userResource) {
        User  user = usersResourceMapper.toFirstType(userResource);
        ValidateUserCommand command = new ValidateUserCommand(user.getEmail(), user.getPassword());
        validateUserCommandCommandHandler.accept(command);
    }

    @ApiOperation(value = "Validate an user to enter into the system")
    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public List<UserResource> getAllUsers() {
        List<User> usersList = getAllUsersQueryHandler.apply(Void.TYPE);
        return usersList.stream().map(usersResourceMapper::toSecondType).collect(Collectors.toList());
    }

}
