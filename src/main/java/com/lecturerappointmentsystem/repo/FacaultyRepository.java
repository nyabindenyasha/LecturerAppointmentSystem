package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.Facaulty;

public interface FacaultyRepository extends BaseRepository<Facaulty> {

    boolean existsByName(String name);

}
