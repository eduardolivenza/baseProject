package com.eolivenza.modules.baseProject.application.users;

import com.eolivenza.modules.baseProject.application.Command;

public class AddUserCommand implements Command {

    private String email;
    private String userPassword;
    private String userName;
    private String userSurname;

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.userName;
    }
}
