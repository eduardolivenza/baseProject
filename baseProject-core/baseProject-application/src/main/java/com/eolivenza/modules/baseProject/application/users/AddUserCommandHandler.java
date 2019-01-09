package com.eolivenza.modules.baseProject.application.users;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.configuration.FileNameNotValidException;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommand;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddUserCommandHandler implements CommandHandler<AddUserCommand> {
    private final UsersRepository usersRepository;

    private Logger logger = LoggerFactory.getLogger(AddUserCommandHandler.class);

    @Inject
    public AddUserCommandHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddUserCommand addUserCommand) {

        User newUser = toDomain(addUserCommand);
        logger.debug(" Element configuration validated");
        if (usersRepository.exists(Configuration.CONFIGURATION_UUID)) {
           /* User actualConfiguration = usersRepository.retrieve(Configuration.CONFIGURATION_UUID);
            actualConfiguration.overwriteWith(configurationWithNewValues);
            usersRepository.update(actualConfiguration);*/
        }
        else {
            usersRepository.create(newUser);
        }
    }

    private User toDomain(AddUserCommand addUserCommand) {
        return new User()
                .setEmail(addUserCommand.getEmail())
                .setName(addUserCommand.getUserName())
                .setSurname(addUserCommand.getUserSurname())
                .setPassword(addUserCommand.getUserPassword());
    }

    @Override
    public String getName() { return "Add user"; }

}


