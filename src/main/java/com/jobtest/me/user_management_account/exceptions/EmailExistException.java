package com.jobtest.me.user_management_account.exceptions;

public class EmailExistException extends RuntimeException{
    public EmailExistException(String message) {
        super(message);
    }
}
