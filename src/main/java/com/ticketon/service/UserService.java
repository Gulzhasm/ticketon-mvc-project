package com.ticketon.service;

import com.ticketon.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String> {
    List<UserDTO> findManagers();

    List<UserDTO> findAllEmployees();
}
