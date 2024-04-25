package com.kosign.redoapi.domain.users;

import com.kosign.redoapi.domain.UpdatableEntity;
import com.kosign.redoapi.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;

@Getter
@Setter
@Table(name = "user_info")
@Entity
@NoArgsConstructor
public class UserInfo extends UpdatableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "usr_nm", length = 100,nullable = false,unique=true)
    private String username;
    @Column(name = "usr_pwd", length = 200,nullable = false)
    private String userPassword;
    @Column(name = "full_nm", length = 100, nullable = false)
    private String fullName;
    @Column(name = "usr_prof_img")
    private String userProfileImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10, nullable = false)
    private AuthProvider role;
    @Column(name = "sts", length = 1)
    @JdbcTypeCode(Types.CHAR)
    private String status;

    @Builder
    public UserInfo(Long id, String username, String userPassword, String fullName, String userProfileImg, AuthProvider role, String status) {
        this.id = id;
        this.username = username;
        this.userPassword = userPassword;
        this.fullName = fullName;
        this.userProfileImg = userProfileImg;
        this.role = role;
        this.status = status;
    }
}
