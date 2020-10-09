package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.local.requests.ProgramCreateRequest;
import com.lecturerappointmentsystem.local.requests.ProgramUpdateRequest;
import com.lecturerappointmentsystem.model.Department;
import com.lecturerappointmentsystem.model.Facaulty;
import com.lecturerappointmentsystem.model.Programs;
import com.lecturerappointmentsystem.repo.DepartmentRepository;
import com.lecturerappointmentsystem.repo.FacaultyRepository;
import com.lecturerappointmentsystem.repo.ProgramRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ProgramServiceImpl extends BaseServiceImpl<Programs, ProgramCreateRequest, ProgramUpdateRequest> implements ProgramService {

    private final ProgramRepository programRepository;

    private final DepartmentRepository departmentRepository;

    private final FacaultyRepository facaultyRepository;

    ProgramServiceImpl(ProgramRepository programRepository, DepartmentRepository departmentRepository, FacaultyRepository facaultyRepository) {
        super(programRepository);
        this.programRepository = programRepository;
        this.departmentRepository = departmentRepository;
        this.facaultyRepository = facaultyRepository;
    }

    @Override
    protected Class<Programs> getEntityClass() {
        return Programs.class;
    }

    @Override
    public Programs create(ProgramCreateRequest request) {

        val detailsExists = programRepository.existsByName(request.getName());

        if (detailsExists) {
            throw new InvalidRequestException("Program with the same name already exists");
        }

        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());

        Optional<Facaulty> facaulty = facaultyRepository.findById(department.get().getFacaulty().getId());

        department.get().setFacaulty(facaulty.get());

        request.setDepartment(department.get());

        val program = Programs.fromCommand(request);

        return programRepository.save(program);
    }


    @Override
    public Programs update(ProgramUpdateRequest request) {

        val detailsExists = programRepository.existsByName(request.getName());

        if (!detailsExists) {
            throw new InvalidRequestException("Program not found");
        }

        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());

        Optional<Facaulty> facaulty = facaultyRepository.findById(department.get().getFacaulty().getId());

        department.get().setFacaulty(facaulty.get());

        request.setDepartment(department.get());

        val program = findById(request.getId());

        program.update(request);

        return programRepository.save(program);
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
