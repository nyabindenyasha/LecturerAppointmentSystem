package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.local.requests.DepartmentCreateRequest;
import com.lecturerappointmentsystem.local.requests.DepartmentUpdateRequest;
import com.lecturerappointmentsystem.model.Department;
import com.lecturerappointmentsystem.model.Facaulty;
import com.lecturerappointmentsystem.repo.DepartmentRepository;
import com.lecturerappointmentsystem.repo.FacaultyRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
class DepartmentServiceImpl extends BaseServiceImpl<Department, DepartmentCreateRequest, DepartmentUpdateRequest> implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final FacaultyRepository facaultyRepository;

    DepartmentServiceImpl(DepartmentRepository departmentRepository, FacaultyRepository facaultyRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
        this.facaultyRepository = facaultyRepository;
    }

    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }

    @Override
    public Department create(DepartmentCreateRequest request) {

        val detailsExists = departmentRepository.existsByName(request.getName());

        if (detailsExists) {
            throw new InvalidRequestException("Department with the same name already exists");
        }

        Optional<Facaulty> facaulty = facaultyRepository.findById(request.getFacaultlyId().longValue());

        request.setFacaulty(facaulty.get());

        val role = Department.fromCommand(request);

        return departmentRepository.save(role);
    }


    @Override
    public Department update(DepartmentUpdateRequest request) {

        val detailsExists = departmentRepository.existsByName(request.getName());

        if (!detailsExists) {
            throw new InvalidRequestException("Department not found");
        }

        val department = findById(request.getId());

        Optional<Facaulty> facaulty = facaultyRepository.findById(request.getFacaultlyId());

        request.setFacaulty(facaulty.get());

        department.update(request);

        return departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }

}

