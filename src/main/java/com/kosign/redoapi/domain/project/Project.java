package com.kosign.redoapi.domain.project;

import com.kosign.redoapi.domain.Auditable;
import com.kosign.redoapi.domain.UpdatableEntity;
import com.kosign.redoapi.enums.AuthProvider;
import com.kosign.redoapi.enums.ProjectStatus;
import com.kosign.redoapi.enums.StatusActive;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PRJ")
@NoArgsConstructor
public class Project extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prj_id")
    private Long id;

    @Column(name = "nm", length = 20)
    private String name;

    @Column(name = "sts", length = 1)
    private ProjectStatus status;

    @Builder
    public Project(Long id, String name, ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
