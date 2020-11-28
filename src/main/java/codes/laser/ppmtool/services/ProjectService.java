/**
 * Created By Arjun Gautam .
 * Date: 2020-11-25
 * Time: 07:13
 * Project Name : ppmtool
 */
package codes.laser.ppmtool.services;

import codes.laser.ppmtool.exceptions.ProjectIDException;
import codes.laser.ppmtool.model.Project;
import codes.laser.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIDException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "'already exists.");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Project Id'" + projectId + "'does not exist.");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Cannot delete Project with ID'" + projectId + "'.This project does not exist.");
        }
        projectRepository.delete(project);

    }

}