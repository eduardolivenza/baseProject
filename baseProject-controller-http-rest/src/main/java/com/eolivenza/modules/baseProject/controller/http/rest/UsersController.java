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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;


@Api(value = "User")
@RestController
public class UsersController {

    private final UsersResourceMapper usersResourceMapper;
    private final QueryHandler<Class<String>, Optional<User>> getUserQueryHandler;
    private final CommandHandler<AddUserCommand> addUserCommandCommandHandler;
    private final CommandHandler<ValidateUserCommand> validateUserCommandCommandHandler;


    @Autowired
    public UsersController(
            QueryHandler<Class<String>, Optional<User>> getUserQueryHandler,
            UsersResourceMapper usersResourceMapper,
            CommandHandler<AddUserCommand> addUserCommandCommandHandler, CommandHandler<ValidateUserCommand> validateUserCommandCommandHandler) {
        this.getUserQueryHandler = getUserQueryHandler;
        this.usersResourceMapper = usersResourceMapper;
        this.addUserCommandCommandHandler = addUserCommandCommandHandler;
        this.validateUserCommandCommandHandler = validateUserCommandCommandHandler;
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
    @PostMapping(path = "/users/login")
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void validateUser(
            @RequestBody UserResource userResource) {
        User  user = usersResourceMapper.toFirstType(userResource);
        ValidateUserCommand command = new ValidateUserCommand(user.getEmail(), user.getPassword());
        validateUserCommandCommandHandler.accept(command);
    }

}
