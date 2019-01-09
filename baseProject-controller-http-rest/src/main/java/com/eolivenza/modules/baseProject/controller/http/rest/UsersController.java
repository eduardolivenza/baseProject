package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommand;
import com.eolivenza.modules.baseProject.application.users.AddUserCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.UsersResourceMapper;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Api(value = "User")
@RestController
public class UsersController {

    private final UsersResourceMapper usersResourceMapper;
    private final QueryHandler<Class<String>, Optional<User>> getUserQueryHandler;
    private final CommandHandler<AddUserCommand> addUserCommandCommandHandler;


    @Autowired
    public UsersController(
            QueryHandler<Class<String>, Optional<User>> getUserQueryHandler,
            UsersResourceMapper usersResourceMapper,
            CommandHandler<AddUserCommand> addUserCommandCommandHandler) {
        this.getUserQueryHandler = getUserQueryHandler;
        this.usersResourceMapper = usersResourceMapper;
        this.addUserCommandCommandHandler = addUserCommandCommandHandler;
    }






}
