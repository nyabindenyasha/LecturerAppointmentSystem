package com.lecturerappointmentsystem.local.requests;

import com.lecturerappointmentsystem.model.Facaulty;
import lombok.Data;

@Data
public class DepartmentCreateRequest {

    private String name;

    private String description;

    private Integer facaultlyId;

    private Facaulty facaulty;
}
