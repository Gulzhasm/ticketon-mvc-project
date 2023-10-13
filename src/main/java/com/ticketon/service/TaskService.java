package com.ticketon.service;

import com.ticketon.dto.TaskDTO;
import com.ticketon.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{
    List<TaskDTO> findTasksByManager(UserDTO manager);
}
