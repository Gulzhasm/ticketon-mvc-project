package com.ticketon.service;

import com.ticketon.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO, String>{

    void complete(ProjectDTO projectDTO);
}
