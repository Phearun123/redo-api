package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.redoapi.enums.AuthProvider;
import com.kosign.redoapi.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoResponse {
    @JsonProperty("usr_nm")
    private String username;
    @JsonProperty("full_nm")
    private String fullName;
    @JsonProperty("role")
    private AuthProvider role;
    @JsonProperty("sts")
    private String sts;
    @JsonProperty("usr_prof_img")
    private String userProfileImg;

    @Builder
    public UserInfoResponse(String username, String fullName, AuthProvider role, String sts, String userProfileImg, String baseUrl) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.sts = sts;
        this.userProfileImg = ImageUtil.getImageUrl(baseUrl, userProfileImg);
    }
}
