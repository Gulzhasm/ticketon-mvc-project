package com.ticketon.service.impl;

import com.ticketon.dto.ProjectDTO;
import com.ticketon.dto.TaskDTO;
import com.ticketon.dto.UserDTO;
import com.ticketon.enums.Status;
import com.ticketon.service.ProjectService;
import com.ticketon.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {
    TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {
        if (object.getProjectStatus() == null) {
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(), object);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        ProjectDTO projectDTO = findById(object.getProjectCode());

        if (object.getProjectStatus() == null)
            object.setProjectStatus(projectDTO.getProjectStatus());
        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.COMPLETE);
        super.save(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        // build all projects with all constructor

        List<ProjectDTO> projectDTOList =
                findAll()
                        .stream().
                        filter(project -> project.getAssignManager().equals(manager))
                        .map(project -> {

                            List<TaskDTO> tasks = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int) tasks
                                    .stream()
                                    .filter(t -> t.getProject().equals(project) &&
                                            t.getTaskStatus() == Status.COMPLETE).count();
                            int unfinishedTaskCounts = (int) tasks
                                    .stream()
                                    .filter(t -> t.getProject().equals(project) &&
                                            t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project;
                        }).collect(Collectors.toList());

        return projectDTOList;
    }
}
