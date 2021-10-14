package com.pranavkhavare.ppmtool.services;

import com.pranavkhavare.ppmtool.domain.Project;
import com.pranavkhavare.ppmtool.exceptions.ProjectDoesNotExistException;
import com.pranavkhavare.ppmtool.exceptions.ProjectIdentifierException;
import com.pranavkhavare.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    //do not add in controller, keep controller for routing only
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Creates or Updates a New Project
     * @param project
     * @return
     */
    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdentifierException(" Project Identifier " + project.getProjectIdentifier().toUpperCase() + " is duplicate!");
        }
    }

    /**
     * Finds new project By Project Identifier
     * Why not use hibernate query? Hibernate query vs Repository google search.
     * Where is findByProjectIdentifier implementation?
     * @param projectIdentifier
     * @return
     */
    public Project findByProjectIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectDoesNotExistException("Project does not exist");
        }
        return projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    }

    /**
     * Finds All Projects
     * @return
     */
    public Iterable<Project> findAllProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        if (projects.iterator().hasNext()) {
            return projects;
        } else {
            throw new ProjectDoesNotExistException("No Projects Created");
        }
    }

    /**
     * Deletes Project By Identifier
     * @param projectIdentifier
     */
    public void deleteProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project != null) {
            projectRepository.delete(project);
        } else {
            throw new ProjectDoesNotExistException("Project Does Not Exist");
        }
    }

    //todo instead of delete create discontinue functionality

}
