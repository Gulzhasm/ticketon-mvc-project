package com.ticketon.controller;


import com.ticketon.dto.TaskDTO;
import com.ticketon.service.ProjectService;
import com.ticketon.service.TaskService;
import com.ticketon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task) {
        taskService.save(task);
        return "redirect:/task/create";
    }

}
