package com.ticketon.controller;


import com.ticketon.dto.ProjectDTO;
import com.ticketon.dto.TaskDTO;
import com.ticketon.dto.UserDTO;
import com.ticketon.service.ProjectService;
import com.ticketon.service.TaskService;
import com.ticketon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public String insertTask(@ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findAllEmployees());
            model.addAttribute("tasks", taskService.findAll());
            return "/task/create";
        }
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model) {
        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("tasks", taskService.findAll());
        return "task/update";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findAllEmployees());
            model.addAttribute("tasks", taskService.findAll());
            return "/task/update";
        }
        taskService.update(task);
        return "redirect:/task/create";
    }

}
