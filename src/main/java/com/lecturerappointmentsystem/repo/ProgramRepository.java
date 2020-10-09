package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.Programs;

public interface ProgramRepository extends BaseRepository<Programs> {

    boolean existsByName(String name);

}
