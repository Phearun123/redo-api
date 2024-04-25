package com.kosign.redoapi.service.users;

import com.kosign.redoapi.common.api.StatusCode;
import com.kosign.redoapi.domain.user.UserInfo;
import com.kosign.redoapi.domain.user.UserInfoRepository;
import com.kosign.redoapi.enums.StatusActive;
import com.kosign.redoapi.exception.BusinessException;
import com.kosign.redoapi.helper.AuthHelper;
import com.kosign.redoapi.payload.users.*;
import com.kosign.redoapi.properties.FileInfoConfig;
import com.kosign.redoapi.utils.BaseSpecification;
import com.kosign.redoapi.utils.PasswordUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileInfoConfig fileInfoConfig;

    @Override
    public void createUser(CreateUserRequest payload) {

        var userExist = userInfoRepository.findByUserName(payload.userName())
                .orElse(new UserInfo());
        if (StringUtils.isNotEmpty(userExist.getUsername())) {
            throw new BusinessException(StatusCode.USERNAME_EXISTED);
        }

        String rawPassword;
        try {
            rawPassword = passwordEncoder.encode(PasswordUtils.decrypt(payload.userPassword()));
        } catch (Exception e) {
           throw new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        var user = UserInfo.builder()
                .username(payload.userName())
                .userPassword(rawPassword)
                .fullName(payload.userFullName())
                .role(payload.role())
                .status(StatusActive.ACTIVATE.getValue())
                .build();
        userInfoRepository.save(user);
    }

    @Override
    public void updateUserById(Long usrId, UpdateUserRequest payload) throws Throwable {
        UserInfo user = userInfoRepository.findById(AuthHelper.getUserId()).orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));

        user.setFullName(payload.fullName());
        user.setRole(payload.role());

        userInfoRepository.save(user);
    }

    @Override
    public Object getUsers(String searchValue, Pageable pages) throws Throwable {

        Page<UserInfo> usersPage = userInfoRepository.findAll((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(searchValue)) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("fullName")), BaseSpecification.containUpperCase(searchValue)),
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("userName")), BaseSpecification.containUpperCase(searchValue))
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pages);

        List<UserResponse> userResponses = usersPage.stream()
                .map(user -> UserResponse.builder()
                        .id(AuthHelper.getUserId())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .role(user.getRole())
                        .sts(user.getStatus())
                        .userProfileImg(user.getUserProfileImg())
                        .baseUrl(fileInfoConfig.getBaseUrl())
                        .build()).toList();

        return UserMainResponse.builder()
                .data(userResponses)
                .page(usersPage)
                .build();
    }

    @Override
    public Object getUserById(Long usrId) throws Throwable {

        var user = userInfoRepository.findById(usrId).orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));

        return UserInfoResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .role(user.getRole())
                .sts(user.getStatus())
                .userProfileImg(user.getUserProfileImg())
                .baseUrl(fileInfoConfig.getBaseUrl())
                .build();
    }

    @Override
    public void updateUserPassword(Long usrId, UpdatePwdUserRequest payload) {

        var user = userInfoRepository.findById(usrId)
                .orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));

        String rawPassword;
        try {
            rawPassword = passwordEncoder.encode(PasswordUtils.decrypt(payload.newUserPassword()));
        } catch (Exception e) {
            throw new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        user.setUserPassword(rawPassword);
        userInfoRepository.save(user);
    }

    @Override
    public void disableUserById(Long usrId) {
        var user = userInfoRepository.findById(usrId)
                .orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));
        user.setStatus(StatusActive.DEACTIVATE.getValue());
        userInfoRepository.save(user);
    }

    @Override
    public void enableUserById(Long usrId) {
        var user = userInfoRepository.findById(usrId)
                .orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));
        user.setStatus(StatusActive.ACTIVATE.getValue());
        userInfoRepository.save(user);
    }

    @Override
    public void changeUserRole(Long usrId, UserRoleRequest payload) {
        var user = userInfoRepository.findById(usrId)
                .orElseThrow(()-> new BusinessException(StatusCode.USER_NOT_FOUND));
        user.setRole(payload.role());
        userInfoRepository.save(user);
    }


}
