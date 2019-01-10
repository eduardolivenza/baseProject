package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.user.User;

public interface UsersRepository extends Repository<User, String>, RetrieveRepository<User, String> {

    boolean exists(String email);
}
