package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Project;
import com.astontech.hr.repositories.ProjectRep;
import com.astontech.hr.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRep projectRep;

    @Override
    public Iterable<Project> listAllProjects() {
        return projectRep.findAll();
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRep.findOne(id);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRep.save(project);
    }

    @Override
    public Iterable<Project> saveProjectList(Iterable<Project> projectIterable) {
        return projectRep.save(projectIterable);
    }

    @Override
    public void deleteEmployee(Integer id) {
        projectRep.delete(id);
    }
}
