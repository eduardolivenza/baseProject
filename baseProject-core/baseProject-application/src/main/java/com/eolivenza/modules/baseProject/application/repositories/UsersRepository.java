package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.user.User;

import java.util.UUID;

public interface UsersRepository extends Repository<User, UUID>, RetrieveRepository<User, UUID> {

    boolean exists(UUID uuid);
}
