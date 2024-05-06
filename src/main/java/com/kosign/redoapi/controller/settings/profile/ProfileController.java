package com.kosign.redoapi.controller.settings.profile;

import com.kosign.redoapi.controller.RedoRestController;
import com.kosign.redoapi.payload.profile.UpdateProfileRequest;
import com.kosign.redoapi.service.profile.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ca/v1/settings/profile")
@RequiredArgsConstructor
public class ProfileController extends RedoRestController {

    private final ProfileService profileService;

    @GetMapping("")
    public Object getProfile() throws Throwable {
        return ok(profileService.getProfile());
    }

    @PutMapping("")
    public Object updateProfile(@Valid @RequestBody UpdateProfileRequest payload) {
        profileService.updateProfie(payload);
        return ok();
    }

//    @PatchMapping("/change-password")
//    public Object changeProfilePassword(@Valid @RequestBody UpdateProfilePas)

}
