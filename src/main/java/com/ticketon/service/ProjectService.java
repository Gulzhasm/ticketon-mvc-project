package com.ticketon.service;

import com.ticketon.dto.ProjectDTO;
import com.ticketon.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO, String>{

    void complete(ProjectDTO projectDTO);

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);

}
