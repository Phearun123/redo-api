package com.kosign.redoapi.service.profile;

import com.kosign.redoapi.common.api.StatusCode;
import com.kosign.redoapi.domain.users.UserInfoRepository;
import com.kosign.redoapi.enums.StatusActive;
import com.kosign.redoapi.exception.BusinessException;
import com.kosign.redoapi.helper.AuthHelper;
import com.kosign.redoapi.payload.profile.ProfileResponse;
import com.kosign.redoapi.payload.profile.UpdateProfileRequest;
import com.kosign.redoapi.properties.FileInfoConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserInfoRepository userInfoRepository;
    private final FileInfoConfig fileInfoConfig;

    @Override
    public Object getProfile() throws Throwable {

        var user = userInfoRepository.findById(AuthHelper.getUserId()).orElseThrow(() -> new BusinessException(StatusCode.USER_NOT_FOUND));

        return ProfileResponse.builder()
                .fullUsername(user.getUsername())
                .fullUsername(user.getFullName())
                .userRole(user.getRole())
                .userProfileImg(user.getUserProfileImg())
                .status(user.getStatus())
                .baseUrl(fileInfoConfig.getBaseUrl())
                .build();
    }

    @Override
    public void updateProfie(UpdateProfileRequest payload) {

        var user = userInfoRepository.findById(AuthHelper.getUserId()).orElseThrow(() -> new BusinessException(StatusCode.USER_NOT_FOUND));

        user.setFullName(payload.fullName());
        user.setUserProfileImg(payload.userProfileImag());

        userInfoRepository.save(user);

    }
}
