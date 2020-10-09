package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.Department;

public interface DepartmentRepository extends BaseRepository<Department> {

    boolean existsByName(String name);

}
