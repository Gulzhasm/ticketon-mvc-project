package com.ticketon.service.impl;

import com.ticketon.dto.RoleDTO;
import com.ticketon.service.CrudService;
import com.ticketon.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapService<RoleDTO, Long> implements RoleService {

    @Override
    public RoleDTO save(RoleDTO object) {
        return super.save(object.getId(), object);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(RoleDTO object) {
        super.update(object.getId(), object);
    }
}
