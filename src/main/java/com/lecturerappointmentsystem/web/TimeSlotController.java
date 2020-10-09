package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.TimeSlots;
import com.lecturerappointmentsystem.service.TimeSlotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "TimeSlots")
@RequestMapping("v1/timeSlots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("")
    @ApiOperation("Get TimeSlots")
    public Page<TimeSlots> getAll(@PageableDefault(sort = "name") Pageable pageable,
                              @RequestParam(required = false) String search) {
        return timeSlotService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All TimeSlots")
    public Collection<TimeSlots> getAll() {
        return timeSlotService.findAll();
    }

    @GetMapping("/{timeSlotId}")
    @ApiOperation("Get a TimeSlot by Id")
    public TimeSlots getTimeSlot(@PathVariable long timeSlotId) {
        return timeSlotService.findById(timeSlotId);
    }

    @DeleteMapping("/{timeSlotId}")
    @ApiOperation("Delete a TimeSlot by Id")
    public void delete(@PathVariable long timeSlotId) {
        timeSlotService.delete(timeSlotId);
    }


    @PostMapping("")
    @ApiOperation("Create TimeSlot")
    public TimeSlots create(@RequestBody TimeSlots request) {
        return timeSlotService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public TimeSlots update(@RequestBody TimeSlots request, @PathVariable long timeSlotId) {
        if(request.getId() != timeSlotId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return timeSlotService.update(request);
    }
}
