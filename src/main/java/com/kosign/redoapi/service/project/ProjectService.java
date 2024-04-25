package com.kosign.redoapi.service.project;

import com.kosign.redoapi.payload.project.ProjectNameRequest;

public interface ProjectService {

    void createProject(ProjectNameRequest payload);
}
