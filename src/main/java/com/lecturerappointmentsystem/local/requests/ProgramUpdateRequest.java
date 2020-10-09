package com.lecturerappointmentsystem.local.requests;

import com.lecturerappointmentsystem.model.Department;
import lombok.Data;

@Data
public class ProgramUpdateRequest {

    private long id;

    private String name;

    private String description;

    private long departmentId;

    private Department department;

}
