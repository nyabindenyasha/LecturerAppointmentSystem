package com.lecturerappointmentsystem.local.requests;

import com.lecturerappointmentsystem.model.Department;
import lombok.Data;

@Data
public class ProgramCreateRequest {

    private String name;

    private String description;

    private long departmentId;

    private Department department;
}
