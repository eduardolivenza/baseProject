package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "Text for describing the user")
public class UserResource {


    @ApiModelProperty(required = true, value = "Relates to the highest level customer identification. The CustomerID value can come from a global SAP, customer master record, local identifier", example = "Bayley")
    public String name;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String surname;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String email;

    @ApiModelProperty(required = true, value = "Unique identifier related to the sales organization or the country affiliate", example = "34")
    public String password;

    public UserResource(){}

    public UserResource( String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
