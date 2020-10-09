package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.requests.ProgramCreateRequest;
import com.lecturerappointmentsystem.local.requests.ProgramUpdateRequest;
import com.lecturerappointmentsystem.model.Programs;

public interface ProgramService extends BaseService<Programs, ProgramCreateRequest, ProgramUpdateRequest> {

}
