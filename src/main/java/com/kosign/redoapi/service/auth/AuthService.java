package com.kosign.redoapi.service.auth;

import com.kosign.redoapi.payload.auth.AuthRequest;

public interface AuthService {

    Object login(AuthRequest payload) throws Throwable;
}
