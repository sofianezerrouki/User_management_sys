package com.jobtest.me.user_management_account.models;


import com.fasterxml.jackson.annotation.JsonProperty;



public enum Role {

    @JsonProperty(value = "ROLE_USER")
    ROLE_USER,
    @JsonProperty(value = "ROLE_SUPERUSER")
    ROLE_SUPERUSER,
    @JsonProperty(value = "ROLE_ADMIN")
    ROLE_ADMIN,
    @JsonProperty(value = "ROLE_NEW")
    ROLE_NEW;

}
