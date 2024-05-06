package com.kosign.redoapi.service.profile;

import com.kosign.redoapi.payload.profile.UpdateProfileRequest;

public interface ProfileService {
    Object getProfile() throws Throwable;

    void updateProfie(UpdateProfileRequest payload);
}
