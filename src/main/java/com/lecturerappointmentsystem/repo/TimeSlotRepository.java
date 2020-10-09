package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.TimeSlots;

public interface TimeSlotRepository extends BaseRepository<TimeSlots> {

    boolean existsByName(String name);

}
