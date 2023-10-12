package com.ticketon.converter;


import com.ticketon.dto.TaskDTO;
import com.ticketon.service.TaskService;
import org.springframework.core.convert.converter.Converter;

public class TaskDTOConverter implements Converter<String, TaskDTO> {
    TaskService taskService;

    public TaskDTOConverter(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public TaskDTO convert(String source) {
        return taskService.findById(Long.parseLong(source));
    }
}
