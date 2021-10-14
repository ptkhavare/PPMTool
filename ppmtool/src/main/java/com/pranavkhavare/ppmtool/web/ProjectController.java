package com.pranavkhavare.ppmtool.web;

import com.pranavkhavare.ppmtool.Utility.ERRORUtility;
import com.pranavkhavare.ppmtool.domain.Project;
import com.pranavkhavare.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


//this is my end point, request will arrive here with the body
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    //@Valid removes 500 error and descriptive error
    //BindingResult interface invokes the validator on the object to check if there are error
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<Map<String, String>> errorMap = ERRORUtility.FieldErrorMap(result);
        if (errorMap != null) {
            return errorMap;
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
        projectService.deleteProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<>("Project with ID " + projectIdentifier + " was deleted",HttpStatus.OK);
    }

}
