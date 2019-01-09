package com.eolivenza.modules.baseProject.domain.model.user;

import com.eolivenza.modules.baseProject.domain.model.Entity;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class User  extends Entity<User> {

    private static final UUID USER_UUID =  UUID.fromString("cce8a203-e594-4c18-96bb-cc21eb2ab773");

    @NotNull
    private final UUID uuid;
    @NotBlank
    private String email;
    @NotNull
    private String password;
    private String name;
    private String surname;

    public User(){
        this.uuid = new UUID(USER_UUID.getMostSignificantBits(), USER_UUID.getLeastSignificantBits());
    }
    
    public UUID getUuid() {
        return uuid;
    }
    
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(User other) {
        return false;
    }
}
