package com.kosign.redoapi.service.users;

import com.kosign.redoapi.payload.users.CreateUserRequest;
import com.kosign.redoapi.payload.users.UpdatePwdUserRequest;
import com.kosign.redoapi.payload.users.UpdateUserRequest;
import com.kosign.redoapi.payload.users.UserRoleRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void createUser(CreateUserRequest payload);
    void updateUserById(Long usrId, UpdateUserRequest payload) throws Throwable;
    Object getUsers(String searchValue, Pageable pages) throws Throwable;
    Object getUserById(Long usrId) throws Throwable;
    void updateUserPassword(Long usrId, UpdatePwdUserRequest payload);
    void disableUserById(Long usrId);
    void enableUserById(Long usrId);
    void changeUserRole(Long usrId, UserRoleRequest payload);
}
