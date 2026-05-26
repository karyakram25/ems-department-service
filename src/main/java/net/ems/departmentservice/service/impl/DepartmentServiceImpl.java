package net.ems.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.ems.departmentservice.exception.ResourceNotFoundException;
import net.ems.departmentservice.dto.DepartmentDto;
import net.ems.departmentservice.entity.Department;
import net.ems.departmentservice.mapper.DepartmentMapper;
import net.ems.departmentservice.repository.DepartmentRepository;
import net.ems.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department= DepartmentMapper.mapToDepartment(departmentDto);

        Department saved=repository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saved);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department= repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Department not found")
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return repository.findAll()
                .stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .toList();
    }
}
