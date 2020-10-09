package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.local.requests.DepartmentCreateRequest;
import com.lecturerappointmentsystem.local.requests.DepartmentUpdateRequest;
import com.lecturerappointmentsystem.model.Department;
import com.lecturerappointmentsystem.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "Departments")
@RequestMapping("v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    @ApiOperation("Get Departments")
    public Page<Department> getAll(@PageableDefault(sort = "name") Pageable pageable,
                                   @RequestParam(required = false) String search) {
        return departmentService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All Departments")
    public Collection<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{departmentId}")
    @ApiOperation("Get a Department by Id")
    public Department getDepartment(@PathVariable long departmentId) {
        return departmentService.findById(departmentId);
    }

    @DeleteMapping("/{departmentId}")
    @ApiOperation("Delete a Department by Id")
    public void delete(@PathVariable long departmentId) {
        departmentService.delete(departmentId);
    }


    @PostMapping("")
    @ApiOperation("Create Department")

    public Department create(@RequestBody DepartmentCreateRequest request) {
        return departmentService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public Department update(@RequestBody DepartmentUpdateRequest request, @PathVariable long departmentId) {
        if(request.getId() != departmentId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return departmentService.update(request);
    }
}
