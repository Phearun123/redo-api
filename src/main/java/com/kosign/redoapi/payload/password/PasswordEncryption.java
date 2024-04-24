package com.kosign.redoapi.payload.password;

public interface PasswordEncryption {

    String getPassword(String password) throws Exception;

}
