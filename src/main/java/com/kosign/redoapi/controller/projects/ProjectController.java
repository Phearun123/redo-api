package com.kosign.redoapi.controller.projects;


import com.kosign.redoapi.controller.RedoRestController;
import com.kosign.redoapi.payload.project.ProjectNameRequest;
import com.kosign.redoapi.service.project.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bo/v1/projects")
@RequiredArgsConstructor
public class ProjectController extends RedoRestController {

    private final ProjectService projectService;

    @PostMapping("")
    public Object createProject(@Valid @RequestBody ProjectNameRequest payload) {
        projectService.createProject(payload);
        return ok();
    }


}
