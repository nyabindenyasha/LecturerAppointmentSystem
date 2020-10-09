package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Appointments;
import com.lecturerappointmentsystem.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "Appointments")
@RequestMapping("v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    @ApiOperation("Get Appointments")
    public Page<Appointments> getAll(@PageableDefault(sort = "name") Pageable pageable,
                              @RequestParam(required = false) String search) {
        return appointmentService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All Appointments")
    public Collection<Appointments> getAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{appointmentId}")
    @ApiOperation("Get a Appointment by Id")
    public Appointments getAppointment(@PathVariable long appointmentId) {
        return appointmentService.findById(appointmentId);
    }

    @DeleteMapping("/{appointmentId}")
    @ApiOperation("Delete a Appointment by Id")
    public void delete(@PathVariable long appointmentId) {
        appointmentService.delete(appointmentId);
    }


    @PostMapping("")
    @ApiOperation("Create Appointment")
    public Appointments create(@RequestBody Appointments request) {
        return appointmentService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public Appointments update(@RequestBody Appointments request, @PathVariable long appointmentId) {
        if(request.getId() != appointmentId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return appointmentService.update(request);
    }
}
