package com.jobtest.me.user_management_account.exceptions;

public class UsernameExistException extends RuntimeException{
    public UsernameExistException(String message) {
        super(message);
    }
}
