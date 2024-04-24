package com.kosign.redoapi.controller.auth;

import com.kosign.redoapi.controller.RedoRestController;
import com.kosign.redoapi.payload.auth.AuthRequest;
import com.kosign.redoapi.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ca/v1/auth")
@RequiredArgsConstructor
public class AuthController extends RedoRestController {

    private final AuthService authService;
    @PostMapping("/login")
    public Object login(@RequestBody @Valid AuthRequest payload) throws Throwable {
        return ok(authService.login(payload));
    }

}
