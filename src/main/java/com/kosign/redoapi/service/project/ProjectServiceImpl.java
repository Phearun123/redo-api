package com.kosign.redoapi.service.project;

import com.kosign.redoapi.common.api.StatusCode;
import com.kosign.redoapi.domain.project.Project;
import com.kosign.redoapi.domain.project.ProjectRepository;
import com.kosign.redoapi.enums.ProjectStatus;
import com.kosign.redoapi.enums.StatusActive;
import com.kosign.redoapi.exception.BusinessException;
import com.kosign.redoapi.payload.project.ProjectNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    @Override
    public void createProject(ProjectNameRequest payload) {

        projectRepository.findPrjName(payload.projectName())
                        .ifPresent(prjName -> {
                            throw new BusinessException(StatusCode.PROJECT_NAME_EXIST);
                        });

        var data = Project.builder()
                .name(payload.projectName())
                .status(ProjectStatus.fromValue(ProjectStatus.APPROVE.getValue()))
                .build();

        projectRepository.save(data);
    }
}
