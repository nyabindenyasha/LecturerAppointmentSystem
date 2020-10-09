package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Facaulty;
import com.lecturerappointmentsystem.service.FacaultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "Facaulty")
@RequestMapping("v1/facaulty")
public class FacaultyController {

    private final FacaultyService facaultyService;

    public FacaultyController(FacaultyService facaultyService) {
        this.facaultyService = facaultyService;
    }

    @GetMapping("")
    @ApiOperation("Get Facaulty")
    public Page<Facaulty> getAll(@PageableDefault(sort = "name") Pageable pageable,
                                 @RequestParam(required = false) String search) {
        return facaultyService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All Facaulty")
    public Collection<Facaulty> getAll() {
        return facaultyService.findAll();
    }

    @GetMapping("/{facaultyId}")
    @ApiOperation("Get a Facaulty by Id")
    public Facaulty getFacaulty(@PathVariable long facaultyId) {
        return facaultyService.findById(facaultyId);
    }

    @DeleteMapping("/{facaultyId}")
    @ApiOperation("Delete a Facaulty by Id")
    public void delete(@PathVariable long facaultyId) {
        facaultyService.delete(facaultyId);
    }


    @PostMapping("")
    @ApiOperation("Create Facaulty")
    public Facaulty create(@RequestBody Facaulty request) {
        return facaultyService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public Facaulty update(@RequestBody Facaulty request, @PathVariable long facaultyId) {
        if(request.getId() != facaultyId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return facaultyService.update(request);
    }
}
