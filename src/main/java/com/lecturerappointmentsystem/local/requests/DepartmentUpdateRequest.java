package com.lecturerappointmentsystem.local.requests;

import com.lecturerappointmentsystem.model.Facaulty;
import lombok.Data;

@Data
public class DepartmentUpdateRequest {

    private long id;

    private String name;

    private String description;

    private long facaultlyId;

    private Facaulty facaulty;
}
