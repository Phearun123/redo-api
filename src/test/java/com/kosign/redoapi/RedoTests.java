package com.kosign.redoapi;

import com.kosign.redoapi.service.users.UserService;
import com.kosign.redoapi.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedoTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() throws Throwable {
        System.err.println("PWD : " + PasswordUtils.encrypt("Aa12345"));
        System.err.println("PWD : " + PasswordUtils.encrypt("Bb12345"));
        System.err.println("PWD : " + PasswordUtils.encrypt("Cc12345"));
//        UserRequest payload = new UserRequest("Dara","Yvo7fm4YflunXJ8XZHL2BQ==", "DaraRith", AuthProvider.ADMIN);
//        userService.createUser(payload);
    }

}
