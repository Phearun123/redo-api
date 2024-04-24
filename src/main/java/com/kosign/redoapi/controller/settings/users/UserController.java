package com.kosign.redoapi.controller.settings.users;

import com.kosign.redoapi.controller.RedoRestController;
import com.kosign.redoapi.payload.users.*;
import com.kosign.redoapi.service.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bo/v1/settings/users")
@RequiredArgsConstructor
public class UserController extends RedoRestController {

    private final UserService userService;

    @PostMapping("")
    public Object createUser(@RequestBody CreateUserRequest payload) {
        userService.createUser(payload);
        return ok();
    }

    @PutMapping("/{usr_id}")
    public Object updateUserById(@PathVariable Long usr_id, @RequestBody UpdateUserRequest payload) throws Throwable {
        userService.updateUserById(usr_id, payload);
        return ok();
    }

    @GetMapping("")
    public Object getUsers(
            @RequestParam(value = "page_num", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "search_value", required = false) String searchValue
    ) throws Throwable {
        Pageable pages = PageRequest.of(pageNumber, pageSize);
        return ok(userService.getUsers(searchValue, pages));
    }

    @GetMapping("/{usr_id}")
    public Object getUserById(@PathVariable Long usr_id) throws Throwable {
        return ok(userService.getUserById(usr_id));
    }

    @PatchMapping("/{usr_id}/change-password")
    public Object updateUserPassword(
            @PathVariable Long usr_id,
            @RequestBody UpdatePwdUserRequest payload
    ) {
        userService.updateUserPassword(usr_id, payload);
        return ok();
    }

    @PatchMapping("/{usr_id}/disable")
    public Object disableUserById(@PathVariable Long usr_id) {
        userService.disableUserById(usr_id);
        return ok();
    }

    @PatchMapping("/{usr_id}/enable")
    public Object enableUserById(@PathVariable Long usr_id) {
        userService.enableUserById(usr_id);
        return ok();
    }

    @PatchMapping("/{usr_id}/change-role")
    public Object changeUserRole(@PathVariable Long usr_id, @RequestBody UserRoleRequest payload) {
        userService.changeUserRole(usr_id, payload);
        return ok();
    }

}
