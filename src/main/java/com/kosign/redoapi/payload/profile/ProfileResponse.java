package com.kosign.redoapi.payload.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.redoapi.enums.AuthProvider;
import com.kosign.redoapi.utils.ImageUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileResponse {

    @JsonProperty("urs_nm")
    private String username;
    @JsonProperty("full_nm")
    private String fullUsername;
    @JsonProperty("role")
    private AuthProvider userRole;
    @JsonProperty("usr_prof_img")
    private String userProfileImg;
    @JsonProperty("sts")
    private String status;


    @Builder
    public ProfileResponse(String username, String fullUsername, AuthProvider userRole, String userProfileImg, String status, String baseUrl) {
        this.username = username;
        this.fullUsername = fullUsername;
        this.userRole = userRole;
        this.userProfileImg = ImageUtil.getImageUrl(baseUrl, userProfileImg);
        this.status = status;
    }
}
