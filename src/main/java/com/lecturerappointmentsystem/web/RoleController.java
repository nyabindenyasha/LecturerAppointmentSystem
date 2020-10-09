package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.local.requests.RoleCreateRequest;
import com.lecturerappointmentsystem.model.Roles;
import com.lecturerappointmentsystem.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "Roles")
@RequestMapping("v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    @ApiOperation("Get Roles")
    public Page<Roles> getAll(@PageableDefault(sort = "name") Pageable pageable,
                              @RequestParam(required = false) String search) {
        return roleService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All Roles")
    public Collection<Roles> getAll() {
        return roleService.findAll();
    }

    @GetMapping("/{roleId}")
    @ApiOperation("Get a Role by Id")
    public Roles getRole(@PathVariable long roleId) {
        return roleService.findById(roleId);
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation("Delete a Role by Id")
    public void delete(@PathVariable long roleId) {
        roleService.delete(roleId);
    }


    @PostMapping("")
    @ApiOperation("Create Role")
    public Roles create(@RequestBody RoleCreateRequest request) {
        return roleService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public Roles update(@RequestBody Roles request, @PathVariable long roleId) {
        if(request.getId() != roleId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return roleService.update(request);
    }

}

