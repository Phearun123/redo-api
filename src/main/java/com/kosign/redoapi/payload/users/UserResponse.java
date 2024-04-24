package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.redoapi.enums.AuthProvider;
import com.kosign.redoapi.enums.StatusActive;
import com.kosign.redoapi.utils.ImageUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {

        private Long id;
        @JsonProperty("usr_nm")
        @NotNull
        @NotBlank
        private String username;
        @JsonProperty("full_nm")
        @NotBlank
        private String fullName;
        @JsonProperty("role")
        @NotBlank
        private AuthProvider role;
        @JsonProperty("sts")
        @NotBlank
        private String sts;
        @JsonProperty("usr_prof_img")
        private String userProfileImg;

        @Builder
        public UserResponse(Long id, String username, String fullName, AuthProvider role,String sts, String userProfileImg,String baseUrl) {
                this.id = id;
                this.username = username;
                this.fullName = fullName;
                this.role = role;
                this.sts = sts;
                this.userProfileImg = ImageUtil.getImageUrl(baseUrl,userProfileImg);
        }
}
